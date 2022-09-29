package top.bioelectronic.framework;

import kotlin.coroutines.CoroutineContext;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.*;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.ImageType;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.ExternalResource;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.jetbrains.annotations.NotNull;
import top.bioelectronic.framework.config.DeviceInfoConfiguration;
import top.bioelectronic.framework.core.BaseRobot;
import top.bioelectronic.framework.event.impl.bot.LoginEvent;
import top.bioelectronic.sdk.common.Nullable;
import top.bioelectronic.sdk.event.IEvent;
import top.bioelectronic.sdk.framework.annotations.Mount;
import top.bioelectronic.sdk.logger.Marker;
import top.bioelectronic.sdk.robot.contact.SNContact;
import top.bioelectronic.sdk.robot.contact.SNGroup;
import top.bioelectronic.sdk.robot.contact.user.*;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;
import top.bioelectronic.sdk.robot.messages.content.SNImage;
import top.bioelectronic.sdk.robot.messages.meta.SNMessageSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static top.bioelectronic.framework.event.impl.bot.LoginEvent.INFO;
import static top.bioelectronic.framework.event.impl.bot.LoginEvent.WARN;

@Marker("MIRAI")
@Slf4j
public class MiraiRobot extends BaseRobot {

    private static final File cache = new File("cache/");
    @Mount
    protected DeviceInfoConfiguration deviceInfoConfiguration;
    @Getter
    private Bot bot = null;

    @Override
    public void onLoad() throws Exception {
        log.info("注意 您正在使用 sn-mirai 模块，仅供学习交流使用，严禁用于商业用途");
        log.info("关于 mirai 是一个在全平台下运行，提供 QQ Android 协议支持的高效率机器人库");
        log.info("项目地址 https://github.com/mamoe/mirai");
        log.info("LICENSES \n\n============================================================\n{}\n============================================================\n", "Copyright (C) 2019-2022 Mamoe Technologies and contributors.\n" +
                "\n" +
                "This program is free software: you can redistribute it and/or modify\n" +
                "it under the terms of the GNU Affero General Public License as\n" +
                "published by the Free Software Foundation, either version 3 of the\n" +
                "License, or (at your option) any later version.\n" +
                "\n" +
                "This program is distributed in the hope that it will be useful,\n" +
                "but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
                "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
                "GNU Affero General Public License for more details.\n" +
                "\n" +
                "You should have received a copy of the GNU Affero General Public License\n" +
                "along with this program.  If not, see <http://www.gnu.org/licenses/>.");
    }

    @Override
    protected boolean getStatus() {
        return bot != null && bot.isOnline();
    }

    @Override
    protected synchronized void initInstance() {
        if (bot == null) {

            final BotConfiguration configuration = new BotConfiguration();
            configuration.loadDeviceInfoJson(deviceInfoConfiguration.toString());
            eventChannel.post(new LoginEvent("已获取设备配置", INFO));
            configuration.noBotLog();
            configuration.autoReconnectOnForceOffline();
            try {
                configuration.setProtocol(BotConfiguration.MiraiProtocol.valueOf(robotConfiguration.getProtocol()));
            } catch (IllegalArgumentException e) {
                log.warn("MIRAI | {} 不是一个合法的登录协议名. 程序将使用默认的协议(MACOS). 支持的协议列表: {}",
                        robotConfiguration.getProtocol(),
                        Arrays.toString(BotConfiguration.MiraiProtocol.values())
                );
                eventChannel.post(new LoginEvent(robotConfiguration.getProtocol() + "不是一个合法的登录协议名. 程序将使用默认的协议(MACOS). ", WARN));
                configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
                robotConfiguration.setProtocol(BotConfiguration.MiraiProtocol.MACOS.name());
                try {
                    context.storeConfiguration();
                } catch (IOException ex) {
                    log.error("MIRAI | 保存配置时出现错误", ex);
                }
            }
            bot = BotFactory.INSTANCE.newBot(robotConfiguration.getAccount(), robotConfiguration.getPassword(), configuration);
            bot.getEventChannel().registerListenerHost(new SimpleListenerHost() {
                @Override
                public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
                    super.handleException(context, exception);
                }

                @EventHandler
                public ListeningStatus onEvent(Event event) {
                    eventChannel.post(converters.convert(event, IEvent.class));
                    return ListeningStatus.LISTENING;
                }
            });


        }
    }

    /**
     * 登录到qq
     */
    public void toLogin() throws Exception {
        bot.login();
    }

    @Override
    protected void close() throws Exception {
        if (bot != null) {
            bot.close();
        }
    }

    @Override
    public void test() {
        log.info("MIRAI TEST SUCCESS!");
    }

    private SNMessageSource sendMessage(SNContact contact, SNMessageChain chain) {
        if (contact == null || chain == null) return null;
        if (contact.getId() == bot.getId()) {
            log.warn("警告，由于协议bug，暂时禁用bot发给自己消息的功能！");
            return null;
        }
        if (isIllegalMessageChain(chain)) return null;
        Contact mc = converters.reverseConvert(contact, Contact.class);
        if (mc == null) return null;
        MessageChain messages = converters.reverseConvert(chain, MessageChain.class);
        try {
            return converters.convert(mc.sendMessage(messages).getSource(), SNMessageSource.class);
        } catch (Exception t) {
            log.warn("发送消息失败！目标：{}，消息链：{}，详情请检查日志！", mc, messages);
            log.debug("发送消息失败！目标：{}，消息链：{}\n", mc, messages, t);
            return null;
        }
    }

    @Override
    public SNMessageSource sendMessage(SNFriend friend, SNMessageChain chain) {
        return sendMessage((SNContact) friend, chain);
    }

    @Override
    public SNMessageSource sendMessage(SNGroup group, SNMessageChain chain) {
        return sendMessage((SNContact) group, chain);
    }

    @Override
    public SNMessageSource sendMessage(SNStranger stranger, SNMessageChain chain) {
        return sendMessage((SNContact) stranger, chain);
    }

    @Override
    public SNMessageSource sendMessage(SNNormalMember member, SNMessageChain chain) {
        return sendMessage((SNContact) member, chain);
    }

    @Override
    public SNFriend getFriend(long friendId) {
        if (friendId == 0L) return null;
        return converters.convert(bot.getFriend(friendId), SNFriend.class);
    }

    @Override
    public SNGroup getGroup(long groupId) {
        if (groupId == 0L) return null;
        return converters.convert(bot.getGroup(groupId), SNGroup.class);
    }

    @Override
    public SNMember getGroupMember(SNGroup group, long memberId) {
        return converters.convert(converters.reverseConvert(group, Group.class).get(memberId), SNMember.class);
    }

    @Override
    public SNStranger getStranger(long strangerId) {
        return converters.convert(bot.getStranger(strangerId), SNStranger.class);
    }

    @Override
    public List<SNFriend> getFriendList() {
        ContactList<Friend> friends = bot.getFriends();
        List<SNFriend> result = new ArrayList<>(friends.size());
        friends.forEach(friend -> result.add(converters.convert(friend, SNFriend.class)));
        return result;
    }

    @Override
    public List<SNGroup> getGroupsList() {
        ContactList<Group> groups = bot.getGroups();
        List<SNGroup> result = new ArrayList<>(groups.size());
        groups.forEach(group -> result.add(converters.convert(group, SNGroup.class)));
        return result;
    }

    @Override
    @Nullable
    public List<SNMember> getGroupMembers(SNGroup group) {
        Group mg = converters.reverseConvert(group, Group.class);
        if (mg == null) return null;
        ContactList<NormalMember> members = mg.getMembers();
        List<SNMember> result = new ArrayList<>(members.size());
        members.forEach(member -> result.add(converters.convert(member, SNMember.class)));
        return result;
    }

    @Override
    public long getBotId() {
        return bot.getId();
    }

    @Override
    public SNImage uploadImg(@NotNull File file) throws IOException {
        // 文件存在，计算md5，获取图片id
        String md5;
        String fileName = file.getName();
        try (FileInputStream fis = new FileInputStream(file)) {
            md5 = DigestUtils.md5Hex(fis);
        }
        String ext = FilenameUtils.getExtension(fileName);
        String imgId = "{" + md5.substring(0, 8) + "-" + md5.substring(8, 12) + "-" + md5.substring(12, 16) + "-" + md5.substring(16, 20) + "-" + md5.substring(20) + "}." + ext;
        imgId = imgId.toUpperCase();
        log.debug("{} 获取到的图片ID", imgId);
        Image.Builder builder = Image.newBuilder(imgId);
        builder.setType(ImageType.match(ext));
        builder.setSize(file.length());
        Image image = builder.build();
        if (Image.isUploaded(image, bot)) {
            return converters.convert(image, SNImage.class);
        } else {
            log.debug("{} 未上传的图片ID，即将上传", imgId);
            Contact contact = null;
            ContactList<Friend> friends = bot.getFriends();
            if (friends.size() != 0) {
                contact = friends.iterator().next();
            } else {
                ContactList<Group> groups = bot.getGroups();
                if (groups.size() != 0) {
                    contact = groups.iterator().next();
                } else {
                    ContactList<Stranger> strangers = bot.getStrangers();
                    if (strangers.size() != 0) {
                        contact = strangers.iterator().next();
                    }
                }
            }
            if (contact == null) throw new IOException("No contact to upload!");

            try (ExternalResource resource = ExternalResource.create(file)) {
                return converters.convert(Contact.uploadImage(contact, resource), SNImage.class);
            }

        }
    }

    @Override
    public SNImage uploadImg(@NotNull URL url, boolean forceUpdate) throws IOException {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            String contentType = conn.getContentType();
            url = conn.getURL();
            // 被缓存的文件
            File urlDir = new File(cache + "/img/" + url.getHost());
            urlDir.mkdirs();
            String fileName = FilenameUtils.getBaseName(url.getPath());
            if (FilenameUtils.getExtension(fileName).isEmpty()) {
                try {
                    fileName += MimeTypes.getDefaultMimeTypes().forName(contentType).getExtension();
                } catch (MimeTypeException e) {
                    fileName += ".jpg";
                }
            }
            File cacheFile = new File(urlDir + File.separator + fileName);
            if (forceUpdate) {
                cacheFile.delete();
            }
            if (!cacheFile.exists()) {
                // 保存文件，下载需要改进，改为多线程下载
                IOUtils.copy(url, cacheFile);
                log.debug("{} 图片已缓存", cacheFile);
            }
            return uploadImg(cacheFile);

        } finally {
            if (conn != null)
                conn.disconnect();
        }

    }

    @Override
    public SNImage uploadImg(@NotNull URL url) throws IOException {
        return uploadImg(url, false);
    }

    @Override
    public boolean recall(SNMessageSource source) {
        try {
            MessageSource.recall(converters.reverseConvert(source, MessageSource.class));
        } catch (Exception e) {
            log.warn("撤回消息失败！时间戳：{}，消息链：{}，详情请检查日志！", source.getTime(), source.getOriginalMessage());
            log.debug("撤回消息失败！时间戳：{}，消息链：{}\n", source.getTime(), source.getOriginalMessage(), e);
            return false;
        }
        return true;

    }

    @Override
    public boolean mute(SNMember member, int durationSeconds) {

        Member m = converters.reverseConvert(member, Member.class);
        try {
            m.mute(durationSeconds);
        } catch (Exception e) {
            log.warn("禁言失败！群组：{}，目标：{}，时间：{}，详情请检查日志！", member.getGroup().getId(), member.getId(), durationSeconds);
            log.debug("禁言失败！群组：{}，目标：{}，时间：{}\n", member.getGroup().getId(), member.getId(), durationSeconds, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean unmute(SNNormalMember member) {
        NormalMember m = converters.reverseConvert(member, NormalMember.class);
        try {
            m.unmute();
        } catch (Exception e) {
            log.warn("解除禁言失败！群组：{}，目标：{}，详情请检查日志！", member.getGroup().getId(), member.getId());
            log.debug("解除禁言失败！群组：{}，目标：{}\n", member.getGroup().getId(), member.getId(), e);
            return false;
        }
        return true;
    }

    @Override
    public void nudge(SNUser target, SNContact sendTo) {
        User user = converters.reverseConvert(target, User.class);
        Contact contact = converters.reverseConvert(sendTo, Contact.class);
        user.nudge().sendTo(contact);
    }

    @Override
    public boolean kick(SNNormalMember member, String message, boolean block) {
        NormalMember m = converters.reverseConvert(member, NormalMember.class);
        try{
            m.kick(message, block);
        } catch (Exception e){
            log.warn("踢出群成员失败！群组：{}，目标：{}，详情请检查日志！", member.getGroup().getId(), member.getId());
            log.debug("踢出群成员失败！群组：{}，目标：{}\n", member.getGroup().getId(), member.getId(), e);
            return false;
        }
        return true;
    }

    @Override
    public String getCoreType() {
        return "MIRAI";
    }


}

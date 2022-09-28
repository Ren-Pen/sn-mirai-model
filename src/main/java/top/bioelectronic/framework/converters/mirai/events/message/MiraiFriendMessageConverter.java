package top.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.FriendMessageEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNFriend;
import top.bioelectronic.sdk.robot.events.messages.SNFriendMessageEvent;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;

public class MiraiFriendMessageConverter extends Converter<FriendMessageEvent, SNFriendMessageEvent, MiraiRobot> {
    @Override
    public SNFriendMessageEvent convert(FriendMessageEvent friendMessageEvent) {

        return new SNFriendMessageEvent(
                converters.convert(friendMessageEvent.getMessage(), SNMessageChain.class),
                converters.convert(friendMessageEvent.getSender(), SNFriend.class),
                friendMessageEvent.getTime()
        );
    }
}

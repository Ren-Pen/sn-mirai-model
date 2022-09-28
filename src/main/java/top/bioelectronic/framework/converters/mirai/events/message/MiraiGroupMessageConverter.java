package top.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNMember;
import top.bioelectronic.sdk.robot.events.messages.SNGroupMessageEvent;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;

public class MiraiGroupMessageConverter extends Converter<GroupMessageEvent, SNGroupMessageEvent, MiraiRobot> {

    @Override
    public SNGroupMessageEvent convert(GroupMessageEvent event) {

        SNMember member = converters.convert(event.getSender(), SNMember.class);
        return new SNGroupMessageEvent(
                converters.convert(event.getMessage(), SNMessageChain.class),
                member.getGroup(),
                member,
                event.getTime()
        );
    }
}

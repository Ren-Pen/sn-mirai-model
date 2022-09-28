package top.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNMember;
import top.bioelectronic.sdk.robot.events.messages.SNGroupTempMessageEvent;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;

public class MiraiGroupTempMessageConverter extends Converter<GroupTempMessageEvent, SNGroupTempMessageEvent, MiraiRobot> {

    @Override
    public SNGroupTempMessageEvent convert(GroupTempMessageEvent event) {

        return new SNGroupTempMessageEvent(
                converters.convert(event.getMessage(), SNMessageChain.class),
                converters.convert(event.getSender(), SNMember.class),
                event.getTime()
        );
    }

}

package com.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNMember;
import com.bioelectronic.sdk.robot.events.messages.SNGroupTempMessageEvent;
import com.bioelectronic.sdk.robot.messages.SNMessageChain;

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

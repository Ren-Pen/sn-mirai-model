package com.slimenano.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNMember;
import com.slimenano.sdk.robot.events.messages.SNGroupTempMessageEvent;
import com.slimenano.sdk.robot.messages.SNMessageChain;

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

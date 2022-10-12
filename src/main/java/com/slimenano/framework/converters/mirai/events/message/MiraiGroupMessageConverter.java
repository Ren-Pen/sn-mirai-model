package com.slimenano.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNMember;
import com.slimenano.sdk.robot.events.messages.SNGroupMessageEvent;
import com.slimenano.sdk.robot.messages.SNMessageChain;

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

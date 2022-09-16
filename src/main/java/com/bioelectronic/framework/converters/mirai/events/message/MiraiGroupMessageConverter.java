package com.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNMember;
import com.bioelectronic.sdk.robot.events.messages.SNGroupMessageEvent;
import com.bioelectronic.sdk.robot.messages.SNMessageChain;

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

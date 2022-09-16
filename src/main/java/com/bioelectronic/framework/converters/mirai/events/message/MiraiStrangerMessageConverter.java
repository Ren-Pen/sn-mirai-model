package com.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.StrangerMessageEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNStranger;
import com.bioelectronic.sdk.robot.events.messages.SNStrangerMessageEvent;
import com.bioelectronic.sdk.robot.messages.SNMessageChain;

public class MiraiStrangerMessageConverter extends Converter<StrangerMessageEvent, SNStrangerMessageEvent, MiraiRobot> {

    @Override
    public SNStrangerMessageEvent convert(StrangerMessageEvent event) {

        return new SNStrangerMessageEvent(
                converters.convert(event.getMessage(), SNMessageChain.class),
                converters.convert(event.getSender(), SNStranger.class),
                event.getTime()
        );
    }
}

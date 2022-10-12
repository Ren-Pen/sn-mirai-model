package com.slimenano.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.StrangerMessageEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNStranger;
import com.slimenano.sdk.robot.events.messages.SNStrangerMessageEvent;
import com.slimenano.sdk.robot.messages.SNMessageChain;

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

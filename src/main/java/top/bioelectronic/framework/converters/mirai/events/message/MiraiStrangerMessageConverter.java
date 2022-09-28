package top.bioelectronic.framework.converters.mirai.events.message;

import net.mamoe.mirai.event.events.StrangerMessageEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNStranger;
import top.bioelectronic.sdk.robot.events.messages.SNStrangerMessageEvent;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;

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

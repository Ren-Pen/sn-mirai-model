package com.bioelectronic.framework.converters.mirai.events.bot;

import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import net.mamoe.mirai.event.events.BotNickChangedEvent;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.events.bot.SNBotNickChangedEvent;

public class BotNickChangedConverter
        extends Converter<BotNickChangedEvent, SNBotNickChangedEvent, MiraiRobot> {
    @Override
    public SNBotNickChangedEvent convert(
            BotNickChangedEvent botNickChangedEvent) {

        return new SNBotNickChangedEvent(botNickChangedEvent.getFrom(), botNickChangedEvent.getTo());
    }
}

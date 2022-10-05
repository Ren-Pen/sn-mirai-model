package com.slimenano.framework.converters.mirai.events.bot;

import com.slimenano.framework.MiraiRobot;
import net.mamoe.mirai.event.events.BotNickChangedEvent;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.events.bot.SNBotNickChangedEvent;

public class BotNickChangedConverter
        extends Converter<BotNickChangedEvent, SNBotNickChangedEvent, MiraiRobot> {
    @Override
    public SNBotNickChangedEvent convert(
            BotNickChangedEvent botNickChangedEvent) {

        return new SNBotNickChangedEvent(botNickChangedEvent.getFrom(), botNickChangedEvent.getTo());
    }
}

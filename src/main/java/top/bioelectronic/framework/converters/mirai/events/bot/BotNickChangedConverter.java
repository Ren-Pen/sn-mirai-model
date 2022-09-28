package top.bioelectronic.framework.converters.mirai.events.bot;

import top.bioelectronic.framework.MiraiRobot;
import net.mamoe.mirai.event.events.BotNickChangedEvent;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.events.bot.SNBotNickChangedEvent;

public class BotNickChangedConverter
        extends Converter<BotNickChangedEvent, SNBotNickChangedEvent, MiraiRobot> {
    @Override
    public SNBotNickChangedEvent convert(
            BotNickChangedEvent botNickChangedEvent) {

        return new SNBotNickChangedEvent(botNickChangedEvent.getFrom(), botNickChangedEvent.getTo());
    }
}

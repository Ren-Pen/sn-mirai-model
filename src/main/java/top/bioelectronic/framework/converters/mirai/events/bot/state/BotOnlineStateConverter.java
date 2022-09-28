package top.bioelectronic.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOnlineEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.events.BotLinkStateChangeEvent;

public class BotOnlineStateConverter extends Converter<BotOnlineEvent, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOnlineEvent botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.ONLINE);
    }
}


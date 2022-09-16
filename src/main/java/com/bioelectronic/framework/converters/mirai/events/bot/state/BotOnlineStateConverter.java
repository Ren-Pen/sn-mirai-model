package com.bioelectronic.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOnlineEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.events.BotLinkStateChangeEvent;

public class BotOnlineStateConverter extends Converter<BotOnlineEvent, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOnlineEvent botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.ONLINE);
    }
}


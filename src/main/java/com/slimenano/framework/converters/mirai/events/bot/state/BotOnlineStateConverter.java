package com.slimenano.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOnlineEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.events.BotLinkStateChangeEvent;

public class BotOnlineStateConverter extends Converter<BotOnlineEvent, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOnlineEvent botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.ONLINE);
    }
}


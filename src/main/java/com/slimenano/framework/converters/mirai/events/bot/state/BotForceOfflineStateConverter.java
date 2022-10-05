package com.slimenano.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOfflineEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.events.BotLinkStateChangeEvent;

public class BotForceOfflineStateConverter extends Converter<BotOfflineEvent.Force, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOfflineEvent.Force botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.FORCE);
    }
}

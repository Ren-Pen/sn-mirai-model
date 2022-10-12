package com.slimenano.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOfflineEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.events.BotLinkStateChangeEvent;

public class BotOfflineStateConverter extends Converter<BotOfflineEvent, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOfflineEvent botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.OFFLINE);
    }
}

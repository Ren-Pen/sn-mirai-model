package com.slimenano.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOfflineEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.events.BotLinkStateChangeEvent;

public class BotMsfOfflineStateConverter extends Converter<BotOfflineEvent.MsfOffline, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOfflineEvent.MsfOffline botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.MSF_OFFLINE);
    }
}

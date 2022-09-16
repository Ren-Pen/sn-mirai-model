package com.bioelectronic.framework.converters.mirai.events.bot.state;

import net.mamoe.mirai.event.events.BotOfflineEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.events.BotLinkStateChangeEvent;

public class BotMsfOfflineStateConverter extends Converter<BotOfflineEvent.MsfOffline, BotLinkStateChangeEvent, MiraiRobot> {
    @Override
    public BotLinkStateChangeEvent convert(BotOfflineEvent.MsfOffline botOnlineEvent) {
        return new BotLinkStateChangeEvent(BotLinkStateChangeEvent.MSF_OFFLINE);
    }
}

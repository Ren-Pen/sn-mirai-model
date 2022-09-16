package com.bioelectronic.framework.converters.mirai.events.bot;

import net.mamoe.mirai.event.events.NudgeEvent;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.SNContact;
import com.bioelectronic.sdk.robot.events.bot.SNNudgeEvent;

public class MiraiBotNudgeConverter
        extends Converter<NudgeEvent, SNNudgeEvent, MiraiRobot> {
    @Override
    public SNNudgeEvent convert(NudgeEvent nudgeEvent) {

        SNContact from = converters.convert(nudgeEvent.getFrom(), SNContact.class);
        SNContact target = converters.convert(nudgeEvent.getTarget(), SNContact.class);

        return new SNNudgeEvent(from, target, nudgeEvent.getAction(), nudgeEvent.getSuffix());
    }

}

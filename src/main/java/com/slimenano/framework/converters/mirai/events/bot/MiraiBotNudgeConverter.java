package com.slimenano.framework.converters.mirai.events.bot;

import net.mamoe.mirai.event.events.NudgeEvent;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.SNContact;
import com.slimenano.sdk.robot.events.bot.SNNudgeEvent;

public class MiraiBotNudgeConverter
        extends Converter<NudgeEvent, SNNudgeEvent, MiraiRobot> {
    @Override
    public SNNudgeEvent convert(NudgeEvent nudgeEvent) {

        SNContact from = converters.convert(nudgeEvent.getFrom(), SNContact.class);
        SNContact target = converters.convert(nudgeEvent.getTarget(), SNContact.class);

        return new SNNudgeEvent(from, target, nudgeEvent.getAction(), nudgeEvent.getSuffix());
    }

}

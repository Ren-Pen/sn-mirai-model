package top.bioelectronic.framework.converters.mirai.events.bot;

import net.mamoe.mirai.event.events.NudgeEvent;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.SNContact;
import top.bioelectronic.sdk.robot.events.bot.SNNudgeEvent;

public class MiraiBotNudgeConverter
        extends Converter<NudgeEvent, SNNudgeEvent, MiraiRobot> {
    @Override
    public SNNudgeEvent convert(NudgeEvent nudgeEvent) {

        SNContact from = converters.convert(nudgeEvent.getFrom(), SNContact.class);
        SNContact target = converters.convert(nudgeEvent.getTarget(), SNContact.class);

        return new SNNudgeEvent(from, target, nudgeEvent.getAction(), nudgeEvent.getSuffix());
    }

}

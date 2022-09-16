package com.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Stranger;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.user.SNStranger;
import com.bioelectronic.sdk.robot.contact.user.SNStrangerImpl;
import com.bioelectronic.sdk.robot.contact.user.SNUserProfile;

public class StrangerConverter extends Converter<Stranger, SNStranger, MiraiRobot> {
    @Override
    public SNStranger convert(Stranger stranger) {
        if (stranger == null) return null;

        return new SNStrangerImpl(stranger.getId(), converters.convert(stranger.queryProfile(), SNUserProfile.class));
    }

    @Override
    public Stranger reverse_convert(SNStranger miraiStranger) {
        return robot.getBot().getStranger(miraiStranger.getId());
    }
}

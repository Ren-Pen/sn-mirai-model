package com.slimenano.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Stranger;
import com.slimenano.sdk.contact.user.MiraiStrangerImpl;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.user.SNStranger;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;

public class StrangerConverter extends Converter<Stranger, SNStranger, MiraiRobot> {
    @Override
    public SNStranger convert(Stranger stranger) {
        if (stranger == null) return null;

        return new MiraiStrangerImpl(stranger.getId(), converters.convert(stranger.queryProfile(), SNUserProfile.class));
    }

    @Override
    public Stranger reverse_convert(SNStranger miraiStranger) {
        return robot.getBot().getStranger(miraiStranger.getId());
    }
}

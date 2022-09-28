package top.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.Stranger;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.contact.user.SNStranger;
import top.bioelectronic.sdk.robot.contact.user.SNStrangerImpl;
import top.bioelectronic.sdk.robot.contact.user.SNUserProfile;

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

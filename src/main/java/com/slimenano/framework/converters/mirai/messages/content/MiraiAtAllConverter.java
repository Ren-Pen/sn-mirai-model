package com.slimenano.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.AtAll;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.content.SNAtAll;

public class MiraiAtAllConverter extends Converter<AtAll, SNAtAll, MiraiRobot> {
    @Override
    public SNAtAll convert(AtAll atAll) {
        return new SNAtAll();
    }

    @Override
    public AtAll reverse_convert(SNAtAll miraiAtAll) {
        return AtAll.INSTANCE;
    }
}

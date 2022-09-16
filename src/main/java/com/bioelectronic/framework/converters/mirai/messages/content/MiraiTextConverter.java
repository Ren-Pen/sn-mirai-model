package com.bioelectronic.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.PlainText;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.messages.content.SNText;

public class MiraiTextConverter extends Converter<PlainText, SNText, MiraiRobot> {
    @Override
    public SNText convert(PlainText plainText) {
        return new SNText(plainText.getContent());
    }

    @Override
    public PlainText reverse_convert(SNText miraiText) {
        return new PlainText(miraiText.getContent());
    }
}

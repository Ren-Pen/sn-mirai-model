package top.bioelectronic.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.PlainText;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.messages.content.SNText;

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

package top.bioelectronic.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.AtAll;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.messages.content.SNAtAll;

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

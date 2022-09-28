package top.bioelectronic.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.At;
import top.bioelectronic.framework.converters.mirai.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.messages.content.SNAt;

public class MiraiAtConverter extends Converter<At, SNAt, MiraiRobot> {
    @Override
    public SNAt convert(At at) {
        return new SNAt(at.getTarget());
    }

    @Override
    public At reverse_convert(SNAt miraiAt) {
        return new At(miraiAt.getTarget());
    }
}

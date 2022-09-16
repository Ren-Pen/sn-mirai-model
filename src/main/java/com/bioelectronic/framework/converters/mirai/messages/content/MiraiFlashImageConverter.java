package com.bioelectronic.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.FlashImage;
import net.mamoe.mirai.message.data.Image;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.messages.content.SNFlashImage;
import com.bioelectronic.sdk.robot.messages.content.SNImage;

public class MiraiFlashImageConverter extends Converter<FlashImage, SNFlashImage, MiraiRobot> {
    @Override
    public SNFlashImage convert(FlashImage flashImage) {
        return new SNFlashImage(converters.convert(flashImage.getImage(), SNImage.class));
    }

    @Override
    public FlashImage reverse_convert(SNFlashImage miraiFlashImage) {
        return FlashImage.from(converters.reverseConvert(miraiFlashImage, Image.class));
    }
}

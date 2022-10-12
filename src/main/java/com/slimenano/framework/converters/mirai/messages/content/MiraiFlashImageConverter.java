package com.slimenano.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.FlashImage;
import net.mamoe.mirai.message.data.Image;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.content.SNFlashImage;
import com.slimenano.sdk.robot.messages.content.SNImage;

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

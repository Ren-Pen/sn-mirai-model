package com.slimenano.framework.converters.mirai.messages.content;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.message.data.Image;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.content.SNImage;

@Slf4j
public class MiraiImageConverter extends Converter<Image, SNImage, MiraiRobot> {

    @Override
    public SNImage convert(Image image) {

        return new SNImage(
                Image.queryUrl(image),
                image.getImageId(),
                image.getImageType().toString(),
                image.getHeight(),
                image.getWidth(),
                image.getSize(),
                image.getMd5()
        );
    }

    @Override
    public Image reverse_convert(SNImage miraiImage) {
        return Image.fromId(miraiImage.getImageId());
    }
}

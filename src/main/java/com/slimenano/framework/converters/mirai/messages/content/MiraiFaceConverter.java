package com.slimenano.framework.converters.mirai.messages.content;

import com.slimenano.framework.MiraiRobot;
import net.mamoe.mirai.message.data.Face;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.content.SNFace;

public class MiraiFaceConverter extends Converter<Face, SNFace, MiraiRobot> {
    @Override
    public SNFace convert(Face face) {
        return new SNFace(face.getId(), face.getName());
    }

    @Override
    public Face reverse_convert(SNFace miraiFace) {
        return new Face(miraiFace.getId());
    }
}

package com.bioelectronic.framework.converters.mirai.messages.content;

import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import net.mamoe.mirai.message.data.Face;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.messages.content.SNFace;

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

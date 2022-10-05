package com.slimenano.framework.converters.mirai.messages.content;

import net.mamoe.mirai.message.data.PokeMessage;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.sdk.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.content.SNPoke;

public class MiraiPokeConverter extends Converter<PokeMessage, SNPoke, MiraiRobot> {
    @Override
    public SNPoke convert(PokeMessage poke) {
        return new SNPoke(poke.getId(), poke.getPokeType(), poke.getName());
    }

    @Override
    public PokeMessage reverse_convert(SNPoke miraiPoke) {
        for (PokeMessage value : PokeMessage.values) {
            if (value.getId() == miraiPoke.getId() && value.getPokeType() == miraiPoke.getPokeType()) return value;
        }
        return PokeMessage.ChuoYiChuo;
    }
}

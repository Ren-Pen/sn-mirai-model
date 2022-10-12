package com.slimenano.framework.converters.mirai.messages.content;

import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.content.SNAudio;
import net.mamoe.mirai.message.data.Audio;
import net.mamoe.mirai.message.data.AudioCodec;
import net.mamoe.mirai.message.data.OfflineAudio;
import net.mamoe.mirai.message.data.OnlineAudio;

public class MiraiAudioConverter extends Converter<Audio, SNAudio, MiraiRobot> {

    @Override
    public SNAudio convert(Audio audio) {

        String url = null;

        if (audio instanceof OnlineAudio){
            url = ((OnlineAudio) audio).getUrlForDownload();
        }

        return new SNAudio(audio.getFilename(), audio.getFileMd5(), audio.getFileSize(), audio.getCodec().getFormatName(), audio.getExtraData(), url);
    }

    @Override
    public Audio reverse_convert(SNAudio snAudio) {
        return OfflineAudio.Factory.INSTANCE.create(snAudio.getFilename(), snAudio.getFileMd5(), snAudio.getFileSize(), AudioCodec.fromFormatName(snAudio.getCodec()), snAudio.getExtra());
    }
}

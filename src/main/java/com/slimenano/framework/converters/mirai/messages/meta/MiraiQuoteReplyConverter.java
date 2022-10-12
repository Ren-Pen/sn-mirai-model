package com.slimenano.framework.converters.mirai.messages.meta;

import com.slimenano.framework.MiraiRobot;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.message.data.QuoteReply;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.messages.meta.SNMessageSource;
import com.slimenano.sdk.robot.messages.meta.SNQuoteReply;

public class MiraiQuoteReplyConverter extends Converter<QuoteReply, SNQuoteReply, MiraiRobot> {

    @Override
    public SNQuoteReply convert(QuoteReply quoteReply) {
        return new SNQuoteReply(converters.convert(quoteReply.getSource(), SNMessageSource.class));
    }

    @Override
    public QuoteReply reverse_convert(SNQuoteReply miraiQuoteReply) {
        return new QuoteReply(converters.reverseConvert(miraiQuoteReply.getSource(), MessageSource.class));
    }
}

package com.bioelectronic.framework.converters.mirai.messages.meta;

import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import net.mamoe.mirai.message.data.MessageSource;
import net.mamoe.mirai.message.data.QuoteReply;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.messages.meta.SNMessageSource;
import com.bioelectronic.sdk.robot.messages.meta.SNQuoteReply;

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

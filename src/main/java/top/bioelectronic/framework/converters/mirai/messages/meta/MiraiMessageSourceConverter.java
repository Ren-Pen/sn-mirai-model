package top.bioelectronic.framework.converters.mirai.messages.meta;

import net.mamoe.mirai.message.data.*;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;
import top.bioelectronic.sdk.robot.messages.meta.SNMessageSource;

public class MiraiMessageSourceConverter extends Converter<MessageSource, SNMessageSource, MiraiRobot> {
    @Override
    public SNMessageSource convert(MessageSource source) {

        return new SNMessageSource(
                source.getIds(),
                source.getInternalIds(),
                source.getTime(),
                source.getFromId(),
                source.getTargetId(),
                source.getBotId(),
                MessageUtils.getKind(source).name(),
                converters.convert(source.getOriginalMessage(), SNMessageChain.class)
        );
    }

    @Override
    public MessageSource reverse_convert(SNMessageSource source) {
        MessageSourceBuilder builder = new MessageSourceBuilder();
        builder.setIds(source.getIds());
        builder.setInternalIds(source.getInternalIds());
        builder.setTime(source.getTime());
        builder.setFromId(source.getFrom());
        builder.setTargetId(source.getTarget());
        builder.messages(converters.reverseConvert(source.getOriginalMessage(), MessageChain.class));

        return builder.build(source.getBotId(), MessageSourceKind.valueOf(source.getKind()));
    }
}

package top.bioelectronic.framework.converters.mirai.messages;

import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.SingleMessage;
import top.bioelectronic.framework.MiraiRobot;
import top.bioelectronic.sdk.framework.converters.Converter;
import top.bioelectronic.sdk.robot.messages.SNMessage;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;

public class MessageChainConverter extends Converter<MessageChain, SNMessageChain, MiraiRobot> {

    @Override
    public SNMessageChain convert(MessageChain chain) {
        SNMessageChain messageChain = new SNMessageChain();
        for (SingleMessage singleMessage : chain) {
            messageChain.add(converters.convert(singleMessage, SNMessage.class));
        }
        return messageChain;
    }

    @Override
    public MessageChain reverse_convert(SNMessageChain chain) {

        MessageChainBuilder builder = new MessageChainBuilder();
        for (SNMessage msg : chain) {
            Message message = converters.reverseConvert(msg, Message.class);
            if (message != null) builder.add(message);
        }

        return builder.build();
    }
}

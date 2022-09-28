package top.bioelectronic.framework.contact;

import top.bioelectronic.sdk.core.Robot;
import top.bioelectronic.sdk.robot.contact.SNGroup;
import top.bioelectronic.sdk.robot.contact.SNMemberPermission;
import top.bioelectronic.sdk.robot.messages.SNMessageChain;
import top.bioelectronic.sdk.robot.messages.meta.SNMessageSource;
import lombok.Getter;

@Getter
public class MiraiGroupImpl implements SNGroup {

    private final long id;
    private final String name;
    private final String avatarUrl;
    private final SNMemberPermission botPermission;
    private final long owner;


    public MiraiGroupImpl(long id, String name, String avatarUrl, SNMemberPermission botPermission, long owner) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.botPermission = botPermission;
        this.owner = owner;
    }

    @Override
    public SNMessageSource sendMessage(Robot robot, SNMessageChain chain) {
        return robot.sendMessage(this, chain);
    }
}
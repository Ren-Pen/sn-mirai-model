package com.bioelectronic.framework.converters.mirai.person;

import net.mamoe.mirai.contact.AnonymousMember;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.NormalMember;
import com.bioelectronic.framework.converters.mirai.MiraiRobot;
import com.bioelectronic.sdk.framework.converters.Converter;
import com.bioelectronic.sdk.robot.contact.SNGroup;
import com.bioelectronic.sdk.robot.contact.SNMemberPermission;
import com.bioelectronic.sdk.robot.contact.user.SNAnonymousMemberImpl;
import com.bioelectronic.sdk.robot.contact.user.SNMember;
import com.bioelectronic.sdk.robot.contact.user.SNNormalMemberImpl;
import com.bioelectronic.sdk.robot.contact.user.SNUserProfile;

public class MemberConverter extends Converter<Member, SNMember, MiraiRobot> {
    @Override
    public SNMember convert(Member member) {
        if (member == null) return null;


        if (member instanceof AnonymousMember) {
            return new SNAnonymousMemberImpl(
                    member.getId(),
                    converters.convert(member.queryProfile(), SNUserProfile.class),
                    converters.convert(member.getGroup(), SNGroup.class),
                    member.getSpecialTitle(),
                    member.getNameCard(),
                    SNMemberPermission.valueOf(member.getPermission().toString()),
                    ((AnonymousMember) member).getAnonymousId());
        } else {


            return new SNNormalMemberImpl(member.getId(),
                    converters.convert(member.queryProfile(), SNUserProfile.class),
                    converters.convert(member.getGroup(), SNGroup.class),
                    member.getSpecialTitle(),
                    member.getNameCard(),
                    SNMemberPermission.valueOf(member.getPermission().toString()),
                    ((NormalMember) member).getJoinTimestamp(),
                    ((NormalMember) member).getLastSpeakTimestamp(),
                    ((NormalMember) member).getMuteTimeRemaining());
        }

    }


    @Override
    public Member reverse_convert(SNMember member) {
        return converters.reverseConvert(member.getGroup(), Group.class).get(member.getId());
    }
}

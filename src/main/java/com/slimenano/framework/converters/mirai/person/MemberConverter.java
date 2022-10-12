package com.slimenano.framework.converters.mirai.person;

import net.mamoe.mirai.contact.AnonymousMember;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.NormalMember;
import com.slimenano.sdk.contact.user.MiraiAnonymousMemberImpl;
import com.slimenano.sdk.contact.user.MiraiNormalMemberImpl;
import com.slimenano.framework.MiraiRobot;
import com.slimenano.nscan.framework.converters.Converter;
import com.slimenano.sdk.robot.contact.SNGroup;
import com.slimenano.sdk.robot.contact.SNMemberPermission;
import com.slimenano.sdk.robot.contact.user.SNMember;
import com.slimenano.sdk.robot.contact.user.SNUserProfile;

public class MemberConverter extends Converter<Member, SNMember, MiraiRobot> {
    @Override
    public SNMember convert(Member member) {
        if (member == null) return null;


        if (member instanceof AnonymousMember) {
            return new MiraiAnonymousMemberImpl(
                    member.getId(),
                    converters.convert(member.queryProfile(), SNUserProfile.class),
                    converters.convert(member.getGroup(), SNGroup.class),
                    member.getSpecialTitle(),
                    member.getNameCard(),
                    SNMemberPermission.valueOf(member.getPermission().toString()),
                    ((AnonymousMember) member).getAnonymousId());
        } else {


            return new MiraiNormalMemberImpl(member.getId(),
                    converters.convert(member.queryProfile(), SNUserProfile.class),
                    converters.convert(member.getGroup(), SNGroup.class),
                    member.getSpecialTitle(),
                    member.getNameCard(),
                    SNMemberPermission.valueOf(member.getPermission().toString()),
                    ((NormalMember) member).getJoinTimestamp(),
                    ((NormalMember) member).getLastSpeakTimestamp(),
                    ((NormalMember) member).getMuteTimeRemaining(),
                    ((NormalMember) member).isMuted());
        }

    }


    @Override
    public Member reverse_convert(SNMember member) {
        return converters.reverseConvert(member.getGroup(), Group.class).get(member.getId());
    }
}

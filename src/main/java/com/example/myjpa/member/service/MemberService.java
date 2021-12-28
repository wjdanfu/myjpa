package com.example.myjpa.member.service;

import com.example.myjpa.domain.order.Member;
import com.example.myjpa.domain.order.MemberRepository;
import com.example.myjpa.member.dto.MemberDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long saveMember(MemberDto memberDto) {
        Member entity = memberRepository.save(convert(memberDto));
        return entity.getId();
    }

    private Member convert(MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setNickName(memberDto.getNickName());
        member.setAge(memberDto.getAge());
        member.setAddress(memberDto.getAddress());
        member.setDescription(memberDto.getDescription());
        member.setCreatedBy(memberDto.getCreate_by());
        return member;
    }
}

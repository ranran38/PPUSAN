package com.project.ppusan.service;

import org.springframework.stereotype.Service;

import com.project.ppusan.domain.Member;
import com.project.ppusan.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
   private final MemberMapper memberMapper;
   
   public void insertMember(Member member) {
      memberMapper.insertMember(member);
   }
   
   public Member findMember(String memberId) {
       return memberMapper.findMemberById(memberId);
   }
   
   public void updateMember(Member member) {
       memberMapper.updateMember(member);
   }
   
}
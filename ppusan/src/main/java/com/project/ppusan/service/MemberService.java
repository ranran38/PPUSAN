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
	
}

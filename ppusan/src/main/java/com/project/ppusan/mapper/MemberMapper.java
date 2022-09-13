package com.project.ppusan.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.ppusan.domain.Member;

/**
 * 회원정보 관련 매퍼
 */
@Mapper
public interface MemberMapper {
	public int insertMember(Member member);
	public Member findMemberById(String username);
}

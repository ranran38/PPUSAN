package com.project.ppusan.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder.Default;

public class FormData {
	@Size(min = 8, max = 20)
	private String memberId;		//사용자 이메일
	@NotEmpty
	private String password;		//사용자 비밀번호
	@NotEmpty
	private String nickname;		//사용자 닉네임
	
	private String role;			//사용자 구분. 'ROLE_USER':일반사용자
	
	public Member toMember() {
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(password);
		member.setNickname(nickname);
		member.setRole(role);
		return member;
	}
}

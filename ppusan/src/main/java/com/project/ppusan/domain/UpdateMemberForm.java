package com.project.ppusan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberForm {
	private String memberId;		//사용자 이메일
	private String password;		//사용자 비밀번호
	
	public Member toMember() {
		Member member = new Member();
		member.setMemberId(memberId);
		member.setPassword(password);
		return member;
	}

}

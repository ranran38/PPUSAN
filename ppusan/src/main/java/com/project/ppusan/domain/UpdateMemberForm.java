package com.project.ppusan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberForm {
	private String memberId;		//사용자 이메일
	private String emailAddress;	//사용자 이메일 주소
	private String password;		//사용자 비밀번호
	private String nickname;		//사용자 닉네임
	private String role;

}

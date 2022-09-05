package com.project.ppusan.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 (시큐리티를 사용한 회원 인증에 사용)
 * UserDetails 인터페이스를 implements
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	String memberId;		//사용자 이메일
	String password;		//사용자 비밀번호
	String nickname;		//사용자 닉네임
	String role;			//사용자 구분. 'ROLE_USER':일반사용자

}

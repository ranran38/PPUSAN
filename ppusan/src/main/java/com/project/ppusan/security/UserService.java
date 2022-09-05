package com.project.ppusan.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ppusan.domain.Member;
import com.project.ppusan.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final MemberMapper memberDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberDAO.findMemberById(username);
		if (member != null) {
			return new UserInfo(member);
		}
		return null;
	}

}

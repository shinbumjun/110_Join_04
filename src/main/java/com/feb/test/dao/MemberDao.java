package com.feb.test.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.feb.test.dto.Member;

@Repository
public interface MemberDao {

	// 회원가입
	public int join(HashMap<String, String> params);

	// ID 중복 확인을 위한 메서드
	public int Loginchick(String memberId);

	// 로그인 
	public Member login(String memberId);

}

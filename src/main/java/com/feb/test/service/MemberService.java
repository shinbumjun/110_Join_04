package com.feb.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.feb.test.dao.MemberDao;
import com.feb.test.dto.Member;
import com.feb.test.util.DuplicateUserIdException;
import com.feb.test.util.InvalidUserIdException;
import com.feb.test.util.Sha512Encoder;

@Service
public class MemberService {

	private static final int MAX_MEMBERID_LENGTH = 18; // 최대 길이
	private static final int MIN_MEMBERID_LENGTH = 6; // 최소 길이
	
	@Autowired
	MemberDao memberDao;
	
	// 회원가입
	public int join(HashMap<String, String> params, HttpServletRequest request) {
		
		System.out.println("member_id2 : " + params.get("member_id")); // sinbumjun
		System.out.println("passwd2 : " + params.get("passwd")); // 123
		System.out.println("email2 : " + params.get("email")); // sinbumjun123@naver.com
		System.out.println("Phone2 : " + params.get("Phone")); // 01038736380
		
		// *비밀번호 암호화
		Sha512Encoder encoder = Sha512Encoder.getInstance(); // 1. SHA-512 암호화를 위한 인스턴스를 얻는다
		System.out.println("encoder2 : " + encoder); // com.feb.test.util.Sha512Encoder@7c3a98fc
		
		String passwd = params.get("passwd"); // 2. 브라우저에서 입력한 비밀번호
		System.out.println("passwd2 : " + passwd); // 123
		
		String encodeTxt = encoder.getSecurePassword(passwd); // 3. 비밀번호 암호화
		System.out.println("encodeTxt2 : " + encodeTxt); // 3c9909afec25354d551dae21590...
		
		params.put("passwd", encodeTxt); // 4. 암호화한 패쓰워드로 저장
		
		// *회원ID 6자 이하 가입 불가, ID 중복 확인
		try {
			// 1. 회원ID 6자 이하 가입 불가
			System.out.println("아이디가 몇 글자인지 확인 : " + params.get("member_id").length()); // 9
		    if (!(MIN_MEMBERID_LENGTH <= params.get("member_id").length() && params.get("member_id").length() <= MAX_MEMBERID_LENGTH)) {
		        throw new InvalidUserIdException("회원 ID가 6자 이상으로 가입해주세요");
		    }
		    
		    // 2. ID 중복 확인
		    String memberId = params.get("member_id");
		    if(!Loginchick(memberId)) {
		    	throw new DuplicateUserIdException("회원 ID가 중복입니다");
		    }
		    
			return memberDao.join(params);
		} catch (InvalidUserIdException e1) { // 회원 ID가 6자 이하인 경우 발생
			request.setAttribute("errorMessage", e1.getMessage());
			return -1;
		} catch (DuplicateUserIdException e2) { // 회원 ID가 이미 데이터베이스에 존재하는 경우
			request.setAttribute("errorMessage", e2.getMessage());
			return -1;
		}
	}
	
	// ID 중복 확인을 위한 메서드
	private boolean Loginchick(String memberId) {
		int count = memberDao.Loginchick(memberId); // 0 또는 1을 반환
		return count==0; // 0을 반환하면 중복이 아니라는 것이기 때문에  true을 반환
	}

	// 로그인 
	public boolean login(HashMap<String, String> params, HttpServletRequest request) {

		System.out.println("member_id3 : " + params.get("member_id")); // sinbumjun
		System.out.println("passwd3 : " + params.get("passwd")); // 123
		
		// 1. id비교
		String memberId = params.get("member_id"); // 브라우저에서 입력한 값
		// tip : select시에 값이 없으면 null인데 JDBC는 tye-catch문으로 했었는데 mybatis에서는 if문으로 처리 가능하다
		Member member = memberDao.login(memberId); // select문 출력하기 때문에 Member타입이 필요하다
		System.out.println("member11111111111111111 : " + member); 
		// Member [memberIdx=2044, memberId=sinbumjun, passwd=3c9909afec25354d551... 
		
		// 바꼈다 원래는 없을경우 exception이 떴는데 mybatis는 그냥 null을 리턴해준다 
		// 서로 mybatis springjdbc랑 보는 객체가 1개일 때에 대한 처리기준이 서로 다르다   
		// if (member == null) {
		if(ObjectUtils.isEmpty(member)) { // null이면 true 반환
			return false;
		}
		
		// 2. 비밀번호 비교
		String passwd = params.get("passwd"); // 사용자가 입력한 비밀번호
		String memberPw = member.getPasswd(); // DB에 저장되어 있는 암호화 된 비밀번호 
		Sha512Encoder encoder = Sha512Encoder.getInstance();
		
		String encodeTxt = encoder.getSecurePassword(passwd); // 사용자가 입력한 값을 암호화한 거다 
				
		return StringUtils.pathEquals(memberPw, encodeTxt); // 비교해서 틀리면 false반환
	}
}




package com.feb.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	// 회원가입 페이지
	// http://localhost:8088/spring/loginPage.do
	@GetMapping("/loginPage.do")
	public ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	/* 
	 	회원가입
	 	member_id : 아이디
	 	passwd : 비밀번호
	 	email : 이메일
	 	Phone : 폰번호
	*/
	
	@PostMapping("/join.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("member_id : " + params.get("member_id")); // sinbumjun
		System.out.println("passwd : " + params.get("passwd")); // 123
		System.out.println("email : " + params.get("email")); // sinbumjun123@naver.com
		System.out.println("Phone : " + params.get("Phone")); // 01038736380
		
		int result = memberService.join(params, request);
		System.out.println("result : " +result); // 0 또는 1
		
		if(result==1) {
			mv.addObject("resultMsg", "회원가입 성공");
		}else { // -1이 반환되면 해당 예외 메시지와 같이 전달
			mv.addObject("resultMsg", request.getAttribute("errorMessage")); // result는 에러 메시지
	        // -1이 반환될 때의 추가적인 처리 가능
		}
		
		mv.setViewName("login"); // home.jsp 따로 만들기
		return mv;
	}
	
	/* 
	 	로그인
	 	member_id : 아이디
	 	passwd : 비밀번호
	*/
	@PostMapping("/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		System.out.println("member_id : " + params.get("member_id")); // sinbumjun
		System.out.println("passwd : " + params.get("passwd")); // 123
		
		
		boolean result = memberService.login(params, request); // true 또는 false
		System.out.println("result1111111111111111 : " + result);
			
		if(result) { // result가 true면 로그인 성공 false면 로그인 실패
			mv.addObject("resultMsg", "로그 성공");
			mv.addObject("resultCode", "loginOk");
			mv.setViewName("home"); // 로그인 성공하면 home.jsp
			return mv;
		}else {
			mv.addObject("resultMsg", "로그 실패");
			mv.addObject("resultCode", "loginFail");
			mv.setViewName("login"); // 로그인 실패하면 login.jsp
		}

		return mv;
	}
}




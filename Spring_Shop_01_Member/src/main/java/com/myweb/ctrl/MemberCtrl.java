package com.myweb.ctrl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.domain.MemberVO;
import com.myweb.service.MemberService;


@Controller
@RequestMapping("/member/*")
public class MemberCtrl {
	private static Logger log = LoggerFactory.getLogger(MemberCtrl.class);
	
	@Inject
	MemberService msv;
	
	@GetMapping("/resign")
	public String resign(@RequestParam("email")String email, @RequestParam("ses")String ses_email,RedirectAttributes reAttr, HttpSession ses) {
		int isRm = msv.resign(email);
		if (isRm > 0) {
			if (email.equals(ses_email)) {
				ses.invalidate();
			}
			reAttr.addFlashAttribute("msg", "회원탈퇴가 완료되었습니다");
		}
		return "redirect:/";
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, Model model) {
		int isUp = msv.modify(mvo);
		if (isUp > 0) {
			model.addAttribute("mvo", mvo);
		}
		return "redirect:/member/detail?email="+mvo.getEmail();
	}
	/*
	 * @GetMapping("/modify") public void modify(@RequestParam("email")String email,
	 * Model model) { MemberVO mvo = msv.getInfo(email); model.addAttribute("mvo",
	 * mvo); }
	 */
	
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("email")String email, Model model) {
		MemberVO mvo = msv.getInfo(email);
		model.addAttribute("mvo", mvo);
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		List<MemberVO> list = msv.getList();		
		model.addAttribute("mList", list);
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession ses) {
		ses.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpSession ses) {
		MemberVO mInfo = msv.login(mvo);
		if (mInfo != null) {
			ses.setAttribute("mInfo", mInfo);
			ses.setMaxInactiveInterval(60*30); // 30 Min.
		}
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@ResponseBody
	@GetMapping("/checkDuple")
	public String emailCheck(@RequestParam("email") String email) {
		int isExt = msv.checkDuple(email);
		return isExt == 1 ? "1" : "0";
	}
	
	@GetMapping("/join")	
	public void join() {
	}
	
	@PostMapping("/join")
	public String join(MemberVO mvo) {		
		int isOk = msv.register(mvo);
		log.debug(">>> isOk : "+ isOk);
		log.debug(isOk==1? "Register Success" : "Register Fail");
		return "redirect:/";
	}
}

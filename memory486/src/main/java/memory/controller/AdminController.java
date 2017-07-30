package memory.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import memory.model.Couple;
import memory.model.Member;
import memory.service.AdminService;
import memory.service.CoupleService;
import memory.service.MemberService;

@Controller
public class AdminController {
	@Autowired
	private AdminService as;
	@Autowired
	private CoupleService cs;
	@Autowired
	private MemberService ms;

	@RequestMapping("/adminLoginForm")
	public String loginForm(){
		return "adminPage/adminLoginForm";
	}
	@RequestMapping("/adminlogin")
	public String login(String adminid, String adminpass, 
			HttpSession session, Model model) {
		int result =as.adminlogin(adminid,adminpass);
			if (result>0){
				session.setAttribute("adminid", adminid);
				return "redirect:adminCouple";				
			}
			else 
			model.addAttribute("msg", "아이디 혹은 비밀번호가 맞지 않습니다.");
			return "adminPage/adminLoginForm";
	}
	@RequestMapping("/adminCouple")
	public String adminCouple( Model model){
		List<Couple> list = cs.couplelist();
		model.addAttribute("list", list);		
		return "adminPage/adminCouple";
	}
	@RequestMapping("/adminCouple2")
	public String adminCouple2( Model model){
		List<Couple> list = cs.couplelist2();
		model.addAttribute("list", list);		
		return "adminPage/adminCouple2";
	}
	@RequestMapping("/adminMember/{id}")
	public String diaryshowMy(@PathVariable("id") String id,Model model) {
			
		List<Member> content = ms.content(id);
		model.addAttribute("content", content);
		return "adminPage/adminMember";
	}
	@RequestMapping("/adminMemberList") 
		public String adminMemberList(Model model) {
		List <Member> content = ms.list();
		model.addAttribute("content",content);
		return "adminPage/adminMemberList";
	}
	
	@RequestMapping("/recover")
	public String recover(String id, Model model) {
		int result = ms.recover(id);
		System.out.println(id);
		System.out.println(result);
		model.addAttribute("result",result);
		return "adminPage/recover";
	}
	

	
	
}

package memory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import memory.model.Couple;
import memory.model.Member;
import memory.service.CoupleService;
import memory.service.MemberService;

@Controller
public class CoupleController {
	@Autowired
	private CoupleService cs;
	@Autowired
	private MemberService ms;
	
	@RequestMapping("/couplechk")
	public String couplechk(String coupleid, Model model) {
		int idchk = ms.idchk(coupleid);
		int couplechk = cs.chk(coupleid);
		model.addAttribute("idchk",idchk);
		model.addAttribute("couplechk",couplechk);
		model.addAttribute("ID_B",coupleid);
		return "member/couplechk";
	}
	
	@RequestMapping("/coupleRequest")
	public String coupleRequest(String id, String ID_B, Model model) {
		int result = cs.request(id, ID_B);
		model.addAttribute("id",id);
		model.addAttribute("result",result);
		return "member/coupleRequest";
	}
	
	@RequestMapping("/breakup")
	public String breakup(String id, Model model) {
		int result = cs.delete(id);
		model.addAttribute("id",id);
		model.addAttribute("result",result);
		return "member/breakup";
	} 
	
	//----------------------------------------------------------------------------------------------------------//
	
	@RequestMapping("/couplepositive")
	public String couplepositive(String id, Model model){
		int result = cs.couplepositive(id);
		Couple couple = cs.select(id);
		String yourID = null;
		if(couple != null) {
			if(id.equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
			Member yourmember = ms.select(yourID);
			if(yourmember.getLayout()!=null) {
				String myLayout = null;
					if(yourmember.getLayout().equals("left")) {
						myLayout = "right";
					}else {
						myLayout = "left";
					}
				int result2 = ms.setyourLayout(id,myLayout);
				}
			
		}
		System.out.println(id);
		System.out.println(result);
		model.addAttribute("result",result);
		return "mainpage/coupleend";
	}
	
	@RequestMapping("/couplenagative")
	public String couplenagative(HttpSession session, Model model){
		String id = (String)session.getAttribute("id");
		int result = cs.couplenagative(id);
		model.addAttribute("result",result);
		return "mainpage/couplenegative";
	}
	
}

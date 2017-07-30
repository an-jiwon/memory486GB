package memory.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import memory.model.Couple;
import memory.model.Dday;
import memory.model.Member;
import memory.service.CalendarService;
import memory.service.CoupleService;
import memory.service.MemberService;

@Controller

public class MemberController {
	@Autowired
	private MemberService ms;
	@Autowired
	private CoupleService cs;
	@Autowired
	private CalendarService ccs;
	
	
	@RequestMapping("/sessionloginForm")
	public String sessionloginForm(HttpSession session, Model model){
		String id = (String)session.getAttribute("id");
		model.addAttribute("id",id);
		return "login/sessionloginForm";
	}
	
	
	@RequestMapping("/sessionlogin")
	public String sessionlogin(String memberID, String memberPass, 
			HttpSession session, Model model) {
		int result = ms.login(memberID, memberPass);
			if (result>0) {
				session.setAttribute("id", memberID);
				loginsequence(memberID, model);
			}
			else {
				model.addAttribute("msg", "아이디 혹은 비밀번호가 맞지 않습니다.");
			}
			return "login/sessionloginForm";
	}
	
	@RequestMapping("/updateFormMember")
	public String updateFormMember(String id, Model model) {
		Member member = ms.select(id);
		String msg = null;
		String yourID=null;
		Couple couple = cs.select(id);
		if(couple != null) {
			if(id .equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
			msg = "커플과의 연결을 끊겠습니까?";
		}
		model.addAttribute("yourID",yourID);
		model.addAttribute("msg",msg);
		model.addAttribute("member",member);
		model.addAttribute("couple",couple);
		return "member/updateFormMember";
	}
	
	@RequestMapping(value="/updateMember", method=RequestMethod.POST)
	public String updateMember(Member member, Model model, HttpSession session,
			@RequestParam("upfile")MultipartFile upfile) throws IOException {
		String fileName=upfile.getOriginalFilename();
		int fileSize = (int) upfile.getSize();
		/*mf.transferTo(new File("D:/"+fileName));*/
		String path = session.getServletContext().getRealPath("/resources/upload");
		
		if (null != upfile && upfile.getSize()>0) {
			File dir = new File(path);
			if(!dir.isDirectory()) {
			 dir.mkdirs(); // 위의 경로에 빠진 폴더가 있으면 자동으로 폴더를 생성해주는 메서드
			}
			FileOutputStream fos = new FileOutputStream( path+"/"+fileName);
			fos.write(upfile.getBytes());
			fos.close();
			model.addAttribute("fileName",fileName);
			model.addAttribute("fileSize",fileSize);
		}
		
		member.setM_pic(member.getUpfile().getOriginalFilename());
		
		/*System.out.println(member.getMemberID());
		System.out.println(member.getMemberPass());
		System.out.println(member.getName());
		System.out.println(member.getEmail());
		System.out.println(member.getJoindate());
		System.out.println(member.getM_online());
		System.out.println(member.getM_out());
		System.out.println(member.getLayout());
		System.out.println(member.getM_location());
		System.out.println(member.getM_pic());*/
	    
		int result = ms.update(member);
		Couple couple = cs.select(member.getMemberID());
		String yourID = null;
		if(couple != null) {
			if(member.getMemberID().equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
			if(member.getLayout()!=null) {
				String yourLayout = null;
					if(member.getLayout().equals("left")) {
						yourLayout = "right";
					}else {
						yourLayout = "left";
					}

				int result2 = ms.setyourLayout(yourID,yourLayout);
				System.out.println(result2);
				}
			
		}
		model.addAttribute("result",result);
		model.addAttribute("id",member.getMemberID());
		return "member/updateMember";
	}
	
	@RequestMapping("/deleteMember")
	public String deleteMember(String id, Model model) {
		int result = ms.delete(id);
		model.addAttribute("result",result);
		return "member/deleteMember";
	}
	
	//---------------------------------------------------------------------------------------------------------//
	
		
		/*//login 창
		@RequestMapping("/loginForm")
		public String loginForm(){
			return "login/loginForm";
		}*/
		@RequestMapping("/main")
		public String main(String id, Model model) {
			loginsequence(id, model);
			return "mainpage/main";
		}
		
		@RequestMapping("/login")
		public String login(String memberID, String memberPass, HttpSession session, Model model, Member member) {
			int result = ms.login(memberID, memberPass);
//			String name = ms.getName(memberID);
				if (result>0) {
					session.setAttribute("id", memberID);
					/*session.setAttribute("memberID", memberID);
					model.addAttribute("name", name);
					model.addAttribute("result", result);
					return "main"; //로그인 완료 후 임시로 main으로 이동*/	
					loginsequence(memberID, model);
					return "mainpage/main";
				}
				else {
					model.addAttribute("msg", "아이디 혹은 비밀번호가 맞지 않습니다.");
					return "login/loginMain";
				}
		}
		
		@RequestMapping("/logout")
		public String logout(String id, Model model) {
			int result = ms.logout(id);
			model.addAttribute("result",result);
			return "login/logout";
		}
		
	 	
		@RequestMapping("/maincouplecheck")
		public String maincouplecheck(String id, Model model){
			String coupleid = cs.coupleid(id);
			model.addAttribute("coupleid",coupleid);
			return "mainpage/maincouplecheck";
		}
		@RequestMapping("/main2")
		public String main2(HttpSession session,Model model){
			String id = (String)session.getAttribute("id");	
			loginsequence(id,model);
			return "mainpage/main";
		}
		
		private void loginsequence(String memberID, Model model) {
			ms.online(memberID);
			Member member = ms.select(memberID);
			model.addAttribute("member",member);
			Couple couple = cs.select(memberID);

			String yourID = null;
			
			if(couple != null) {
				if(memberID .equals(couple.getId_A())) {
					yourID = couple.getId_B();
				}else{
					yourID = couple.getId_A();
				}
				Member coupleMember = ms.select(yourID);
				Dday dd = ccs.selectDday(memberID,yourID);
				if(dd != null) {
					int dday = ccs.caldday(dd.getDay_no());
					model.addAttribute("dday",dday);
					model.addAttribute("dd",dd);
				}else
					model.addAttribute("dday",null);
				model.addAttribute("coupleMember",coupleMember);
			}else {
				Dday dd = ccs.selectDday(memberID,yourID);
				if(dd != null) {
					int dday = ccs.caldday(dd.getDay_no());
					model.addAttribute("dday",dday);
					model.addAttribute("dd",dd);
				}else
					model.addAttribute("dday",null);
				model.addAttribute("coupleMember",null);
				
				String acceptchk = cs.acceptchk(memberID);						
				
				//체크되어있고 n이면 
					if (acceptchk==null){
						model.addAttribute("acceptchk",null);		
					}
					else if(acceptchk.equals("n")){
						//로그인한놈이 A인지 B인지 파악하고
						int acceptid = cs.acceptid(memberID);
						//A면 ok찍고 보내고
						if(acceptid>0){									
							acceptchk="ok";	
						}
						//B면 n찍고 보내									
						model.addAttribute("acceptchk", acceptchk);								
					}
				//체크null이면 마이페이지로 안내
					else{
						model.addAttribute("acceptchk",null);							
					}
					
			}
			
			
			
	// 위치 상의 시간 나타내기
			String locName = ms.loc(memberID);
			if (locName.equals("Korea seoul")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Korea gyeonggi")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Korea gangwon")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}		
			if (locName.equals("Korea chungcheong")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}   
			if (locName.equals("Korea jeolla")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Korea jeju")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("China ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Hong_Kong" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Japan ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Japan" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Russia ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/Moscow" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("America ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "America/New_York" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Africa ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Africa/Dakar" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Australia ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Australia/Sydney" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("United Kingdom ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/London" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("France ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/Paris" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			if (locName.equals("Italy ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/Rome" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime", locTime);
			}
			
			
		if (yourID!=null){
			//상대방
//			String coupleid = cs.coupleid(memberID);
			String locName2 = ms.loc2(yourID);
			if (locName2.equals("Korea seoul")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Korea gyeonggi")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Korea gangwon")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}		
			if (locName2.equals("Korea chungcheong")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}   
			if (locName2.equals("Korea jeolla")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Korea jeju")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Seoul" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("China ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Asia/Hong_Kong" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Japan ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Japan" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Russia ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/Moscow" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("America ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "America/New_York" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Africa ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Africa/Dakar" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Australia ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Australia/Sydney" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("United Kingdom ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/London" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("France ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/Paris" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
			if (locName2.equals("Italy ")) {
				GregorianCalendar clsCalendar = new GregorianCalendar( TimeZone.getTimeZone( "Europe/Rome" ) );
				String locTime = String.format( "%04d-%02d-%02d %02d:%02d"
						    , clsCalendar.get( Calendar.YEAR ), clsCalendar.get( Calendar.MONTH ) + 1, clsCalendar.get( Calendar.DAY_OF_MONTH )
						    , clsCalendar.get( Calendar.HOUR_OF_DAY ), clsCalendar.get( Calendar.MINUTE ) );
				model.addAttribute("locTime2", locTime);
			}
		}
			
		}
		
		
		
		@RequestMapping("/joinForm")
		public String joinForm(){
			return "join/joinForm";
		}

		@RequestMapping("/idcheck")
		public String idCheck(String id, Model model) {
			String idCheck = ms.getMemberID(id);
			if (idCheck == null) {
				model.addAttribute("memberID", id);
				model.addAttribute("msg", "사용 가능한 아이디 입니다.");
			} else {
				model.addAttribute("msg", "이미 존재하는 아이디 입니다.");
			}
			return "join/joinForm";
		}

		@RequestMapping("/locationPop")
		public String locationPop() {
			return "join/locationPop";
		}
		
		@RequestMapping("/check_location")
		public String check_location(HttpServletRequest request, Model model){
			String loc1 = request.getParameter("location1");
			String loc2 = request.getParameter("location2");
			model.addAttribute("loc1", loc1);
			model.addAttribute("loc2", loc2);
			return "join/locationPop";
		}
		
		@RequestMapping(value="/join", method=RequestMethod.POST)
		public String joinResult(Member member, Model model) {
			ms.joinMember(member);
			model.addAttribute("member", member);
			return "join/joinResult";
		}
		
	/*	@RequestMapping("/chatWindow")
		public String chatWindow(String memberID, Model model) {
			String name = ms.getName(memberID);
			String memID = ms.getMemberID(memberID);
			model.addAttribute("memberID", memID);
			model.addAttribute("id", memberID);
			model.addAttribute("name", name);

			return "chat/chatWindow";
		}
		*/

		@RequestMapping("/chatWindow")
		public String chatWindow(String memberID, HttpSession session, Model model) {
			Member member = ms.select(memberID);
			model.addAttribute("member",member);
			Couple couple = cs.select(memberID);
			model.addAttribute("couple",couple);
			String yourID = null;
			if(couple != null) {
				if(memberID .equals(couple.getId_A())) {
					yourID = couple.getId_B();
				}else{
					yourID = couple.getId_A();
				}
				Member coupleMember = ms.select(yourID);
				model.addAttribute("coupleMember",coupleMember);
			}else {
				model.addAttribute("coupleMember",null);
				
				String acceptchk = cs.acceptchk(memberID);						
				
				//체크되어있고 n이면 
					if (acceptchk==null){
						model.addAttribute("acceptchk",null);		
					}
					else if(acceptchk.equals("n")){
						//로그인한놈이 A인지 B인지 파악하고
						int acceptid = cs.acceptid(memberID);
						//A면 ok찍고 보내고
						if(acceptid>0){									
							acceptchk="ok";	
						}
						//B면 n찍고 보내									
						model.addAttribute("acceptchk", acceptchk);								
					}
				//체크null이면 마이페이지로 안내
					else{
						model.addAttribute("acceptchk",null);							
					}
					model.addAttribute("msg", "대화 상대가 없습니다.  커플 신청을 해주세요!");
			}
			
			return "chat/chatWindow";
		}
		
		@RequestMapping("/loginMain")
		public String loginMain(){
			return "login/loginMain";
		}
		
}

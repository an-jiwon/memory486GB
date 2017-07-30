package memory.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import memory.model.Calendar;
import memory.model.Couple;
import memory.model.Dday;
import memory.model.Member;
import memory.service.CalendarService;
import memory.service.CoupleService;
import memory.service.MemberService;

@Controller 
public class CalendarController {
	@Autowired
	private CalendarService cs;
	@Autowired
	private MemberService ms;
	@Autowired
	private CoupleService cos;
	
	@RequestMapping(value="/calendarMain")
	public String calendarMain(String id,Model model) {
		Couple couple = cos.select(id);
		String yourID = null;
		if(couple != null) {
			if(id.equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
		}
		List<Calendar> ddayList = cs.getdday(id,yourID);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<ddayList.size();i++){
			ddayList.get(i).setFromText(fmt.format(ddayList.get(i).getFromDate()));	
		}
		model.addAttribute("ddayList",ddayList);
		return "calendar/calendarMain";
	}
	
	@RequestMapping(value="/calendarList", produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String calendarList(String id)  {
		Couple couple = cos.select(id);
		String yourID = null;
		if(couple != null) {
			if(id.equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
		}
		List<Calendar> calendarList = cs.getList(id,yourID); // 우선 json 데이터로 변환할 데이터를 DB에서 추출
		Gson gson = new Gson();
		/*System.out.println(gson.toJson(calendarList));*/
		/*return URLEncoder.encode(gson.toJson(calendarList), "UTF-8");*/
		return gson.toJson(calendarList);
	}
	
	/*@RequestMapping("/insertCalendarForm")
	public String insertCalendarForm() {
		return "calendar/insertCalendarForm";
	}*/
	
	@RequestMapping("/insertCalendarForm")
	public String insertCalendarForm(String date, Model model) {
		
		model.addAttribute("date", date);
		return "calendar/insertCalendarForm";
	}
	
	@RequestMapping(value="/insertCalendar", method=RequestMethod.POST)
	public String insertCalendar(Calendar calendar,  Model model) throws ParseException {
		Member mem  = ms.select(calendar.getC_writer());
		
			calendar.setC_layout(mem.getLayout());
			model.addAttribute("msg",null);
			if(calendar.getToText() == null || calendar.getToText().equals("")) {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				Date fromDate = fmt.parse(calendar.getFromText());
				calendar.setFromDate(fromDate);
			}else {
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a kk:mm",Locale.ENGLISH);
				Date fromDate = fmt.parse(calendar.getFromText());
				Date toDate = fmt.parse(calendar.getToText());
				calendar.setFromDate(fromDate);
				calendar.setToDate(toDate);			
			}
			int result = cs.insert(calendar);
			model.addAttribute("result",result);
			return "calendar/insertCalendar";
		
	}
	
	@RequestMapping("/updateCalendarForm")
	public String updateCalendarForm(int c_no, Model model) {
		Calendar calendar = cs.select(c_no);
		if(calendar.getToDate() == null) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setFromText(fmt.format(calendar.getFromDate()));	
		} else {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a hh:mm",Locale.ENGLISH);
			calendar.setFromText(fmt.format(calendar.getFromDate()));
			calendar.setToText(fmt.format(calendar.getToDate()));
		}
		model.addAttribute("calendar",calendar);
		return "calendar/updateCalendarForm";
	}
	
	@RequestMapping(value="/updateCalendar", method=RequestMethod.POST)
	public String updateCalendar(Calendar calendar, Model model) throws ParseException {
		if(calendar.getToText() == null || calendar.getToText().equals("")) {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			Date fromDate = fmt.parse(calendar.getFromText());
			calendar.setFromDate(fromDate);
		}else {
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a kk:mm",Locale.ENGLISH);
			Date fromDate = fmt.parse(calendar.getFromText());
			Date toDate = fmt.parse(calendar.getToText());
			
			calendar.setFromDate(fromDate);
			calendar.setToDate(toDate);
			System.out.println(calendar.getToText());
			System.out.println(calendar.getToDate());
		}
		int result = cs.update(calendar);
		model.addAttribute("result",result);
		return "calendar/updateCalendar";
	}
	
	@RequestMapping("/deleteCalendar")
	public String deleteCalendar(int c_no, Model model) {
		int result = cs.delete(c_no);
		model.addAttribute("result",result);
		return "calendar/deleteCalendar";
	}
	
	@RequestMapping("/setdday")
	public String setdday(String id, int c_no, Model model) {
		Couple couple = cos.select(id);
		String yourID = null;
		if(couple != null) {
			if(id.equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
		}
		Dday dday = cs.selectDday(id, yourID);
		if(dday != null) {
			int delresult = cs.deleteDday(dday.getDay_no());
		}
		Calendar calendar = cs.select(c_no);
		int result = cs.setdday(calendar);
		model.addAttribute("result",result);
		return "calendar/setdday";
	}
}

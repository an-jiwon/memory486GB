package memory.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import memory.model.Couple;
import memory.model.Diary;
import memory.service.CoupleService;
import memory.service.DiaryService;


@Controller
public class DiaryController {
	@Autowired
	private DiaryService ds;
	@Autowired
	private CoupleService cs;
	
	@RequestMapping("/diary")
	public String diaryall(HttpSession session,String id,Model model) {
			id = (String)session.getAttribute("id");
			List<Diary> list = ds.listall(id);
			model.addAttribute("list", list);
			//요약한내용들 구하기
			String[] contentlist = new String[list.size()];
			for(int i = 0;i<list.size();i++){
				String str = list.get(i).getD_content();
				int leng;
				if (str.length()>15){
					leng = 15;
					contentlist[i] = str.substring(0,leng)+"...";
				}
				else{
					leng=str.length();
					contentlist[i] = str.substring(0,leng);
				}
				
				model.addAttribute("contentlist", contentlist);
			}			
			//요일구하기
			String[] datelist = new String[list.size()];
			for(int i = 0;i<list.size();i++){				
				Date from= list.get(i).getD_date();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String to = transFormat.format(from);
				try {
					String result = getDateDay(to,"yyyy-MM-dd");
					datelist[i]= result;
					
				} catch (Exception e) {
					e.getMessage();
				}						
			}
			model.addAttribute("datelist", datelist);

		return "diary/diary";
	}
	@RequestMapping("/diary/{no}")
	public String diarymonth(@PathVariable("no") int no, HttpSession session,String id,Model model) {
		id = (String)session.getAttribute("id");			
		List<Diary> list = ds.listall(id,no);
		model.addAttribute("no",no);
		model.addAttribute("list", list);
		
		//요약한내용들 구하기
		String[] contentlist = new String[list.size()];
		for(int i = 0;i<list.size();i++){
			String str = list.get(i).getD_content();
			int leng;
			if (str.length()>15){
				leng = 15;
				contentlist[i] = str.substring(0,leng)+"...";
			}
			else{
				leng=str.length();
				contentlist[i] = str.substring(0,leng);
			}				
			model.addAttribute("contentlist", contentlist);
		}			
		//요일구하기
		String[] datelist = new String[list.size()];
		for(int i = 0;i<list.size();i++){				
			Date from= list.get(i).getD_date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String to = transFormat.format(from);
			try {
				String result = getDateDay(to,"yyyy-MM-dd");
				datelist[i]= result;
				
			} catch (Exception e) {
				e.getMessage();
			}						
		}
		model.addAttribute("datelist", datelist);

	return "diary/diary";
	}
	@RequestMapping("/diaryall")
	public String diaryall2(HttpSession session,String id,Model model) {
			id = (String)session.getAttribute("id");			
			List<Diary> list = ds.listall(id);
			model.addAttribute("list", list);	
			//요약한내용들 구하기
			String[] contentlist = new String[list.size()];
			for(int i = 0;i<list.size();i++){
				String str = list.get(i).getD_content();
				int leng;
				if (str.length()>15){
					leng = 15;
					contentlist[i] = str.substring(0,leng)+"...";
				}
				else{
					leng=str.length();
					contentlist[i] = str.substring(0,leng);
				}				
				model.addAttribute("contentlist", contentlist);
			}
			String coupleid=null;
			Couple couple = cs.select(id);
			if(couple != null) {
				if(id .equals(couple.getId_A())) {
					coupleid = couple.getId_B();
				}else{
					coupleid = couple.getId_A();
				}
			}
			model.addAttribute("coupleid",coupleid);
			//요일구하기			
			String[] datelist = new String[list.size()];
			for(int i = 0;i<list.size();i++){				
				Date from= list.get(i).getD_date();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String to = transFormat.format(from);
				try {
					String result = getDateDay(to,"yyyy-MM-dd");
					datelist[i]= result;
					
				} catch (Exception e) {
					e.getMessage();
				}						
			}
			model.addAttribute("datelist", datelist);

			return "diary/diary";
	}
	
	@RequestMapping("/diarymy")
	public String diarymy(HttpSession session,String id,Model model) {
			id = (String)session.getAttribute("id");
			List<Diary> list = ds.list(id);
			model.addAttribute("list", list);
			//요약한내용들 구하기
			String[] contentlist = new String[list.size()];
			for(int i = 0;i<list.size();i++){
				String str = list.get(i).getD_content();
				int leng;
				if (str.length()>15){
					leng = 15;
					contentlist[i] = str.substring(0,leng)+"...";
				}
				else{
					leng=str.length();
					contentlist[i] = str.substring(0,leng);
				}				
				model.addAttribute("contentlist", contentlist);
			}
			//요일구하기
			String[] datelist = new String[list.size()];
			for(int i = 0;i<list.size();i++){				
				Date from= list.get(i).getD_date();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String to = transFormat.format(from);
				try {
					String result = getDateDay(to,"yyyy-MM-dd");
					datelist[i]= result;
				
				} catch (Exception e) {
					e.getMessage();
				}						
			}
			model.addAttribute("datelist", datelist);

		return "diary/diary";
	}
	
	@RequestMapping("/diaryyour")
	public String diaryyour(HttpSession session,String id,Model model) {
			id = (String)session.getAttribute("id");
			String coupleid=null;
			model.addAttribute("id",id);
			
			Couple couple = cs.select(id);
			if(couple != null) {
				if(id .equals(couple.getId_A())) {
					coupleid = couple.getId_B();
				}else{
					coupleid = couple.getId_A();
				}
			}
			model.addAttribute("coupleid",coupleid);
			List<Diary> list = ds.list(coupleid);
			model.addAttribute("list", list);
			
			//요약한내용들 구하기
			String[] contentlist = new String[list.size()];
			for(int i = 0;i<list.size();i++){
				String str = list.get(i).getD_content();
				int leng;
				if (str.length()>15){
					leng = 15;
					contentlist[i] = str.substring(0,leng)+"...";
				}
				else{
					leng=str.length();
					contentlist[i] = str.substring(0,leng);
				}				
				model.addAttribute("contentlist", contentlist);
			}
			
			//요일구하기
			String[] datelist = new String[list.size()];
			for(int i = 0;i<list.size();i++){				
				Date from= list.get(i).getD_date();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				String to = transFormat.format(from);
				try {
					String result = getDateDay(to,"yyyy-MM-dd");
					datelist[i]= result;
				
				} catch (Exception e) {
					e.getMessage();
				}						
			}
			model.addAttribute("datelist", datelist);
			
		return "diary/diary";
	}
	@RequestMapping("/diaryWriteForm")
	public String diaryWriteForm(String resultMsg, Model model){		
		if(resultMsg != null) {
			model.addAttribute("resultMsg",resultMsg);
		}
		return "diary/diaryWriteForm";
	}
	@RequestMapping("/diaryWrite")
	public String diaryWrite(HttpServletRequest request,HttpSession session,String id,String emotion, MultipartFile file,Model model,String location, String content,String year,String month,String day) throws Exception{//연도 월 일 , 위치,사진 동영상파일,main을 매개변수지정
		//그뭐야날짜설정
		
		String olddate = year+"-"+ month +"-"+day+" 00:00:00";		
		java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(olddate);;
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
	    //파일저장	    

	    if (!file.isEmpty()) {
	    	String savePath = session.getServletContext().getRealPath("/resources/diaryimg"); // 파일이 저장될 프로젝트 안의 폴더 경로
		    System.out.println(savePath);
		    
		    String originalFilename = file.getOriginalFilename(); // fileName.jpg
		    String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
		    String extension = originalFilename.substring(originalFilename.indexOf(".")); // .jpg
		     
		    String rename = onlyFileName + "_" + getCurrentDayTime() + extension; // fileName_20150721-14-07-50.jpg
		    String fullPath = savePath + "\\" + rename;
	    	File dir = new File(savePath);
			if(!dir.isDirectory()) {
			 dir.mkdirs(); // 위의 경로에 빠진 폴더가 있으면 자동으로 폴더를 생성해주는 메서드
			}
	        try {
	            byte[] bytes = file.getBytes();
	            System.out.println("바이트"+bytes);
	            System.out.println("패thㅡ"+fullPath);
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
	            System.out.println("패thㅡ"+fullPath);
	            stream.write(bytes);
	            stream.close();
	            model.addAttribute("resultMsg", "파일을 업로드 성공!");
	            System.out.println("성공");
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            model.addAttribute("resultMsg", "파일을 업로드하는 데에 실패했습니다.");
	            System.out.println("ㄴㄴ..");
	            return "diary/diaryWriteForm";
	        }
	        String emoticon=emotion;
	        Diary diary = new Diary();		
			diary.setD_writer(id);
			System.out.println("id"+id);
			diary.setD_file(rename);
			System.out.println("rename"+rename);
			diary.setD_location(location);
			System.out.println("location"+location);
			diary.setD_content(content);
			System.out.println("content"+content);
			diary.setD_date(sqlDate);
			
			System.out.println("emotion"+emotion);			
			if("".equals(emoticon)||emoticon==""||emoticon.equals("")){
			diary.setD_emotion("none.jpg");
			System.out.println("ifemotion"+emotion);
			}else{
			diary.setD_emotion(emotion);
			System.out.println("elseemotion"+emotion);
			}
			int result =  ds.insert(diary);
			
			System.out.println(result);
			
			if (result>0){
				model.addAttribute("result",result);			
				return "redirect:diaryall";
			}
			else
			{
				return "diary/diaryWriteForm";
			}	    
	    
	    } else {
	        model.addAttribute("resultMsg", "업로드할 파일을 선택해주시기 바랍니다.");
	        return "diary/diaryWriteForm";
	    }  
	}
	
	@RequestMapping("/diaryshow/{no}")
	public String diaryshowMy(@PathVariable("no") int no, HttpSession session,Model model) {
			
		List<Diary> content = ds.content(no);
		model.addAttribute("content", content);
		
		return "diary/diaryshow";
	}
	@RequestMapping("/diaryWriteFormsub")
	public String diaryWriteFormsub() {		
		return "diary/diaryWriteFormsub";
	}
	
	@RequestMapping("/diarydelete/{no}")
	public String diarydelete(@PathVariable("no") int no, HttpSession session,Model model) {
			
		ds.delete(no);
		
		return "redirect:../diaryall";
	}
	@RequestMapping("/update/{no}")
	public String diaryUpdateForm(@PathVariable("no") int no, String resultMsg, HttpSession session,Model model) {
		List<Diary> content = ds.content(no);
		model.addAttribute("content", content);
		if(resultMsg != null) {
			model.addAttribute("resultMsg",resultMsg);
		}
		String date = content.get(0).getD_date().toString();
		String month = date.substring(5,7);
		String day = date.substring(8);
		model.addAttribute("month",month);
		model.addAttribute("day",day);
		return "diary/diaryUpdateForm";
	}
	@RequestMapping("/diaryUpdate")
	public String diaryUpdate(HttpServletRequest request,HttpSession session,String id,String emotion, int d_no, MultipartFile file,Model model,String location, String content,String year,String month,String day) throws Exception{//연도 월 일 , 위치,사진 동영상파일,main을 매개변수지정
		//그뭐야날짜설정
		String olddate = year+"-"+ month +"-"+day+" 00:00:00";		
		java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(olddate);
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
	    //파일저장
	    if (!file.isEmpty()) {
	    	String savePath = session.getServletContext().getRealPath("/resources/diaryimg"); // 파일이 저장될 프로젝트 안의 폴더 경로
		    System.out.println(savePath);
		    
		    String originalFilename = file.getOriginalFilename(); // fileName.jpg
		    String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
		    String extension = originalFilename.substring(originalFilename.indexOf(".")); // .jpg
		     
		    String rename = onlyFileName + "_" + getCurrentDayTime() + extension; // fileName_20150721-14-07-50.jpg
		    String fullPath = savePath + "\\" + rename;
	    	File dir = new File(savePath);
			if(!dir.isDirectory()) {
			 dir.mkdirs(); // 위의 경로에 빠진 폴더가 있으면 자동으로 폴더를 생성해주는 메서드
			}
	        try {
	            byte[] bytes = file.getBytes();
	            System.out.println("바이트"+bytes);
	            System.out.println("패thㅡ"+fullPath);
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
	            System.out.println("패thㅡ"+fullPath);
	            stream.write(bytes);
	            stream.close();
	            model.addAttribute("resultMsg", "파일을 업로드 성공!");
	            System.out.println("성공");
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            model.addAttribute("resultMsg", "파일을 업로드하는 데에 실패했습니다.");
	            System.out.println("ㄴㄴ..");
	            return "redirect:update/"+d_no;
	        }
	        System.out.println(d_no);
	        String emoticon=emotion;
	        Diary diary = new Diary();
	        diary.setD_no(d_no);
			diary.setD_writer(id);
			System.out.println("id"+id);
			diary.setD_file(rename);
			System.out.println("rename"+rename);
			diary.setD_location(location);
			System.out.println("location"+location);
			diary.setD_content(content);			
			System.out.println("content"+content);
			diary.setD_date(sqlDate);
			
			System.out.println("emotion"+emotion);			
			if("".equals(emoticon)||emoticon==""||emoticon.equals("")){
			diary.setD_emotion("none.jpg");
			System.out.println("ifemotion"+emotion);
			}else{
			diary.setD_emotion(emotion);
			System.out.println("elseemotion"+emotion);
			}
			int result =  ds.update(diary);
			
			System.out.println(result);
			
			if (result>0){
				model.addAttribute("result",result);			
				return "redirect:diaryall";
			}
			else
			{
				return "redirect:update/"+d_no;
			}	    
	    
	    } else {
	        model.addAttribute("resultMsg", "업로드할 파일을 선택해주시기 바랍니다.");
	        return "redirect:update/"+d_no;
	    }  
	}

	
	public String getDateDay(String date, String dateType) throws Exception{
	    String day = "" ;
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
	    Date nDate = dateFormat.parse(date) ;
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
	    switch(dayNum){
	        case 1:
	            day = "일요일";
	            break ;
	        case 2:
	            day = "월요일";
	            break ;
	        case 3:
	            day = "화요일";
	            break ;
	        case 4:
	            day = "수요일";
	            break ;
       		case 5:
	            day = "목요일";
	            break ;
	        case 6:
	            day = "금요일";
	            break ;
	        case 7:
	            day = "토요일";
	            break ;
	    }return day ;
	}
	
	//파일이름중복피하기
	public String getCurrentDayTime(){
	    long time = System.currentTimeMillis();
	    SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd-HH-mm-ss", Locale.KOREA);
	    return dayTime.format(new Date(time));
	}
	
}





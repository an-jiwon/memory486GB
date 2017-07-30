package memory.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import memory.model.Couple;
import memory.model.Member;
import memory.model.Photo;
import memory.model.Profile;
import memory.service.CoupleService;
import memory.service.MemberService;
import memory.service.PhotoService;
import memory.service.ProfileService;

@Controller
public class PhotoController {
	@Autowired
	private PhotoService ps;
	@Autowired
	private ProfileService pps;
	@Autowired
	private MemberService ms;
	@Autowired
	private CoupleService cs;
	
	@RequestMapping("/photoListMain")
	public String photoListMain(String id) {
		
		return "redirect:/photoList?id="+id+"&searchYear=0&searchMonth=0";
	}
	
	@RequestMapping("/photoList")
	public String photoList(String id, String searchYear, String searchMonth, String mypageNum,String yourpageNum, Model model) {
		
		final int ROWPERPAGE = 5;
		final int PAGEPERBLOCK = 5;
		
		int year = Integer.parseInt(searchYear);
		int month =  Integer.parseInt(searchMonth);
				
		if(mypageNum == null || mypageNum.equals(""))
			mypageNum="1";
		int mycurrentPage = Integer.parseInt(mypageNum);
		int mytotal   = ps.getTotal(id,year,month);
		int mystartRow = (mycurrentPage - 1)*ROWPERPAGE + 1;
		int myendRow = mystartRow + ROWPERPAGE-1;
		
		int mytotPage = (int)Math.ceil((double)mytotal/ROWPERPAGE);
		int mystartPage = mycurrentPage-(mycurrentPage-1)%PAGEPERBLOCK;
		int myendPage = mystartPage + PAGEPERBLOCK - 1;
		if(myendPage > mytotPage)
			myendPage = mytotPage;
		
		Member member = ms.select(id);
		Couple couple = cs.select(id);
		String yourID = null;
		List<Photo> myphoto = ps.list(id,year,month,mystartRow, myendRow);
		Profile myprofile = pps.select(id);
		if(couple != null) {
			if(id .equals(couple.getId_A())) {
				yourID = couple.getId_B();
			}else{
				yourID = couple.getId_A();
			}
			if(yourpageNum == null || yourpageNum.equals(""))
				yourpageNum="1";
			int yourcurrentPage = Integer.parseInt(yourpageNum);
			int yourtotal   = ps.getTotal(yourID,year,month);
			int yourstartRow = (yourcurrentPage - 1)*ROWPERPAGE + 1;
			int yourendRow = yourstartRow + ROWPERPAGE-1;
			
			int yourtotPage = (int)Math.ceil((double)yourtotal/ROWPERPAGE);
			int yourstartPage = yourcurrentPage-(yourcurrentPage-1)%PAGEPERBLOCK;
			int yourendPage = yourstartPage + PAGEPERBLOCK - 1;
			if(yourendPage > yourtotPage)
				yourendPage = yourtotPage;
			List<Photo> yourphoto = ps.list(yourID,year,month,yourstartRow, yourendRow);
			Profile yourprofile = pps.select(yourID);
			List<String> yearlist = ps.yearList(id,yourID);
			
			model.addAttribute("yourpageNum", yourpageNum);
			model.addAttribute("yourcurrentPage", yourcurrentPage);
			model.addAttribute("yourtotPage", yourtotPage);
			model.addAttribute("yourstartPage", yourstartPage);
			model.addAttribute("yourendPage", yourendPage);
					
			model.addAttribute("yearlist",yearlist);
			model.addAttribute("yourphoto",yourphoto);
			model.addAttribute("yourprofile",yourprofile);
	
		}else {
			List<String> yearlist = ps.yearList(id,yourID);
			model.addAttribute("yearlist",yearlist);
			model.addAttribute("yourphoto",null);
			model.addAttribute("yourprofile",null);
		}
		
		model.addAttribute("mypageNum", mypageNum);
		model.addAttribute("mycurrentPage", mycurrentPage);
		model.addAttribute("PAGEPERBLOCK", PAGEPERBLOCK);
		model.addAttribute("mytotPage", mytotPage);
		model.addAttribute("mystartPage", mystartPage);
		model.addAttribute("myendPage", myendPage);
		
		model.addAttribute("searchYear",searchYear);
		model.addAttribute("searchMonth",searchMonth);
		model.addAttribute("member",member);
		model.addAttribute("couple",couple);
		model.addAttribute("myphoto",myphoto);
		model.addAttribute("myprofile",myprofile);
		return "photo/photoList";
	}
	
	@RequestMapping("/insertPhotoForm")
	public String insertPhotoForm() {
		return "photo/insertPhotoForm";
	}
	
	@RequestMapping(value="/insertPhoto", method=RequestMethod.POST)
	public String insertPhoto(Photo photo, Model model,	 HttpSession session,
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
		photo.setP_file(fileName);
		photo.setP_writer((String)session.getAttribute("id"));
		
		int result = ps.insert(photo);
		model.addAttribute("result",result);
		
		return "photo/insertPhoto";
	}
	
	@RequestMapping("/modifyPhotoForm")
	public String modifyPhotoForm (int p_no, Model model) {
		model.addAttribute("p_no",p_no);
		
		return "photo/modifyPhotoForm";
	}
	
	@RequestMapping("/updatePhotoForm")
	public String updatePhotoForm(int p_no, Model model) {
		Photo photo = ps.select(p_no);
		model.addAttribute("photo",photo);
		return "photo/updatePhotoForm";
	}
	
	@RequestMapping(value="/updatePhoto", method=RequestMethod.POST)
	public String updatePhoto(Photo photo, Model model,  HttpSession session,
			@RequestParam("upfile")MultipartFile upfile ) throws IOException {
		
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
		photo.setP_file(fileName);
		photo.setP_writer((String)session.getAttribute("id"));
		
		int result = ps.update(photo);
		model.addAttribute("result",result);
		return "photo/updatePhoto";
	}
	
	@RequestMapping("/deletePhoto")
	public String deletePhoto(int p_no, Model model) {
		int result = ps.delete(p_no);
		model.addAttribute("result",result);
		return "photo/deletePhoto";
	}
	
	
	
}

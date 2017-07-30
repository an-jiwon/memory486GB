package memory.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import memory.model.Profile;
import memory.service.ProfileService;

@Controller
public class ProfileController {
	@Autowired
	private ProfileService pps;

	@RequestMapping("/insertProfileForm")
	public String insertProfileForm () {	
		return "photo/insertProfileForm";
	}
	
	@RequestMapping(value="/insertProfile", method=RequestMethod.POST)
	public String insertProfile(Profile profile, Model model,  HttpSession session,
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
		profile.setPro_title(fileName);
		profile.setPro_writer((String)session.getAttribute("id"));
		
		int result = pps.insert(profile);
		model.addAttribute("result",result);
		return "photo/insertProfile";
	}
	
	@RequestMapping("/modifyProfileForm")
	public String modifyProfileForm(int pro_no, Model model) {
		model.addAttribute("pro_no",pro_no);
		return "photo/modifyProfileForm";
	}
	
	@RequestMapping("/updateProfileForm")
	public String updateProfileForm(int pro_no, Model model) {
		Profile profile = pps.getProfile(pro_no);
		model.addAttribute("profile",profile);
		return "photo/updateProfileForm";
	}
	
	@RequestMapping(value="/updateProfile", method=RequestMethod.POST)
	public String updateProfile(Profile profile,Model model,  HttpSession session,
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
		profile.setPro_title(fileName);
		profile.setPro_writer((String)session.getAttribute("id"));
		
		int result = pps.update(profile);
		model.addAttribute("result",result);
		return "photo/updateProfile";
	}
	
	@RequestMapping("/deleteProfile")
	public String deleteProfile(int pro_no, Model model) {
		int result = pps.delete(pro_no);
		model.addAttribute("result",result);
		return "photo/deleteProfile";
	}
}

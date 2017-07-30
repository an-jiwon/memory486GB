package memory.model;

import org.springframework.web.multipart.MultipartFile;

public class Profile {
	private int pro_no;
	private String pro_title;
	private String pro_writer;
	private String pro_divide;
	private MultipartFile upfile;
	
	public int getPro_no() {
		return pro_no;
	}
	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}
	public String getPro_title() {
		return pro_title;
	}
	public void setPro_title(String pro_title) {
		this.pro_title = pro_title;
	}
	public String getPro_writer() {
		return pro_writer;
	}
	public void setPro_writer(String pro_writer) {
		this.pro_writer = pro_writer;
	}
	public String getPro_divide() {
		return pro_divide;
	}
	public void setPro_divide(String pro_divide) {
		this.pro_divide = pro_divide;
	}
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}
	
}

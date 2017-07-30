package memory.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Photo {
	 private int p_no;
	 private String p_file;
	 private String p_writer;
	 private Date p_date;
	 private String p_delete;
	 private MultipartFile upfile;
	public int getP_no() {
		return p_no;
	}
	public void setP_no(int p_no) {
		this.p_no = p_no;
	}
	public String getP_file() {
		return p_file;
	}
	public void setP_file(String p_file) {
		this.p_file = p_file;
	}
	public String getP_writer() {
		return p_writer;
	}
	public void setP_writer(String p_writer) {
		this.p_writer = p_writer;
	}
	public Date getP_date() {
		return p_date;
	}
	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}
	public String getP_delete() {
		return p_delete;
	}
	public void setP_delete(String p_delete) {
		this.p_delete = p_delete;
	}
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}
	

}

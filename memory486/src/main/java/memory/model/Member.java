package memory.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String memberID;
	private String memberPass;
	private String name;
	private String email;
	private Date joindate;
	private String m_online;
	private String m_out;
	private String layout;
	private String m_location;
	private String m_pic;
	private MultipartFile upfile;

	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberPass() {
		return memberPass;
	}
	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public String getM_online() {
		return m_online;
	}
	public void setM_online(String m_online) {
		this.m_online = m_online;
	}
	public String getM_out() {
		return m_out;
	}
	public void setM_out(String m_out) {
		this.m_out = m_out;
	}
	public String getLayout() {
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	public String getM_location() {
		return m_location;
	}
	public void setM_location(String m_location) {
		this.m_location = m_location;
	}
	public String getM_pic() {
		return m_pic;
	}
	public void setM_pic(String m_pic) {
		this.m_pic = m_pic;
	}
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}
	
	
}

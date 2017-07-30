package memory.model;
import java.sql.Date;

public class Diary {
	private int d_no;
	private String d_writer;
	private Date d_date;
	private String d_content;
	private String d_file;
	private String d_location;
	private String d_emotion;
	private String d_delete;
	
	public int getD_no() {
		return d_no;
	}
	public void setD_no(int d_no) {
		this.d_no = d_no;
	}
	public String getD_writer() {
		return d_writer;
	}
	public void setD_writer(String d_writer) {
		this.d_writer = d_writer;
	}
	public Date getD_date() {
		return d_date;
	}
	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}
	public String getD_content() {
		return d_content;
	}
	public void setD_content(String d_content) {
		this.d_content = d_content;
	}
	public String getD_file() {
		return d_file;
	}
	public void setD_file(String d_file) {
		this.d_file = d_file;
	}
	public String getD_location() {
		return d_location;
	}
	public void setD_location(String d_location) {
		this.d_location = d_location;
	}
	public String getD_emotion() {
		return d_emotion;
	}
	public void setD_emotion(String d_emotion) {
		this.d_emotion = d_emotion;
	}
	public String getD_delete() {
		return d_delete;
	}
	public void setD_delete(String d_delete) {
		this.d_delete = d_delete;
	}

}
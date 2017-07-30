package memory.model;
import java.util.Date;

public class Calendar {
	private int c_no;
	private String c_writer;
	private String c_layout;
	private String c_content;
	private String c_category;
	private Date fromDate;
	private Date toDate;
	private String fromText;
	private String toText;
	
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public String getC_writer() {
		return c_writer;
	}
	public void setC_writer(String c_writer) {
		this.c_writer = c_writer;
	}
	public String getC_layout() {
		return c_layout;
	}
	public void setC_layout(String c_layout) {
		this.c_layout = c_layout;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public String getC_category() {
		return c_category;
	}
	public void setC_category(String c_category) {
		this.c_category = c_category;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getFromText() {
		return fromText;
	}
	public void setFromText(String fromText) {
		this.fromText = fromText;
	}
	public String getToText() {
		return toText;
	}
	public void setToText(String toText) {
		this.toText = toText;
	}

	
	
}

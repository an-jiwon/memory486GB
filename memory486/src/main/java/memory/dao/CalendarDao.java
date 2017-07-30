package memory.dao;

import java.util.ArrayList;
import java.util.List;

import memory.model.Calendar;
import memory.model.Dday;

public interface CalendarDao {

	int insert(Calendar calendar);

	List<Calendar> getList(String id, String yourID);

	Calendar select(int c_no);

	int update(Calendar calendar);

	int delete(int c_no);

	List<Calendar> getdday(String id, String yourID);

	int setdday(Calendar calendar);

	Dday selectDday(String memberID, String yourID);

	int deleteDday(int day_no);

	int caldday(int day_no);

}

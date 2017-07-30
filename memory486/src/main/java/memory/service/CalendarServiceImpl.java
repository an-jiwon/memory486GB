package memory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.CalendarDao;
import memory.model.Calendar;
import memory.model.Dday;

@Service
public class CalendarServiceImpl implements CalendarService{
	@Autowired
	private CalendarDao cd;

	@Override
	public int insert(Calendar calendar) {
		// TODO Auto-generated method stub
		return cd.insert(calendar);
	}

	@Override
	public List<Calendar> getList(String id,String yourID) {
		// TODO Auto-generated method stub
		return cd.getList(id,yourID);
	}

	@Override
	public Calendar select(int c_no) {
		// TODO Auto-generated method stub
		return cd.select(c_no);
	}

	@Override
	public int update(Calendar calendar) {
		// TODO Auto-generated method stub
		return cd.update(calendar);
	}

	@Override
	public int delete(int c_no) {
		// TODO Auto-generated method stub
		return cd.delete(c_no);
	}

	@Override
	public List<Calendar> getdday(String id, String yourID) {
		// TODO Auto-generated method stub
		return cd.getdday(id,yourID);
	}

	@Override
	public int setdday(Calendar calendar) {
		// TODO Auto-generated method stub
		return cd.setdday(calendar);
	}

	@Override
	public Dday selectDday(String memberID, String yourID) {
		// TODO Auto-generated method stub
		return cd.selectDday(memberID,yourID);
	}

	@Override
	public int deleteDday(int day_no) {
		return cd.deleteDday(day_no);
		// TODO Auto-generated method stub
		
	}

	@Override
	public int caldday(int day_no) {
		// TODO Auto-generated method stub
		return cd.caldday(day_no);
	}
}

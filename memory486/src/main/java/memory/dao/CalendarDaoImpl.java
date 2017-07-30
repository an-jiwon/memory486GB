package memory.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import memory.model.Calendar;
import memory.model.Dday;

@Repository
public class CalendarDaoImpl implements CalendarDao{
@Autowired
private SqlSessionTemplate st;

@Override
public int insert(Calendar calendar) {
	// TODO Auto-generated method stub
	return st.insert("calendarns.insert",calendar);
}

@Override
public List<Calendar> getList(String id,String yourID) {
	HashMap hashmap = new HashMap();
	hashmap.put("id",id);
	hashmap.put("yourID", yourID);
	return st.selectList("calendarns.list",hashmap);
}

@Override
public Calendar select(int c_no) {
	// TODO Auto-generated method stub
	return st.selectOne("calendarns.select",c_no);
}

@Override
public int update(Calendar calendar) {
	// TODO Auto-generated method stub
	return st.update("calendarns.update",calendar);
}

@Override
public int delete(int c_no) {
	// TODO Auto-generated method stub
	return st.delete("calendarns.delete",c_no);
}

@Override
public List<Calendar> getdday(String id, String yourID) {
	HashMap hashmap = new HashMap();
	hashmap.put("id",id);
	hashmap.put("yourID", yourID);
	return st.selectList("calendarns.getdday",hashmap);
}
 
@Override
public int setdday(Calendar calendar) {
	// TODO Auto-generated method stub
	return st.insert("ddayns.setdday",calendar);
}

@Override
public Dday selectDday(String memberID, String yourID) {
	HashMap hashmap = new HashMap();
	hashmap.put("memberID",memberID);
	hashmap.put("yourID", yourID);
	return st.selectOne("ddayns.select",hashmap);
}

@Override
public int deleteDday(int day_no) {

	return st.delete("ddayns.deleteDday",day_no);
}

@Override
public int caldday(int day_no) {
	// TODO Auto-generated method stub
	return st.selectOne("ddayns.caldday",day_no);
}
}

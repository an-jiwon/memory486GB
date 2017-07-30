package memory.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import memory.model.Photo;

@Repository
public class PhotoDaoImpl implements PhotoDao{
@Autowired
private SqlSessionTemplate st;

@Override
public List<Photo> list(String id,int year,int month, int startRow, int endRow) {
	HashMap hashmap = new HashMap();
	hashmap.put("id", id);
	hashmap.put("year", year);
	hashmap.put("month", month);
	hashmap.put("startRow", startRow);
	hashmap.put("endRow", endRow);
	return st.selectList("photons.list",hashmap);
}

@Override
public int insert(Photo photo) {
	// TODO Auto-generated method stub
	return st.insert("photons.insert",photo);
}

@Override
public List<String> yearList(String id, String yourID) {
	// TODO Auto-generated method stub
	HashMap hashmap = new HashMap();
	hashmap.put("id", id);
	hashmap.put("yourID", yourID);
	return st.selectList("photons.yearList",hashmap);
}

@Override
public int getTotal(String id, int year, int month) {
	HashMap hashmap = new HashMap();
	hashmap.put("id", id);
	hashmap.put("year", year);
	hashmap.put("month", month);
	return st.selectOne("photons.getTotal",hashmap);
}

@Override
public Photo select(int p_no) {
	// TODO Auto-generated method stub
	return st.selectOne("photons.select",p_no);
}

@Override
public int update(Photo photo) {
	// TODO Auto-generated method stub
	return st.update("photons.update",photo);
}

@Override
public int delete(int p_no) {
	// TODO Auto-generated method stub
	return st.update("photons.delete",p_no);
}
}

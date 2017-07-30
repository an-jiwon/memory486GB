package memory.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import memory.model.Diary;
import oracle.net.aso.h;

@Repository
public class DiaryDaoImpl implements DiaryDao {
	@Autowired
	private SqlSessionTemplate st;

	@Override
	public int insert(Diary diary) {		
		return st.insert("diaryns.insert",diary);
	}

	@Override
	public List<Diary> content(int no) {
		// TODO Auto-generated method stub
		return st.selectList("diaryns.content",no);
	}

	@Override
	public List<Diary> list(String id) {
		// TODO Auto-generated method stub
		return st.selectList("diaryns.list",id);
	}

	@Override
	public List<Diary> listall(String id) {
		// TODO Auto-generated method stub
	
		return st.selectList("diaryns.listall",id);
	}

	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return st.update("diaryns.delete",no);
	}

	@Override
	public List<Diary> listall(String id, int no) {
		// TODO Auto-generated method stub
		String num;
		if(no<10)
			num = "0"+String.valueOf(no);
		else
			num = String.valueOf(no);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id );
		map.put("num", num);
		List<Diary> list = st.selectList("diaryns.listallmonth",map);
		
		return list;

	}

	@Override
	public int update(Diary diary) {
		// TODO Auto-generated method stub
		return st.update("diaryns.update",diary);
	}

}

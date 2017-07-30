package memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.DiaryDao;
import memory.model.Diary;

@Service
public class DiaryServiceImpl implements DiaryService{
	@Autowired
	private DiaryDao dd;


	@Override
	public int insert(Diary diary) {
		// TODO Auto-generated method stub
		return dd.insert(diary);
	}


	@Override
	public List<Diary> content(int no) {
		// TODO Auto-generated method stub
		return dd.content(no);
	}


	@Override
	public List<Diary> list(String id) {
		// TODO Auto-generated method stub
		return dd.list(id);
	}


	public List<Diary> listall(String id) {
		// TODO Auto-generated method stub
		return dd.listall(id);
	}


	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return dd.delete(no);
	}


	@Override
	public List<Diary> listall(String id, int no) {
		// TODO Auto-generated method stub
		return dd.listall(id,no);
	}


	@Override
	public int update(Diary diary) {
		// TODO Auto-generated method stub
		return dd.update(diary);
	}

	
}

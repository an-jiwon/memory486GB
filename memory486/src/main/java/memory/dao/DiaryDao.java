package memory.dao;

import java.util.List;

import memory.model.Diary;

public interface DiaryDao {
	int insert(Diary diary);

	List<Diary> content(int no);

	List<Diary> list(String id);

	List<Diary> listall(String id);

	int delete(int no);

	List<Diary> listall(String id, int no);

	int update(Diary diary);

}

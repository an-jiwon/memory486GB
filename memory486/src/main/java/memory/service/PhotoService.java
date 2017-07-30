package memory.service;

import java.util.List;

import memory.model.Photo;

public interface PhotoService {


	int insert(Photo photo);

	List<Photo> list(String id, int year, int month, int startRow, int endRow);

	List<String> yearList(String id, String yourID);

	int getTotal(String id, int year, int month);

	Photo select(int p_no);

	int update(Photo photo);

	int delete(int p_no);



}

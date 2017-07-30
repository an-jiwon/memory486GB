package memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.PhotoDao;
import memory.model.Photo;

@Service
public class PhotoServiceImpl implements PhotoService{
	@Autowired
	private PhotoDao pd;


	@Override
	public int insert(Photo photo) {
		// TODO Auto-generated method stub
		return pd.insert(photo);
	}

	@Override
	public List<Photo> list(String id, int year, int month, int startRow, int endRow) {
		// TODO Auto-generated method stub
		return pd.list(id,year,month,startRow,endRow);
	}

	@Override
	public List<String> yearList(String id, String yourID) {
		// TODO Auto-generated method stub
		return pd.yearList(id, yourID);
	}

	@Override
	public int getTotal(String id, int year, int month) {
		// TODO Auto-generated method stub
		return pd.getTotal(id,year,month);
	}

	@Override
	public Photo select(int p_no) {
		// TODO Auto-generated method stub
		return pd.select(p_no);
	}

	@Override
	public int update(Photo photo) {
		// TODO Auto-generated method stub
		return pd.update(photo);
	}

	@Override
	public int delete(int p_no) {
		// TODO Auto-generated method stub
		return pd.delete(p_no);
	}



}

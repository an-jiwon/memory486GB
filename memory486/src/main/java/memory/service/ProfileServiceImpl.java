package memory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.ProfileDao;
import memory.model.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileDao ppd;

	@Override
	public Profile select(String id) {
		// TODO Auto-generated method stub
		return ppd.select(id);
	}

	@Override
	public int insert(Profile profile) {
		// TODO Auto-generated method stub
		return ppd.insert(profile);
	}

	@Override
	public Profile getProfile(int pro_no) {
		// TODO Auto-generated method stub
		return ppd.getProfile(pro_no);
	}

	@Override
	public int update(Profile profile) {
		// TODO Auto-generated method stub
		return ppd.update(profile);
	}

	@Override
	public int delete(int pro_no) {
		// TODO Auto-generated method stub
		return ppd.delete(pro_no);
	}

}

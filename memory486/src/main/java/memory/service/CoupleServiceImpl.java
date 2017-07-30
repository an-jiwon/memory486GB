package memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.CoupleDao;
import memory.model.Couple;

@Service
public class CoupleServiceImpl implements CoupleService {
	@Autowired
	private CoupleDao cd;

	@Override
	public Couple select(String id) {
		// TODO Auto-generated method stub
		return cd.select(id);
	}

	@Override
	public int chk(String id) {
		// TODO Auto-generated method stub
		return cd.chk(id);
	}

	@Override
	public int request(String id, String ID_B) {
		// TODO Auto-generated method stub
		return cd.request(id,ID_B);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return cd.delete(id);
	}
	
	//------------------------------------------------------------------------------//
	
	@Override
	public int couplechk(String memberID) {
		return cd.couplechk(memberID);
	}

	@Override
	public String coupleid(String memberID) {
		return cd.coupleid(memberID);
	}

	@Override
	public String acceptchk(String memberID) {
		return cd.acceptchk(memberID);
	}

	@Override
	public int acceptid(String memberID) {
		return cd.acceptid(memberID);
	}

	@Override
	public int couplepositive(String id) {
		
		return cd.couplepositive(id);
		
	}

	@Override
	public int couplenagative(String id) {
		
		return cd.couplenagative(id);
	}
	
	
	@Override
	public List<Couple> couplelist() {
		// TODO Auto-generated method stub
		return cd.couplelist();
	}

	@Override
	public List<Couple> couplelist2() {
		// TODO Auto-generated method stub
		return cd.couplelist2();
	}
}

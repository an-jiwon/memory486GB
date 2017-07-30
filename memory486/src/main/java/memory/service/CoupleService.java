package memory.service;

import java.util.List;

import memory.model.Couple;

public interface CoupleService {

	Couple select(String id);

	int chk(String id);

	int request(String id, String ID_B);

	int delete(String id);
	
	//-------------------------------------------------------------------//
	
	int couplechk(String memberID);

	String coupleid(String memberID);

	String acceptchk(String memberID);

	int acceptid(String memberID);

	int couplepositive(String id);

	int couplenagative(String id);
	
	
	List<Couple> couplelist();

	List<Couple> couplelist2();


}

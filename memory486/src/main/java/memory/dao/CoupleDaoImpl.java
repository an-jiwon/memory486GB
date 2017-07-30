package memory.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import memory.model.Couple;

@Repository
public class CoupleDaoImpl implements CoupleDao{
	@Autowired
	private SqlSessionTemplate st;

	@Override
	public Couple select(String id) {
		// TODO Auto-generated method stub
		return st.selectOne("couplens.select",id);
	}

	@Override
	public int chk(String id) {
		int result = 0;
		String str = (String) st.selectOne("couplens.chk",id);
		if (str != null)
			result = 1;
		return result;
	}

	@Override
	public int request(String id, String ID_B) {
		HashMap hashmap = new HashMap();
		hashmap.put("id", id);
		hashmap.put("ID_B", ID_B);
		return st.insert("couplens.request",hashmap);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return st.delete("couplens.delete",id);
	}
	
	//---------------------------------------------------------------------------------//
	
	@Override
	public int couplechk(String memberID) {
		
		int result = st.selectOne("couplens.intchk",memberID);
		
		return result;
	}

	@Override
	public String coupleid(String memberID) {
		String coupleid = st.selectOne("couplens.coupleid",memberID);
		return coupleid;
	}

	@Override
	public String acceptchk(String memberID) {
		String result = st.selectOne("couplens.acceptchk", memberID);
		return result;
	}

	@Override
	public int acceptid(String memberID) {
		int result = st.selectOne("couplens.acceptid", memberID);
		return result;
	}

	@Override
	public int couplepositive(String id) {
		return st.update("couplens.couplepositive",id);
		
	}

	@Override
	public int couplenagative(String id) {
		return st.delete("couplens.couplenagative",id);
		
	}
	
	
	@Override
	public List<Couple> couplelist() {		
		return st.selectList("couplens.list");
	}

	@Override
	public List<Couple> couplelist2() {
		// TODO Auto-generated method stub
		return st.selectList("couplens.list2");
	}
}

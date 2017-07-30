package memory.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import memory.model.Profile;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	@Autowired
	private SqlSessionTemplate st;

	@Override
	public Profile select(String id) {
		// TODO Auto-generated method stub
		return st.selectOne("profilens.select",id);
	}

	@Override
	public int insert(Profile profile) {
		// TODO Auto-generated method stub
		return st.insert("profilens.insert",profile);
	}

	@Override
	public Profile getProfile(int pro_no) {
		// TODO Auto-generated method stub
		return st.selectOne("profilens.getProfile",pro_no);
	}

	@Override
	public int update(Profile profile) {
		// TODO Auto-generated method stub
		return st.update("profilens.update",profile);
	}

	@Override
	public int delete(int pro_no) {
		// TODO Auto-generated method stub
		return st.delete("profilens.delete",pro_no);
	}
	
}

package memory.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {
	@Autowired
	private SqlSessionTemplate st;

	@Override
	public int adminlogin(String adminid, String adminpass) {
		String dbPass = st.selectOne("adminns.adminlogin",adminid);
		if (adminpass.equals(dbPass))
			return 1;	
		else
			return 0;
	}
}

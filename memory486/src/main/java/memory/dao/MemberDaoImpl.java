package memory.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import memory.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSessionTemplate st;
	
	@Override
	public int login(String id, String password) {
		String dbPass = st.selectOne("memberns.login",id);
		if (password.equals(dbPass))
			return 1;	
		else
			return 0;
		
	}
	
	@Override
	public Member select(String id) {
		// TODO Auto-generated method stub
		return st.selectOne("memberns.select",id);
	}
	
	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return st.update("memberns.update",member);
	}
	
	@Override
	public int idchk(String id) {
		int result = 0;
		String str = (String)st.selectOne("memberns.idchk",id);
		if(str!=null)
			result = 1;
		return result;
	}
	
	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return st.update("memberns.delete",id);
	}
	
	//----------------------------------------------------------------------------------//
	

	/*@Override
	public int login(String memberID, String memberPass) {
		String dbPass = st.selectOne("memberns.login", memberID);
		if (memberPass.equals(dbPass))
			return 1;	
		else
			return 0;
		
	}*/
	
	@Override
	public void joinMember(Member member) {
		st.insert("memberns.insert", member);
	}

	@Override
	public String getMemberId(String memberID) {
		return st.selectOne("selectId", memberID);
	}

	@Override
	public String getName(String memberID) {
		return st.selectOne("getName", memberID );
	}

	@Override
	public Member getMember(String memberID) {
		return st.selectOne("member", memberID);
	}

	
	public String online(String memberID) {
		int online = st.update("memberns.online",memberID);
		if(online>0){		
			String status = st.selectOne("memberns.status",memberID);
			return status;
		}
		else{
			return "error";
		}
	}

	public String pic(String memberID) {
		String str = st.selectOne("memberns.pic",memberID);
		return str;
	}

	@Override
	public String loc(String memberID) {
		return st.selectOne("memberns.loc", memberID);
	}

	@Override
	public String getName2(String coupleid) {
		return st.selectOne("memberns.getName2",coupleid);
	}

	@Override
	public String loc2(String coupleid) {
		return st.selectOne("memberns.loc2", coupleid);
	}

	@Override
	public int logout(String id) {
		// TODO Auto-generated method stub
		return st.update("memberns.logout",id);
	}
	
	
	
	@Override
	public List<Member> content(String id) {
		// TODO Auto-generated method stub
		return st.selectList("memberns.content",id);
	}

	@Override
	public int setyourLayout(String yourID, String yourLayout) {
		// TODO Auto-generated method stub
		HashMap hashmap = new HashMap();
		hashmap.put("yourID", yourID);
		hashmap.put("yourLayout", yourLayout);
		return st.update("memberns.setyourLayout",hashmap);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return st.selectList("memberns.list");
	}

	@Override
	public int recover(String id) {
		// TODO Auto-generated method stub
		return st.update("memberns.recover",id);
	}
}

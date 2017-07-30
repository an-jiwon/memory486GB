package memory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.MemberDao;
import memory.model.Member;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDao md;

	@Override
	public int login(String id, String password) {		
		return md.login(id,password);
	}

	@Override
	public Member select(String id) {
		// TODO Auto-generated method stub
		return md.select(id);
	}

	@Override
	public int update(Member member) {
		// TODO Auto-generated method stub
		return md.update(member);
	}

	@Override
	public int idchk(String id) {
		// TODO Auto-generated method stub
		return md.idchk(id);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return md.delete(id);
	}
	
	//------------------------------------------------------------------------------//
	
	/*@Override
	public int login(String memberID, String memberPass) {		
		return md.login(memberID, memberPass);
	}*/
	
	@Override
	public void joinMember(Member member) {
		md.joinMember(member);
	}

	@Override
	public String getMemberID(String memberID) {
		return md.getMemberId(memberID);
	}

	@Override
	public String getName(String memberID) {
		return md.getName(memberID);
	}

	@Override
	public Member getMember(String memberID) {
		return md.getMember(memberID);
	}

	@Override
	public String online(String memberID) {
		return md.online(memberID);
	}

	@Override
	public String pic(String memberID) {
		return md.pic(memberID);
	}

	@Override
	public String loc(String memberID) {
		return md.loc(memberID);
	}

	@Override
	public String getName2(String coupleid) {
		return md.getName2(coupleid);
	}

	@Override
	public String loc2(String coupleid) {
		return md.loc2(coupleid);
	}

	@Override
	public int logout(String id) {
		// TODO Auto-generated method stub
		return md.logout(id);
	}
	
	
	@Override
	public List<Member> content(String id) {
		// TODO Auto-generated method stub
		return md.content(id);
	}

	@Override
	public int setyourLayout(String yourID, String yourLayout) {
		// TODO Auto-generated method stub
		return md.setyourLayout(yourID, yourLayout);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return md.list();
	}

	@Override
	public int recover(String id) {
		// TODO Auto-generated method stub
		return md.recover(id);
	}
}

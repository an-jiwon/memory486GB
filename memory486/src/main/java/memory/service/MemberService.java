package memory.service;

import java.util.List;

import memory.model.Member;

public interface MemberService {

	int login(String id, String password);

	Member select(String id);

	int update(Member member);

	int idchk(String id);

	int delete(String id);
	
	//------------------------------------------------------------------//
	
	/*int login(String memberID, String memberPass);*/
	
	void joinMember(Member member);

	String getMemberID(String memberID);

	String getName(String memberID);

	Member getMember(String memberID);

	String online(String memberID);

	String pic(String memberID);

	String loc(String memberID);

	String getName2(String coupleid);

	String loc2(String coupleid);

	int logout(String id);

	
	List<Member> content(String id);

	int setyourLayout(String yourID, String yourLayout);

	List<Member> list();

	int recover(String id);

}

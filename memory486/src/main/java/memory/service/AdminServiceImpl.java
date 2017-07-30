package memory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.AdminDao;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao ad;

	@Override
	public int adminlogin(String adminid, String adminpass) {
		// TODO Auto-generated method stub
		return ad.adminlogin(adminid,adminpass);
	}
}

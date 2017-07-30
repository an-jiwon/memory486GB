package memory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import memory.dao.ReplyDao;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDao rd;
}

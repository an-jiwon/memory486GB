package memory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import memory.service.ReplyService;

@Controller
public class ReplyController {
	@Autowired
	private ReplyService rs;
	
	/*@RequestMapping*/
}

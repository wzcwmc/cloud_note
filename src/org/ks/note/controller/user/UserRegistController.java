package org.ks.note.controller.user;

import javax.annotation.Resource;

import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource
	private UserService userService;
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult execute(String username,String password,String nickname){
		
		
		return userService.registUser(username, password, nickname);
		
	}

}

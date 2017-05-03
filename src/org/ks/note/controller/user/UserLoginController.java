package org.ks.note.controller.user;

import javax.annotation.Resource;

import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserLoginController {
@Resource
private UserService userService;
@RequestMapping("/login.do")
//返回指定格式的json下面注解加上
@ResponseBody
public NoteResult execute(String username,String password){
	//调用业务组件
	NoteResult result=userService.checkLogin(username, password);
	return result;
	
}
}

package org.ks.note.service.test;

import junit.framework.Assert;

import org.junit.Test;
import org.ks.note.service.UserService;
import org.ks.note.util.NoteResult;
import org.ks.note.util.SpringTest;

public class TestUserService extends SpringTest{
	
	@Test
	public void testCheckingLogin(){
		UserService service=getContext().getBean("userServiceImpl",UserService.class);
		NoteResult result=service.checkLogin("demo", "123456");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		//断言：1，预期结果2，实际结果   
		//当预期结果和实际结果不一致时，编译器会报错
		//断言看failuers 异常错误看Errors
		//Assert.assertEquals(1, result.getStatus());
		//Assert.assertEquals("用户不存在", result.getMsg());
		
	}

}

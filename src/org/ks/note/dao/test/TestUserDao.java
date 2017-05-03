package org.ks.note.dao.test;

import junit.framework.Assert;

import org.junit.Test;
import org.ks.note.dao.UserDao;
import org.ks.note.entity.User;
import org.ks.note.util.NoteUtil;
import org.ks.note.util.SpringTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserDao extends SpringTest{
	@Test
	public void test1(){
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		UserDao dao=ac.getBean("userDao",UserDao.class);
		
		User user=dao.findByName("demo");
		System.out.println(user.getCn_user_name());
		System.out.println(user.getCn_user_password());
	}
	@Test
	public void testSave(){
		User user=new User();
		String id=NoteUtil.createId();
		user.setCn_user_id(id);
		user.setCn_user_name("ks23");
		String md5_pwd=NoteUtil.md5("123456");
		user.setCn_user_token(null);
		user.setCn_user_desc("ks23");
		UserDao dao=getContext().getBean("userDao",UserDao.class);
		dao.save(user);
		User db_user=dao.findByName(user.getCn_user_name());
		Assert.assertEquals(id, db_user.getCn_user_id());
		Assert.assertEquals("ks23", db_user.getCn_user_name());
		
		Assert.assertNull(db_user.getCn_user_token());
	}

}

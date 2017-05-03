package org.ks.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.ks.note.dao.BookDao;
import org.ks.note.entity.NoteBook;
import org.ks.note.util.SpringTest;

public class TestBookDao extends SpringTest{
	@Test
	public void test1(){
		BookDao dao=getContext().getBean("bookDao",BookDao.class);
		List<NoteBook> list=dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for(NoteBook n:list){
			System.out.println(n.getCn_notebook_name());
		}
	}
	
	
}

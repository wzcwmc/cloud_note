package org.ks.note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.ks.note.dao.BookDao;
import org.ks.note.entity.NoteBook;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.springframework.stereotype.Service;
@Service
public class BookServiceImpl implements BookService{
@Resource
private BookDao bookDao;

	public NoteResult loadUserBooks(String userId) {
		List<NoteBook> books=bookDao.findByUserId(userId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(books);
		return result;
	}

	@Override
	public NoteResult addBook(String bookName, String userId) {
		//可以追加笔记本重名检测
		NoteBook book=new NoteBook();
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		String bookId=NoteUtil.createId();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_desc("");
		book.setCn_notebook_type_id("5");
		Timestamp time=new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);
		bookDao.save(book);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("创建笔记本成功");
		result.setData(bookId);//返回新建的笔记本id
		return result;
	}
	
	
}

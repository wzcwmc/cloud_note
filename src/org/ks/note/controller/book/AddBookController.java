package org.ks.note.controller.book;

import javax.annotation.Resource;

import org.ks.note.service.BookService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notebook")
public class AddBookController {
@Resource
private BookService bookService;
@RequestMapping("add.do")
@ResponseBody
public NoteResult execute(String bookName,String userId){
	NoteResult result=bookService.addBook(bookName, userId);
	return result;
}
}

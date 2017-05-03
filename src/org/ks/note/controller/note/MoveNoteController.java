package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class MoveNoteController {
@Resource
private NoteService noteService;
@RequestMapping("/move.do")
@ResponseBody
public NoteResult execute(String noteId,String bookId){
	NoteResult result=noteService.moveNote(noteId, bookId);
	return result;
	
}
}

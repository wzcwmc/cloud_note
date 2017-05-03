package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class UpdateNoteController {
@Resource
private NoteService noteService;
@RequestMapping("/update.do")
@ResponseBody
public NoteResult execute(String noteId,String noteTitle,String noteBody){
	
	NoteResult result=noteService.updateNote(noteId, noteTitle, noteBody);
	return result;
	
}
}

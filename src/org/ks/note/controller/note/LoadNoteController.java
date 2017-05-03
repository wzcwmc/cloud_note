package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class LoadNoteController {
	//注入service组件
	@Resource
private NoteService noteService;
	@RequestMapping("/load.do")
	@ResponseBody
public NoteResult execute(String noteId){
	NoteResult result=noteService.loadNote(noteId);
	
	return result;
	
}
	}



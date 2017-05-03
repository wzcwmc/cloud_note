package org.ks.note.controller.note;

import javax.annotation.Resource;

import org.ks.note.service.NoteService;
import org.ks.note.util.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/note")
public class SearchShareController {
@Resource
private NoteService noteService;
@RequestMapping("/search.do")
@ResponseBody
public NoteResult execute(String keyword){
	NoteResult result =noteService.searchNote(keyword);
	return result;
}
}

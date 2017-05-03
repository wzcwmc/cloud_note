package org.ks.note.dao.test;

import java.util.List;

import org.junit.Test;
import org.ks.note.dao.NoteDao;
import org.ks.note.entity.Note;
import org.ks.note.util.SpringTest;

public class TestNote extends SpringTest{
	@Test
	public void test1(){
		NoteDao  noteDao=getContext().getBean("noteDao",NoteDao.class);
		List<Note> list=noteDao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Note note:list){
			System.out.println(note.getCn_notebook_id()+","+
								note.getCn_note_title());
		}
	}
}

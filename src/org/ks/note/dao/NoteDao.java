package org.ks.note.dao;

import java.util.List;

import org.ks.note.entity.Note;


public interface NoteDao {
	//移动笔记
	public int updateBookId(Note note);
	//删除笔记
	public int updateStatus(String noteId);
	public int update(Note note);
	public int save(Note note);
	public Note findById(String id);//笔记内容
	List<Note> findByBookId(String bookId);//笔记
	List<String> findByNoteId();//查询分享笔记中的noteid

}

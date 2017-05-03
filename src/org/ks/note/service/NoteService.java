package org.ks.note.service;

import org.ks.note.util.NoteResult;

public interface NoteService {
	//预览笔记
	public NoteResult loadShare(String shareId);
	//搜索笔记
	public NoteResult searchNote(String keyword);
	//移动笔记
	public NoteResult moveNote(String noteId,String bookId);
	//分享笔记
	public NoteResult shareNote(String noteId);
	//删除笔记
	public NoteResult recycleNote(String noteId);
	//更新笔记内容
	public NoteResult updateNote(String noteId,String noteTitle,String noteBody);
	//添加笔记
	public NoteResult addNote(String bookId,String noteTitle,String userId);
	//加载笔记
	public NoteResult loadNote(String noteId);
	public NoteResult loadBookNotes(String bookId);


}

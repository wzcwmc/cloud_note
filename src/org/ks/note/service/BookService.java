package org.ks.note.service;

import org.ks.note.util.NoteResult;

public interface BookService {
	public NoteResult addBook(String bookName,String userId);
	public NoteResult loadUserBooks(String userId);
	

}

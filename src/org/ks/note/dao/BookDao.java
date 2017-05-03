package org.ks.note.dao;

import java.util.List;

import org.ks.note.entity.NoteBook;

public interface BookDao {
	public int save(NoteBook book);
	public List<NoteBook> findByUserId(String userId);

}

package org.ks.note.service;

import org.ks.note.util.NoteResult;

public interface UserService {
	public NoteResult registUser(String username,String password,String nickname);
	public NoteResult checkLogin(String username,String password);

}

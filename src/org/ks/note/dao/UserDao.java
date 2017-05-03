package org.ks.note.dao;

import org.ks.note.entity.User;

public interface UserDao {
	public int save(User user);
	public User findByName(String name);

}

package org.ks.note.dao;

import java.util.List;

import org.ks.note.entity.Note;
import org.ks.note.entity.Share;

public interface ShareDao {
	public Share findById(String shareId);//笔记内容

	//模糊查询
	public List<Share> findLikeTitle(String title);
	//依据noteId检查是否分享过
public Share findByNoteId(String noteId);
public int save(Share share);
}

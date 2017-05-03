package org.ks.note.service;

import java.util.List;

import javax.annotation.Resource;

import org.ks.note.dao.NoteDao;
import org.ks.note.dao.ShareDao;
import org.ks.note.entity.Note;
import org.ks.note.entity.Share;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.springframework.stereotype.Service;
@Service
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao noteDao;
	@Resource
	private ShareDao shareDao;
	
	public NoteResult loadBookNotes(String bookId) {
		List<Note> notes=noteDao.findByBookId(bookId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("查询笔记成功");
		result.setData(notes);
		return result;
	}

	@Override
	public NoteResult loadNote(String noteId) {
		Note note=noteDao.findById(noteId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("加载笔记信息成功");
		result.setData(note);
		return result;
		
	}
	
	public NoteResult updateNote(String noteId, String noteTitle,
			String noteBody) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.update(note);//更新cn_note表
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("保存笔记信息成功");
		
		return result;
	}


	public NoteResult addNote(String bookId, String noteTitle, String userId) {
		Note note=new Note();
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(noteTitle);
		note.setCn_user_id(userId);
		String noteId=NoteUtil.createId();
		note.setCn_note_id(noteId);//笔记Id
		note.setCn_note_status_id("1");//normal
		note.setCn_note_type_id("1");
		note.setCn_note_body("");
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		
		noteDao.save(note);//保存
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("笔记创建成功");
		result.setData(noteId);
		
		return result;
	}

	@Override
	public NoteResult recycleNote(String noteId) {
		NoteResult result=new NoteResult();
		int row=noteDao.updateStatus(noteId);
		if(row==0){
			result.setStatus(1);
			result.setMsg("放入回收站失败");
			return result;
		}
		result.setStatus(0);
		result.setMsg("放入回收站成功");
		return result;
	}
//笔记分享
	@Override
	public NoteResult shareNote(String noteId) {
	//1检查笔记是否分享过
		NoteResult result=new NoteResult();
		Share has_share=shareDao.findByNoteId(noteId);
		if(has_share!=null){
			result.setStatus(1);
			result.setMsg("该笔记已分享");
			return result;
		}
		//2.分享根据笔记的id查询,获取share 进行cn_share表的插入
		Note note=noteDao.findById(noteId);
				Share share=new Share();
				share.setCn_note_id(noteId);
				share.setCn_share_title(note.getCn_note_title());
				share.setCn_share_body(note.getCn_note_body());
				String shareId=NoteUtil.createId();
				share.setCn_share_id(shareId);
				shareDao.save(share);
				result.setStatus(0);
				result.setMsg("笔记分享成功");
				return result;
	}

	@Override
	public NoteResult moveNote(String noteId,String bookId) {
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		
		noteDao.updateBookId(note);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("移动成功");
		return result;
	}

	@Override
	public NoteResult searchNote(String keyword) {
		String title="";
		//判断
		if(keyword==null||"".equals(keyword)){
			//查询全部
			title="%";//%任意全部
		}else{
			title="%"+keyword+"%";
		}
		//Note note=new Note();
		//note.getCn_note_title();
		List<Share> list=shareDao.findLikeTitle(title);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("搜索成功");
		result.setData(list);
		return result;
	}
//预览笔记
	@Override
	public NoteResult loadShare(String shareId) {
		Share share=shareDao.findById(shareId);
		NoteResult result=new NoteResult();
		result.setStatus(0);
		result.setMsg("成功预览");
		result.setData(share);
		//System.out.println(share);
		return result;
	}
}


	



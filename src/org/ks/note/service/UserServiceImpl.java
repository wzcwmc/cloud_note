package org.ks.note.service;

import javax.annotation.Resource;

import org.ks.note.dao.UserDao;
import org.ks.note.entity.User;
import org.ks.note.util.NoteResult;
import org.ks.note.util.NoteUtil;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public NoteResult checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		NoteResult result=new NoteResult();
		User user=userDao.findByName(username);
		//检测用户
		if(user==null){
			//状态0：表示成功，其他情况自定义
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		//检测密码
		String md5_password=NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5_password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//全部正确
		result.setStatus(0);
		result.setMsg("用户名和密码正确");
		//将用户的id返回
		result.setData(user.getCn_user_id());
		return result;
	}

	@Override
	public NoteResult registUser(String username, String password,
			String nickname) {
		NoteResult result=new NoteResult();
		//检测用户名是否注册
		User dbUser=userDao.findByName(username);
		if(dbUser!=null){
			result.setStatus(1);
			result.setMsg("用户名已经存在");
			return result;
		}
		//注册操作
		User user=new User();
		String userId=NoteUtil.createId();
		user.setCn_user_id(userId);
		user.setCn_user_name(username);
		String md5_pwd=NoteUtil.md5(password);
		user.setCn_user_password(md5_pwd);
		user.setCn_user_desc(nickname);
		user.setCn_user_token(null);
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		
		
		
		return result;
	}

}

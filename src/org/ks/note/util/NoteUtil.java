package org.ks.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;

public class NoteUtil {
	/**
	 * 密码加密处理
	 * @param msg 明文
	 * @return 加密之后的密文
	 * md5加密的特点
	 * 1.不可逆
	 * 2.不管明文的长度是否一致
	 * 加密后的密文长度都是一致的
	 */
	public static String md5(String msg){
		//返回的对象具有MD5算法的对象
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] input=msg.getBytes();
			//对传入的字节数组进行加密并返回
			//md5处理后的结果转成字符串会乱码
			//用Base64算法解决
			byte[] output=md.digest(input);
			String str=Base64.encodeBase64String(output);
			return str;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("密码加密异常");
			return "";
		}
		
	}
	//使用UUID生成id号码，不重复，不连续
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	
	
	
	public static void main(String[] args){
		System.out.println(md5("1234"));
		System.out.println(md5("123456"));
		/*
		 * update cn_user set cn_user_password='4QrcOUm6Wau+VuBX8g+IPg=='
		 */
		
	}
	
	
	
	

}

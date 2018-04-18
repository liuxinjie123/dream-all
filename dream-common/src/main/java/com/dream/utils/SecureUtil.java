package com.dream.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import com.dream.dao.user.UserDAO;
import org.springframework.util.DigestUtils;

/**
 * 工具类
 */
public class SecureUtil {

	public static String securePassword(String password) {
		return org.apache.commons.codec.digest.DigestUtils.md5Hex("$&*" + org.apache.commons.codec.digest.DigestUtils.md5Hex("@." + password + "$*************") + "!@#%……&");
	}
	
	/**
	 * 加盐加密的策略非常多,根据实际业务来
	 */
	public static void entryptPassword(UserDAO user) {
		String salt = UUID.randomUUID().toString();
		String temPassword = salt + user.getPlainPassword();
		String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
		user.setSalt(salt);
		user.setPassword(md5Password);
	}
	
	public static boolean decryptPassword(UserDAO user, String plainPassword) {
		String temPassword = user.getSalt() + plainPassword;
		String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
		return user.getPassword().equals(md5Password);
	}
	
	public static String getCurrentDateTime() {
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(zone);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

}

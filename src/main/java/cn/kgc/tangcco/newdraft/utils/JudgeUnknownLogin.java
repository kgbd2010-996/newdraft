package cn.kgc.tangcco.newdraft.utils;

import java.util.regex.Pattern;

/**
 * 判断手机号、邮箱还是用户名
 * @author YaoShTime
 *
 */
public class JudgeUnknownLogin {
	/**
	 * 1是邮箱
	 * 2是手机号
	 * 0是普通账号
	 * @param unknownLogin
	 * @return
	 */
	public static int getJudgeResult(String unknownLogin) {
		String emailPattern = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		String phonePattert = "^1[34578]\\d{9}$";
		String email2Pattern = "^([a-zA-Z0-9_\\.\\-])+\\%40(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		if(Pattern.matches(emailPattern, unknownLogin)) {
			return 1;
		}else if(Pattern.matches(email2Pattern, unknownLogin)) {
			return 1;
		}else if(Pattern.matches(phonePattert, unknownLogin)) {
			return 2;
		}
		return 0;
	}
	
//	public static void main(String[] args) {
//		System.out.println(getJudgeResult("yaoshun1999@sina.com"));
//		System.out.println(getJudgeResult("18810445927"));
//		System.out.println(getJudgeResult("110"));
//		System.out.println(getJudgeResult("6666"));
//	}
}

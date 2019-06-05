package com.kboss.system.tool;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ZTool {
	/**
	 * 网址添加新的参数
	 */
	public static String addParamer(String url,String par) {
		if(url.indexOf("?")>-1) {
			return url+"&"+par;
		}else {
			return url+"?"+par;
		}
	}
	/**
	 * 获取当前日期
	 */
	public static String getNowDateTime() {
		return formatDate("yyyy-MM-dd HH:mm:ss",new Date());
	}
	/**
	 * 格式化日期
	 */
	public static String formatDate(String format,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if(date==null) {
			date = new Date();
		}
		return sdf.format(date);
	}
	/**
	 * 格式化日期
	 */
	public static Date formatDate(String format,String date) {
		Date result = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			result = sdf.parse(date);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取树形的下个编号
	 */
	public static String getNextNum(String num) {
		String returnStr = "";
		returnStr = (Integer.parseInt(num)+1)+"";
		Integer length = num.length();
		if(returnStr.length()<length) {
			Integer n = length-returnStr.length();
			for(int i=0;i<n;i++) {
				returnStr = "0" + returnStr;
			}
		}
		return returnStr;
	}
	/**
	 * MD5加密
	 */
    public static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
 	/**
 	 * 过滤特殊字符,允许() （）【】
 	 */
    public static String StringFilter(String str) throws PatternSyntaxException  {     
          String regEx="[`~!@#$%^*+=|{}':;',\\[\\].<>/~！@#￥%……*——+|{}‘；：”“’。，、？]";
          Pattern p=Pattern.compile(regEx);     
          Matcher m=p.matcher(str);     
          return m.replaceAll("").trim();     
   } 
	//获取cookies值
	public static String getCookiesByName(HttpServletRequest request,String name) {
		 Cookie[] cookies = request.getCookies(); //先得到Cookie数组
		 String value = "";
		 if (cookies != null) {
			 for (int i = 0; i < cookies.length; i++)
			 {
				 Cookie newCookie = cookies[i];
				 if (name.equals(newCookie.getName())){
					 value = newCookie.getValue();
				 }
			 }
		 }	
		 return value;
	}
}

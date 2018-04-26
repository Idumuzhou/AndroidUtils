package com.forever.utilcode;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符串相关工具类
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 判断字符串值是否有效
     *
     * @param value
     * @return
     */
    public static boolean isValueInvalid(String value) {
        if (TextUtils.isEmpty(value) || value.equalsIgnoreCase("null") || value.equalsIgnoreCase("unknown") || value.equalsIgnoreCase("未知") || value.length() < 3) {
            return false;
        } else {
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) != '0') {
                    return true;
                }
            }
            return false;
        }
    }



    /**
     * 判断是否是手机号码
     *
     * @param s
     * @return
     */
    public static boolean isMobile(String s) {
//        Pattern p = null;
//        Matcher m = null;
//        boolean b = false;
//        p = Pattern.compile("^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$"); // 验证手机号
//        m = p.matcher(s);
//        b = m.matches();

        if (s.length() < 10) {
            return false;
        }


        return true;
    }

    /**
     * 截取时间长度
     *
     * @param string
     * @return
     */
    public static String timeSub(String string, int length) {
        String str = "";
        if (null != string) {
            if (string.equals("-")) {
                str = "-";
            } else {
                str = string.substring(0, length);
            }
        }
        return str;
    }

    /**
     * 截取时间长度
     *
     * @param string
     * @return
     */
    public static String timeSubStartEnd(String string, int start, int end) {
        String str = "-";
        if (null != string && string.length() > 16) {
            str = string.substring(start, end);
        }
        return str;
    }

    /***
     *
     * 把数字转换成三位以空格
     *
     *
     */

    public static String numberChange(int number, int size) {

        DecimalFormat df1 = (DecimalFormat) DecimalFormat.getInstance();
        df1.setGroupingSize(size);
        String string = (df1.format(number) + "").replaceAll(",", " ");
//        string = string.replaceAll("."," ");

        return string;
    }


    /**
     * java读取文件中的属性类型
     * @param model
     * @return
     * @throws Exception
     */
    public static Map<String,String> getModelAttriButeType(Object model){
        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        Map<String,String> map = new HashMap<String, String>();
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            String name = field[j].getName();    //获取属性的名字

            //System.out.print("attribute name:"+name);
            name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString();    //获取属性的类型
              /*if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
                  Method m = model.getClass().getMethod("get"+name);
                  String value = (String) m.invoke(model);    //调用getter方法获取属性值
                  if(value != null){

                      System.out.println("attribute value:"+value);
                  }
              }*/
            type = type.replace("class ", "");
            //System.out.println("=>:"+type);
            map.put(name, type);

        }
        return map;
    }



}
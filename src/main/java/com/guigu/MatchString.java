package com.guigu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MatchString {
	
	public static String getPreStr(String msg,String spit) {  
		
		return null;
	}
	
	//获取匹配替换后的新字符数据
	public static String getReplaceMatch(String msg,char left,char right,String preStr,String reStr) {  
        //纯净化处理
        String childStr = null ;
        String newChildStr = null ;
        String newString = msg;
        
        int start = 0;  
        int end = 0;  
        int startFlag = 0;  
        int endFlag = 0;  
        
        for (int i = 0; i < msg.length(); i++) {  
        	//如果寻找匹配字符串
        	if (left == right) {
        		if (msg.charAt(i) == left) {  
                    startFlag++;  
                    if (startFlag%2==1) {  
                        start = i; 
                    }  else {
                    	endFlag++;
                    	end = i;
                    	childStr = msg.substring(start + 1, end);
//	                    	replace
                    	newChildStr = getNewReStr(childStr,preStr,reStr).trim();
//	                    	newChildStr = getNewStr(childStr);
                    	newString = newString.replace(childStr, newChildStr);
					}
                } 
        	} else {
        		if (msg.charAt(i) == left) {  
                    startFlag++;  
                    if (startFlag == endFlag + 1) {  
                        start = i; 
                    }  
                } else if (msg.charAt(i) == right) {  
                    endFlag++;
                    if (endFlag == startFlag) {  
                    	end = i;
                    	childStr = msg.substring(start + 1, end);
                    	newChildStr = getNewReStr(childStr,preStr,reStr).trim();
//	                    	newChildStr = getNewStr(childStr);
                    	newString = newString.replace(childStr, newChildStr);
                    }  
                }  
            }  
		}
        return newString;
    }
	
	//获取匹配的字符数据
	public static List<String> getMatchList(String msg,char left,char right) {  
        List<String> list = new ArrayList<String>();  
        int start = 0;  
        int startFlag = 0;  
        int endFlag = 0;  
        for (int i = 0; i < msg.length(); i++) {  
            if (msg.charAt(i) == left) {  
                startFlag++;  
                if (startFlag == endFlag + 1) {  
                    start = i; 
                }  
            } else if (msg.charAt(i) == right) {  
                endFlag++;
                if (endFlag == startFlag) {  
                    list.add(msg.substring(start + 1, i).trim());  
                }  
            }  
        }  
        return list;  
    }

	//获取匹配替换后的新字符数据
	public static String getNewStr(String msg,char left,char right) {  
        //纯净化处理
        String childStr = null ;
        String newChildStr = null ;
        String newString = msg;
        
        int start = 0;  
        int end = 0;  
        int startFlag = 0;  
        int endFlag = 0;  
        
        for (int i = 0; i < msg.length(); i++) {  
        	//如果寻找匹配字符串
        	if (left == right) {
        		if (msg.charAt(i) == left) {  
                    startFlag++;  
                    if (startFlag%2==1) {  
                        start = i; 
                    }  else {
                    	endFlag++;
                    	end = i;
                    	childStr = msg.substring(start + 1, end);
                    	newChildStr = getNewStr(childStr).trim();
                    	newString = newString.replace(childStr, newChildStr);
					}
                } 
        	} else {
        		if (msg.charAt(i) == left) {  
                    startFlag++;  
                    if (startFlag == endFlag + 1) {  
                        start = i; 
                    }  
                } else if (msg.charAt(i) == right) {  
                    endFlag++;
                    if (endFlag == startFlag) {  
                    	end = i;
                    	childStr = msg.substring(start + 1, end);
                    	newChildStr = getNewStr(childStr).trim();
                    	newString = newString.replace(childStr, newChildStr);
                    }  
                }  
            }  
		}
        return newString;
    }
	
	//获取匹配后新字符数据
	public static List<String> getMatchChildStr(String msg,char left,char right) {  
		List<String> list = new LinkedList<String>();  
        //纯净化处理
        String childStr ;
        String newChildStr = null ;
        int start = 0;  
        int end = 0;  
        int startFlag = 0;  
        int endFlag = 0;  
        
        for (int i = 0; i < msg.length(); i++) {  
        	//如果寻找匹配字符串
        	if (left == right) {
        		if (msg.charAt(i) == left) {  
                    startFlag++;  
                    /*********/
                    if (startFlag%2==1) {  
                        start = i; 
                    }  else {
                    	endFlag++;
                    	end = i;
                    	childStr = msg.substring(start + 1, end);
                    	newChildStr = getNewStr(childStr).trim();
                    	list.add(newChildStr);
					}
                } 
        	} else {
        		if (msg.charAt(i) == left) {  
                    startFlag++;  
                    if (startFlag == endFlag + 1) {  
                        start = i; 
                    }  
                } else if (msg.charAt(i) == right) {  
                    endFlag++;
                    if (endFlag == startFlag) {  
                    	end = i;
                    	childStr = msg.substring(start + 1, end);
                    	newChildStr = getNewStr(childStr).trim();
                    	list.add(newChildStr);
                    }  
                }  
            }  
		}
        return list;
    }
		
	private static String getNewStr(String subString) {
		if (subString.contains(";")) {
			subString = subString.replace(";", "~");
		} 
    	if (subString.contains("\\s+")) {
    		subString = subString.replaceAll("\\s+", "");
		}
    	if (subString.contains("；")) {
    		subString = subString.replace("；", ",");
		}
    	if (subString.contains("：")) {
    		subString = subString.replace("：", ":");
		}
    	if (subString.contains("、")) {
    		subString = subString.replace("、", ",");
		}
    	if (subString.contains("，")) {
    		subString = subString.replace("，", ",");
		}
    	if (subString.contains("。")) {
    		subString = subString.replace("。", "");
		}
    	if (subString.contains("（")) {
    		subString = subString.replace("（", "(");
		}
    	if (subString.contains("）")) {
    		subString = subString.replace("）", ")");
		}
    	if (subString.contains("。")) {
    		subString = subString.replace("。", "");
		}
    	if (subString.contains("？")) {
    		subString = subString.replace("？", "?");
		}
    	if (subString.contains(" ")) {
    		subString = subString.trim();
		}
    	if (subString.contains("\r\n")) {
    		subString = subString.replace("\r\n", " ");
		}
		return subString;
	}
	
	private static String getNewReStr(String subString,String preStr,String reStr) {
		if (subString.contains(preStr)) {
			subString = subString.replace(preStr, reStr);
		}
		return subString;
	}
	
}

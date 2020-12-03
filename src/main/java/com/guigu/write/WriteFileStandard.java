package com.guigu.write;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class WriteFileStandard {
	public static void main(String[] args) {
	}

	private static String getType(String param) {
		param = param.toUpperCase();
		String newParam = null;
		if (param.contains("VARCHAR")) {
			newParam = "STRING";
		} else if (param.contains("CHAR")) {
			newParam = "STRING";
		} else if (param.contains("DATE")) {
			newParam = "TIMESTAMP";
		} else if (param.contains("FLOAT")) {
			newParam = "DOUBLE";
		} else if (param.contains("NUMBER")) {
			if (param.contains(",")) {
				newParam = "DOUBLE";
			} else {
				newParam = "BIGINT";
			}
		} else {
		}
		return newParam;
	}

	private static String getPureMessage(String msg) {
		String newMsgString = "";
		if (msg != null) {
			for (int i = 0; i < msg.length(); i++) {
				if (msg.charAt(i) != '\'') {
					newMsgString = newMsgString + msg.charAt(i);
				}
			}
		}
		return newMsgString;
	}

	private static String getPureString(String param) {
		param = param.toUpperCase();
		String newParam = null;
		if (param.contains("'")) {
			int first = param.indexOf("'");
			int last = param.lastIndexOf("'");
			if (last == -1) {
				newParam = param.substring(first + 1);
			} else {
				newParam = param.substring(first + 1, last);
			}
		} else {
		}
		return newParam;
	}

	private static List<String> extractMessage(String msg) {
		List<String> list = new ArrayList<String>();
		int start = 0;
		int startFlag = 0;
		int endFlag = 0;
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) == '[') {
				startFlag++;
				if (startFlag == endFlag + 1) {
					start = i;
				}
			} else if (msg.charAt(i) == ']') {
				endFlag++;
				if (endFlag == startFlag) {
					list.add(msg.substring(start + 1, i));
				}
			}
		}
		return list;
	}

	private static List<String> getMatchDouble(String msg, char left, char right) {
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
					list.add(msg.substring(start + 1, i));
				}
			}
		}
		return list;
	}

	private static String getMatchDoubleString(String msg, char left, char right) {
		String replaceFirst = msg;
		String replaceAll = msg;
		String reFirst = null;
		String reSecond = null;
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
					String substring = msg.substring(start + 1, i);
					if (substring.contains(";")) {
						reFirst = substring.replaceAll(";", ":");
						replaceFirst = msg.replaceAll(substring, reFirst);
						System.err.println("/////////**********" + replaceAll);
					}
					if (substring.contains("\\s+")) {
						reSecond = reFirst.replaceAll("\\s+", "");
						replaceAll = msg.replaceAll(substring, reSecond);
					}
					System.err.println("================**********" + replaceAll);
				}
			}
		}
		return replaceAll;
	}

	private static List<String> getFileNameList(String name) {
		Properties prop = new Properties();
		List<String> strList = new ArrayList<String>();
		String fileName = name + ".properties";
		try {
			// 读取属性文件a.properties
			InputStream in = new BufferedInputStream(new FileInputStream(fileName));
			prop.load(in); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				strList.add(prop.getProperty(key));
				System.out.println(key + ":" + prop.getProperty(key));
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return strList;
	}

	public static void writeToFile(String outFilePath, boolean append, String content) {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(outFilePath, append), "UTF-8"));
			bufferedWriter.write(content);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件输入输出流
				bufferedWriter.close();
			} catch (Exception e) {
			}
		}
	}

	public static void writeToFile(String outFilePath, boolean append, List<String> contentList) {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(outFilePath, append), "UTF-8"));
			for (String content : contentList) {
				bufferedWriter.write(content);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件输入输出流
				bufferedWriter.close();
			} catch (Exception e) {
			}
		}
	}
}

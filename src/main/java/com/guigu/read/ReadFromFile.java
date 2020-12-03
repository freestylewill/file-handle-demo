package com.guigu.read;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List<String> readFileByLines(String filePath) {
		BufferedReader reader = null;
		List<String> strList = new ArrayList<>();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				strList.add(tempString.trim());
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return strList;
	}

}

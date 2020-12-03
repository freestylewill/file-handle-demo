package com.guigu.directory;

import java.io.File;

//题目:遍历出aaa文件夹下的文件
//首先分析思路：
//	1.首先判断这个文件夹是否为文件，通过isFile()函数可以判断是否为文件。
// 2.然后通过isDirectory判断是否为目录。
// 3.如果是目录就使用递归遍历目录
public class DirectorySearch {

	public static void main(String[] args) {
		// 创建file对象
//		File file = new File("D:/tmp");
		File file = new File("D:/tmp");
		// 用listFiles()方法遍历文件夹
		File[] listFiles = file.listFiles();
		fun(listFiles);
	}

	public static void fun(File[] file) {
		// 如果文件夹为空就直接退出
		if (file == null) {
			return;
		}
		// 遍历file
		for (File subFile : file) {
			// 判断如果文件不是文件夹就直接输出文件名
			if (subFile.isFile()) {
//				System.out.println(subFile.getName());
			}
			// 如果文件是个文件夹就继续通过调用自己遍历文件夹
			else if (subFile.isDirectory()) {
				System.err.println(subFile.getAbsolutePath());
				fun(subFile.listFiles());
			}
		}
	}

}
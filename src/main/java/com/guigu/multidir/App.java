package com.guigu.multidir;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		File file = new File("D:\\tmp\\webapps\\uuid\\APP.sql");
		if (!file.getParentFile().exists()) {
			System.err.println(file.getParentFile());
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(file.getPath());
	}
}

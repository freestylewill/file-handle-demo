package com.guigu.getresource;

import java.io.File;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class ResourceApp {
	public static void main(String[] args) {
		ResourceApp resourceApp = new ResourceApp();
		try {
			resourceApp.testname();
			resourceApp.testpath();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testname() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("");
		String path = url.getPath();
		System.out.println(path);
		System.out.println("Hello World!");
	}

	public void testpath() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("test.sql");
		String path = url.getPath();
		System.err.println(path);
		File file = new File(path);
		System.out.println("Hello World!");
	}
}

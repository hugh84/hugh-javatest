package com.hugh.javatest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 检测目录下jar包是否有异常文件存在
 * @author Hugh
 *
 */
public class TestJarFile {
	//private static String libdir = "D:/Code/server-admin/target/loan-admin-1.0/WEB-INF/lib/";
	private static String libdir = "D:/Program Files/apache-maven-3.5.0/repository/";
	private static boolean foundflag = false;
	private static boolean delflag = false;

	public static void main(String[] args) {
		test(libdir);
		if (!foundflag) {
			System.out.println("所有jar包检测完毕，未发现异常jar!");
		}
	}

	// 获取文件列表
	private static void test(String dir) {
		File file = new File(dir);
		File[] files = file.listFiles();
		// 如果目录为空，直接退出
		if (files == null) {
			return;
		}
		// 遍历，目录下的所有文件
		for (File f : files) {
			if (f.isFile() && f.getName().endsWith(".jar")) {
				// System.out.println(f.getAbsolutePath());
				read(f.getAbsolutePath());
			} else if (f.isDirectory()) {
				// System.out.println(f.getAbsolutePath());
				test(f.getAbsolutePath());
			}
		}
	}

	// 读取文件
	private static void read(String filename) {
		InputStream is = null;
		try {
			URL url = new URL("jar:file:/" + filename + "!/META-INF/MANIFEST.MF");
			is = url.openStream();
			byte b[] = new byte[1023];
			is.read(b);
			is.close();
		} catch (Exception e) {
			if (e.getMessage().indexOf("invalid LOC header") != -1) {
				foundflag = true;
				System.out.println("异常jar文件：" + filename);
				// e.printStackTrace();
				if (delflag) {
					try {
						if (is != null)
							is.close();
						Runtime.getRuntime().exec("del " + filename);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}

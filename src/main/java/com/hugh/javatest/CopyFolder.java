package com.hugh.javatest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFolder {
	public static String srcPath="F:/Musics/";
	public static String destPath="F:/Musics/";

	public static void main(String[] args){
		String[] filelist1,filelist2;
		File file = new File(srcPath);
		//��ΪĿ¼
		if (file.isDirectory()) {
			filelist1 = file.list();
			for (int i = 0; i < filelist1.length; i++) {
				file = new File(srcPath + "/" + filelist1[i]);
				//��ΪĿ¼
				if (file.isDirectory()) {
					filelist2 = file.list();
					for (int j = 0; j < filelist2.length; j++) {
						//���⴦��
						if(filelist2[j].endsWith(".wma") || filelist2[j].endsWith(".mp3")){
							copyFile(file.getAbsolutePath() +'/'+ filelist2[j],destPath+filelist2[j]);
						}
					}
				}
			}
		}
	}
	
	//??????????
	public static void copyFile(String src, String dest) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(new File(src));
			fo = new FileOutputStream(new File(dest));
			in = fi.getChannel();// ??????????????
			out = fo.getChannel();// ??????????????
			in.transferTo(0, in.size(), out);// ??????????????????in???????????��??out???
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

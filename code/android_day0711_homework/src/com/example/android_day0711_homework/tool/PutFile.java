package com.example.android_day0711_homework.tool;

import android.util.Log;

import java.io.*;

/**
 * Created by VennUser on 2015/7/11.
 */
public class PutFile {
	public static void CreateFile(byte[] data, String path) {
		try {
			File file = new File(path, "new_file");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(data, 0, data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

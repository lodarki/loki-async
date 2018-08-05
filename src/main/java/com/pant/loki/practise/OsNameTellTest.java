package com.pant.loki.practise;

import com.pant.loki.utils.FileUtil;

import java.io.File;

public class OsNameTellTest {
	public static void main(String[] args) {
		File file1 = new File("D:\\SUBRECORD\\2018-07-13-192-168-100-31-record-1.txt");
		File file2 = new File("D:\\RECORD\\2018-07-13-192-168-100-31-record-1.txt");
		boolean b = FileUtil.moveFile(file2, file1);
		System.out.println(b);
	}
}

package com.pant.loki.practise;

import java.io.IOException;

public class OsNameTellTest {
	public static void main(String[] args) {
		try {
			Process process = Runtime.getRuntime().exec("sh /tmp/aa.sh");
			process.waitFor();
			System.out.println(process.exitValue());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

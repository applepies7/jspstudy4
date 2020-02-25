package com.study.util;

import java.text.DecimalFormat;

public class FileUtils {

	// 1024보다 작으면 그냥 바이트 붙여서 출력
	// 1048576보다 작으면 size/1024 kb
	// 1024*1024*1024 mb

	public static String fancySize(long size) {
		DecimalFormat fmt = new DecimalFormat("#,###.##");
		String fSize = null;
		long kb = 1024;
		long mb = 1024 * 1024;
		long gb = 1024 * 1024 * 1024;
		double div = 1024.0;
		if (size < kb) {

			fSize = fmt.format(size) + "Byte";

		} else if (size< mb) {
			fSize = fmt.format(size / div) + "Kb";

		} else if (size < gb) {
			fSize = fmt.format(size / div / div) + "Mb";
		} else {
			fSize = fmt.format(size / div / div / div) + "Gb";
		}

		return fSize;
	}

	public static void main(String[] args) {
		int a = 2147483647;
		long b = 99999999999999L;
		System.out.println(fancySize(1024*1024*1023));
		System.out.println();
	}
}

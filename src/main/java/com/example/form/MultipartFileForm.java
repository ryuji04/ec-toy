package com.example.form;

import java.util.Arrays;

/**
 * 商品画像ファイルに関するフォーム.
 * 
 * @author adachiryuji
 *
 */
public class MultipartFileForm {
	/**ファイル名*/
	private String originalFileName;
	/**配列*/
	private byte[]bytes;

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		return "MultipartFile [originalFileName=" + originalFileName + ", bytes=" + Arrays.toString(bytes) + "]";
	}
	
	
}

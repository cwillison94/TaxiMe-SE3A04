package com.mac.se3a04.taxime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

/**
 * This class is responsible for reading writing information to the android
 * internal storage. This class was made for the purpose remember the users 
 * login email.
 * 
 * @author Cole Willison
 * @version 1.0
 * @since 2014-11-05
 * */
public class FileManager {
	private String fileName;
	private String path;

	/**
	 * Class constructor, also gets the file path of the internal 
	 * storage.
	 * 
	 * @param Context context the context to write you want to read/write
	 * @param String fileName the fileName to which you want to read/write
	 * 
	 * */
	public FileManager(Context context, String fileName) {
		this.path = context.getFilesDir().getAbsolutePath();
		this.fileName = fileName;
	}

	/**
	 * Writes the data to the file
	 * @param String data - Data to write
	 * @return void
	 * @see IOException
	 * */
	public void writeToFile(String data) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(new File(this.path + "/"
				+ this.fileName));
		fileOutputStream.write(data.getBytes());
		fileOutputStream.close();
	}

	/**
	 * Reads the data from the specified fileName in the contructor
	 * @param None
	 * @return String data read at fileName
	 * 
	 * */
	public String readFile() throws IOException {
		byte[] bytes = new byte[50];
		FileInputStream fileInputStream = new FileInputStream(new File(this.path + "/"
				+ this.fileName));
		fileInputStream.read(bytes);
		fileInputStream.close();

		return new String(bytes);
	}

}

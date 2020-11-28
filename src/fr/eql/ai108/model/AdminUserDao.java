package fr.eql.ai108.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AdminUserDao {
	
	private String loginBDDPath = "./login.bin";
	
	public void addAdmin(AdminUser adminUser) {
		
		File file = new File(loginBDDPath);
		FileWriter out = null;
		RandomAccessFile raf = null;
		
		try {
					
			file.createNewFile();
			
			out = new FileWriter(file, true);
			raf = new RandomAccessFile(file, "rw");
			
			raf.writeBytes(adminUser.getLogin());
			raf.writeByte(0);
			raf.writeBytes(adminUser.getPassword() + "\r");
			
			raf.writeBytes("Bob0");
			raf.writeByte(0);
			raf.writeBytes("cool" + "\r");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

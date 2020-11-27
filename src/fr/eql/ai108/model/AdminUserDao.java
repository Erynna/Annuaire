package fr.eql.ai108.model;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AdminUserDao {
	
	private String loginBDDPath = "./login.bin";
	
	public void addAdmin(AdminUser adminUser) {
		
		File file = new File(loginBDDPath);
		RandomAccessFile raf = null;

		
		try {
			
			raf = new RandomAccessFile(file, "rw");
			
			file.createNewFile();
			
			raf.writeBytes(adminUser.getLogin() + "_" + adminUser.getPassword());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

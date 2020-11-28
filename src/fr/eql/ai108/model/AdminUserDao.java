package fr.eql.ai108.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class AdminUserDao {

	private String loginBDDPath = "./login.bin";

	public void addAdminAccount(String login, String password) {

		File file = new File(loginBDDPath);
		FileWriter out = null;
		BufferedWriter bw = null;

		try {

			file.createNewFile();

			out = new FileWriter(file, true);
			bw = new BufferedWriter(out);

			bw.write(login);
			bw.write(0);											//Ecriture d'un octet null afin de séparer les champs Login et Password
			bw.write(password + "\r");

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//	public void modifyPassword(String initialLogin, String initialPassword, String newPassword) {
	//
	//		File file = new File(loginBDDPath);
	//		RandomAccessFile raf = null;
	//		int sizeLogin = 0;
	//		byte[] initialLoginInBytes = initialLogin.getBytes();
	//		byte[] ligne = new byte[20];
	//
	//		try {
	//			raf = new RandomAccessFile(file, "rw");
	//		
	//			
	//			
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//	}

	public void collectPassword(String login) {

		File loginsBDD = new File(loginBDDPath);
		byte actualReadByte;
		byte[] loginFromBDD;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String convertedLogin;
		String password = "";
		RandomAccessFile raf = null;

		try {

			raf = new RandomAccessFile(loginsBDD, "rw");

			do {
				while ((actualReadByte = raf.readByte()) != 0) {			

					outputStream.write(actualReadByte);

				}

				loginFromBDD = outputStream.toByteArray();
				convertedLogin = new String(loginFromBDD);

				if(convertedLogin.equals(login)) {

					password = raf.readLine();

				}

				System.out.println(password);

			//On Balaye chaque ligne 
			//si le premier caractere est similaire alors 
			//on compare les bytes avec les caracteres:
			//On recupere chaque byte que l'on caste en char
			//On compare les deux ensuite
			//Si equivalence on renvoi le password


			//Sinon on continue de balayer les lignes
			//Fin si rien trouvé
			//Le compte n'existe pas





		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
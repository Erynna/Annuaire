package fr.eql.ai108.model;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AdminUserDao {

	private String loginBDDPath = "./login.bin";
	private File loginsBDD = new File(loginBDDPath);
	private long pointerPosition = 0;						//Variable de classe nécessaire afin de stocker l'information de positionnement du RandomAccessFile renvoyé par checkLoginExistence et utilisée dans collectPassword

	/*
	 * Méthode qui permet l'ajout d'un nouvel "Administrateur" possédant un identifiant et un mot de passe personnalisé
	 */
	public void addAdminAccount(String login, String password) {

		FileWriter out = null;
		BufferedWriter bw = null;
		RandomAccessFile raf = null;

		try {

			raf = new RandomAccessFile(loginsBDD, "rw");

			loginsBDD.createNewFile();

			out = new FileWriter(loginsBDD, true);
			bw = new BufferedWriter(out);

			if(!checkLoginExistence(login)) {
				bw.write(login);
				bw.write(0);											//Ecriture d'un octet null afin de séparer les champs Login et Password
				bw.write(password + "\r");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bw.close();
				out.close();
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Méthode qui renvoie le mot de passe administrateur dans le cas où l'identifiant existe bien dans la BDD, et qui retourne "" dans le cas contraire
	 */
	public String collectPassword(String login) {

		String password = "";
		RandomAccessFile raf = null;

		try {

			raf = new RandomAccessFile(loginsBDD, "rw");

			if (checkLoginExistence(login)) {

				raf.seek(pointerPosition);
				password = raf.readLine();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return password;
	}

	/*
	 * Méthode qui renvoie "true" si le login existe dans la BDD et "false" dans le cas contraire
	 */
	public boolean checkLoginExistence(String login) {

		boolean loginExistence = false;
		byte actualReadByte;
		byte[] loginFromBDD;
		RandomAccessFile raf = null;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		String convertedLogin = "";
		int fileLength = 0;

		try {

			raf = new RandomAccessFile(loginsBDD, "rw");
			fileLength = getFileLength(raf);

			if (fileLength > 0) {
				do {

					while ((actualReadByte = raf.readByte()) != 0) {			//Lecture de l'identifiant courant jusqu'au séparateur : octet nul

						outputStream.write(actualReadByte);						//Ajout de chaque octet de données à l'OutputStream afin de recomposer l'identifiant

					}

					loginFromBDD = outputStream.toByteArray();
					outputStream.reset();
					convertedLogin = new String(loginFromBDD);

					if(convertedLogin.equals(login)) {
						pointerPosition = raf.getFilePointer();
						loginExistence = true;
						break;
					}else {
						raf.readLine();
					}

				}while(raf.getFilePointer() < fileLength);						//Bouclage sur la totalité du fichier
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				outputStream.close();
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return loginExistence;
	}

	/*
	 * Méthode qui permet de récupérer la taille des données àl'intérieur de notre fichier
	 */
	private int getFileLength(RandomAccessFile raf) {

		int position = 0;

		try {
			while(raf.readLine() != null) {

				position = (int) raf.getFilePointer();

			}
			raf.seek(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return position;
	}
	
	/*
	 * M�thode qui permet � un admin de se connecter apr�s v�rification de son login et mot de passe --- Premi�re version
	 */
	public boolean connexion(AdminUser admin) {
		
		boolean adminExistence = false;
		byte actualReadByte;
		byte[] loginFromBDD;
		byte[] passwordFromBDD;
		RandomAccessFile raf = null;
		ByteArrayOutputStream opsl = new ByteArrayOutputStream();
		ByteArrayOutputStream opsp = new ByteArrayOutputStream();
		String convertedLogin = "";
		String convertedPassword = "";
		int fileLength = 0;

		try {
			raf = new RandomAccessFile(loginsBDD, "r");
			fileLength = getFileLength(raf);
			if (fileLength > 0) {
				do {
					while ((actualReadByte = raf.readByte()) != '\r') {			//Lecture jusqu'au saut de ligne
						while ((actualReadByte = raf.readByte()) != 0) {
							opsl.write(actualReadByte);
						}
						opsp.write(actualReadByte);						
					}
					loginFromBDD = opsl.toByteArray();
					opsl.reset();
					convertedLogin = new String(loginFromBDD);
					passwordFromBDD = opsp.toByteArray();
					opsp.reset();
					convertedPassword = new String(passwordFromBDD);
					AdminUser admin1 = new AdminUser(convertedLogin, convertedPassword);
					if(admin1.equals(admin)) {
						pointerPosition = raf.getFilePointer();
						adminExistence = true;
						break;
					}else {
						raf.readLine();
					}
				} while(raf.getFilePointer() < fileLength);						//Bouclage sur la totalité du fichier
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				opsl.close();
				opsp.close();
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return adminExistence;
	}

	//Deuxi�me version

	public boolean connection(AdminUser user) {
		RandomAccessFile raf = null;
		boolean verification = false;
		String txt;
		
		try {
			raf = new RandomAccessFile(loginsBDD, "r");
			if(raf.readLine() != "\r\n") {
				txt = raf.readLine();
				String[] info = txt.split(" ");
				AdminUser admin = new AdminUser(info[0], info[1]);
				if(admin.equals(user)) {
					verification = true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return verification;
	}
	
}
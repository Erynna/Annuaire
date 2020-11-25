package fr.eql.ai108.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InternProfileDao {
	
	private String internBDD = "./internBDD.bin";
	
	//M�thode pour r�cup�rer les donn�es d'un stagiaire
	private InternProfile stringToIP(String line) {
		String[] info = line.split("");		//Ajouter le caract�re choisi pour s�parer les infos d'un stagiaire
		InternProfile internProfile = new InternProfile(info[0], info[1], Integer.parseInt(info[2]), info[3], Integer.parseInt(info[4])); // promotion string ou integer ?
		return internProfile;
	}
	
	//Lecture du fichier et instanciation d'objet internprofil
	public  List<InternProfile> getAll() {
		List<InternProfile> internProfiles = new ArrayList<InternProfile>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(internBDD);
			br = new BufferedReader(fr);
			String line = "";
			while((line = br.readLine()) != null) {
				InternProfile internProfile = stringToIP(line);
				internProfiles.add(internProfile);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return internProfiles;
	}
	
	
	

}

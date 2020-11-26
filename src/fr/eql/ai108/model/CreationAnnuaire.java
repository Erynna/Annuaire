package fr.eql.ai108.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CreationAnnuaire {

	private static String originalFilePath = "./stagiaires.txt";
	private static String internsBDDPath = "./internBDD.bin";

	public CreationAnnuaire() {
		super();
	}

	public List<InternProfile> extractInternDatas() {

		BufferedReader br = null;
		List<InternProfile> internProfiles = new ArrayList<InternProfile>();
		
		try {
			br = new BufferedReader(new FileReader(originalFilePath));
			String line;
			
			while ((line = br.readLine()) != null) { 
				
				InternProfile internProfile = new InternProfile();
				
				for (int i = 0; i < 5; i++) {      //Parcourir chacune des 5 lignes qui composent l'internProfile (profil du stagiaire)	
					switch (i) {
					case 0:
						internProfile.setSurname(line.trim());
						break;
					case 1:
						internProfile.setFirstName(line.trim());
						break;
					case 2:
						internProfile.setCounty(Integer.parseInt(line.trim()));
						break;
					case 3:
						internProfile.setPromotion(line.trim());
						break;
					case 4:
						internProfile.setStudyYear(Integer.parseInt(line.trim()));
						break;
					default:
						break;
					}
					line = br.readLine();
				}
				internProfiles.add(internProfile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return internProfiles;
	}
	
	public void createInternsBDDFile() {

		//Créer le fichier de BDD au même endroit que le fichier stagiaire.txt
		File internsBDD = new File(internsBDDPath);
		RandomAccessFile raf = null;
		Byte leftChildPosition;
		Byte rightChildPosition;

		//***************************************************************************************************
		InternProfile alphonse = new InternProfile("Dyriner", "Alphonse", "94", "AI 100", 2013, null, null); // A RETIRER!!!!!!!!!!!!
		InternProfile clement = new InternProfile("Roger", "Clement", "91", "AI 10", 2003, null, null);
		InternProfile bob = new InternProfile("Bob", "boby", "95", "AI 104", 2014, alphonse, clement);
		InternProfile manon = new InternProfile("Giogi", "Manon", "93", "AI 103", 2004, null, null);
		InternProfile sylvie = new InternProfile("Tellier", "Sylvie", "91", "BR 43", 2008, null, null);
		InternProfile orient = new InternProfile("Marcopolo", "Orient", "93", "AI 103", 2004, manon, sylvie);
		InternProfile didier = new InternProfile("johns", "Didier", "95", "BA 40", 2013, bob, null);
		InternProfile lucien = new InternProfile("Sanges", "Lucien", "77", "DO 40", 2013, didier, orient);
		//****************************************************************************************************

		try {
			internsBDD.createNewFile();
			raf = new RandomAccessFile(internsBDD, "rw");

			//Récupérer le profil stagiaire parent racine (median de notre liste)


			//RECURCIVITE : Pour chaque Profil de stagiaire récupérer ses profil stagiaires enfants
			writeChildrenInterns(lucien, raf);
			


		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String concatenateInternDatas(InternProfile internProfile) {

		return (internProfile.getSurname() + "_" + internProfile.getFirstName() 
		+ "_" + internProfile.getCounty() + "_" + internProfile.getPromotion() 
		+ "_" + internProfile.getStudyYear());

	}

	private void writeChildrenInterns(InternProfile internProfile, RandomAccessFile raf) {

		InternProfile leftChild = internProfile.getLeftChild();
		InternProfile rightChild = internProfile.getRightChild();
		int leftChildPosition = 0;
		int rightChildPosition = 0;
		long parentPosition = 0;

		try {
			
			
			raf.writeBytes(internProfile.getFirstName());								//Traitement
			
			if(leftChild != null && rightChild == null){								//Recursivité à Gauche

				leftChildPosition = (int) raf.getFilePointer();
				raf.writeInt(leftChildPosition);
				writeChildrenInterns(leftChild, raf);

			}else {
				
				leftChildPosition = (int) raf.getFilePointer();
				raf.writeInt(leftChildPosition);
				raf.writeInt(0);
				raf.writeBytes(";");
				writeChildrenInterns(leftChild, raf);
				
			}
			
			if(rightChild != null) {													//Recursivité à Droite

				writeChildrenInterns(rightChild, raf);

			}
			
			if (leftChild == null && rightChild == null) {
				
				raf.writeInt(0);
				raf.writeInt(0);
				raf.writeBytes(";");
				
			}
		
			




		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}

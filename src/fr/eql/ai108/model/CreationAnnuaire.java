package fr.eql.ai108.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreationAnnuaire {

	private static String originalFilePath = "./stagiaires.txt";
	private static String internBDD = "./internBDD.bin";

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
				
				for (int i = 0; i < 5; i++) {
					
					switch (i) {
					case 0:
						internProfile.setSurname(line.trim());
						break;
					case 1:
						internProfile.setFirstName(line.trim());
						break;
					case 2:
						internProfile.setCounty(line.trim());
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
}

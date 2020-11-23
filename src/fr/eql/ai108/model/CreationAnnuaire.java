package fr.eql.ai108.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreationAnnuaire {

	private static String originalFilePath = "./stagiaires.txt";
	private static String internBDD = "./internBDD.bin";

	private int maxSurnameSize = 0;
	private int maxFirstNameSize = 0;
	private int maxCountySize = 0;
	private int maxPromSize = 0;
	private int maxStudyYearSize = 0;
	private int internDataSize = 0;

	public CreationAnnuaire() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void computeMaxSize() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(originalFilePath));
			String line = br.readLine();

			int i = 0;
			while (line != null) {
				if (!line.equals("*")) {
						// i correspond au saut de ligne. En fonction de la valeur de i calcul la taille max de la variable.
					switch (i) {
					case 0:
						if (line.length() > maxSurnameSize) {
							maxSurnameSize = line.length();
						}
						break;
					case 1:
						if (line.length() > maxFirstNameSize) {
							maxFirstNameSize = line.length();
						}
						break;
					case 2:
						if (line.length() > maxCountySize) {
							maxCountySize = line.length();
						}
						break;
					case 3:
						if (line.length() > maxPromSize) {
							maxPromSize = line.length();
						}
						break;
					case 4:
						if (line.length() > maxStudyYearSize) {
							maxStudyYearSize = line.length();
						}
						break;
					}
					i++;
				} else {
					i = 0;
				}
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
		internDataSize = maxSurnameSize + maxFirstNameSize + maxCountySize + maxPromSize + maxStudyYearSize;
	}






}

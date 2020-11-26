package fr.eql.ai108.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Classe utilitaire qui permet d'extraire ligne � ligne des donn�es d'un fichier texte pour en faire des stagiaires 
 * qui s'inscrivent dans un arbre de stagiaires.
 */
public class CreationAnnuaire {

	private final String originalFilePath = "./stagiaires.txt";
	private final String internBDD = "./internBDD.bin";

	public CreationAnnuaire() {
		super();
	}

	/*
	 * m�thode qui cr�e un arbre binaire � partir du fichier dont le chemin est renseign� dans la variable originalFilePath.
	 * Le premier stagiaire extrait du fichier constitue le stagiaire racine (le "patriarche"). Chaque stagiaire qui est ensuite 
	 * extrait du fichier est ajout� � la descendance de la racine au fur et � mesure de la lecture du fichier source.
	 * @ return : un objet de type InternProfile qui contient dans ses propri�t�s leftChild et rightChild toute sa descendance.
	 */
	public InternProfile createTree() {

		BufferedReader br = null;
		InternProfile internProfile = null;
		try {
			br = new BufferedReader(new FileReader(originalFilePath));
			String line;
			
			while ((line = br.readLine()) != null) { 
				
				InternProfile internProfile = new InternProfile();
				
				for (int i = 0; i < 5; i++) {      //Parcourir chacune des 5 lignes qui composent l'internProfile (profil du stagiaire)
					
				String surname = "";
				String firstName = "";
				String county = "";
				String promotion = "";
				int studyYear = 0;
				//Parcourt chacune des 5 lignes pour en d�duire le nom, le pr�nom, le d�partement, la promotion et l'ann�e 
				//d'�tude du stagiaire
				for (int i = 0; i < 5; i++) {      
					switch (i) {
					case 0:
						surname = line.trim();
						break;
					case 1:
						firstName = line.trim();
						break;
					case 2:
						county = line.trim();
						break;
					case 3:
						promotion = line.trim();
						break;
					case 4:
						studyYear = Integer.parseInt(line.trim());
						break;
					}
					line = br.readLine();
				}
				//Inscrit le stagiaire dans une lign�e de stagiaires
				internProfile.addChild(surname, firstName, county, promotion, studyYear);
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
	return internProfile;	
	}
	
	/*
	 * M�thode qui permet de chercher, dans la descendance d'un InternProfile pass� en argument, un InternProfile.
	 * @param :  - internProfileFather : un objet de type InternProfile (pour une recherche compl�te, passer ici 
	 * 				en argument l'InternProfile racine)
	 * 			 - internProfile : un objet de type internProfile qu'on veut trouver dans la descendance du internProfileFather
	 * @return : null si aucun objet trouv� sinon un objet de type InternProfile qui repr�sente l'objet cherch� 
	 */
	public InternProfile findInternProfile(InternProfile internProfileFather, InternProfile internProfile) {
		//instanciation d'un objet InternProfileComparator pour comparer les 2 objets pass�s en argument
		InternProfileComparator comparator = new InternProfileComparator();
		int resultCompararison = comparator.compare(internProfile,internProfileFather);
		switch (resultCompararison) {
		//si le r�sultat de la comparaison =-1 (i.e l'objet internProfile est plus petit que internProfileFather), 
		//on oriente la recherche sur l'enfant gauche
		case -1:
			if (internProfileFather.getLeftChild().isEmpty) {
				return null;
			}
			else {
				return findInternProfile(internProfileFather.getLeftChild(), internProfile);
			}
		//si le r�sultat de la comparaison =1 (i.e l'objet internProfile est plus grand que internProfileFather), 
		//on oriente la recherche sur l'enfant droit
		case 1:
			if (internProfileFather.getRightChild().isEmpty) {
				return null;
			}
			else {
				return findInternProfile(internProfileFather.getRightChild(), internProfile);
			}
		//si le r�sultat de la comparaison n'est ni 1 ni -1, il est n�cessairement =0. Cela signifie que l'objet internProfileFather
		//est l'objet recherch�. La m�thode renvoie alors internProfileFather.
		default:
			return internProfileFather;
		}
	}
}

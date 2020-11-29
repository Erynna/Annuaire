package fr.eql.ai108.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/*
 * Classe utilitaire qui permet de consulter, ajouter, supprimer, modifier des stagiaires dans le fichier binaire
 * passé en argument dans le constructeur.
 */
public class InternProfileDao {

	private String internBDD;
	
	/*
	 * Pour créer une instance de cette classe, il faut passer en argument dans le constructeur le nom de l'annuaire
	 * créé précédemment dans la classe CréationAnnuaire
	 */
	public InternProfileDao(String internBDD) {
		this.internBDD = internBDD;
	}

	/*
	 * Méthode qui lit les bytes correspondant à un stagiaire dans le RandomAccessFile passé en argument et 
	 * renvoie un objet InternProfile
	 * @ param : raf=> un RandomAccessFile
	 * 			cursorPosition=> un int qui représente la position où placer le pointeur dans le raf
	 * @ return : un objet InternProfile constitué à partir des informations lues dans le fichier binaire
	 */
	private InternProfile extractDatasIntern(RandomAccessFile raf, int cursorPosition) {
		
		InternProfile internProfile = null;
		int datasStartPosition = 8;				//Démarrage des données pour chaque stagiaire après 2 x 4 octets (positions des enfants)
		
		try {
			
			raf.seek(cursorPosition + datasStartPosition);
			String surname = raf.readUTF().trim();
			String firstName = raf.readUTF().trim();
			String county = raf.readUTF().trim();
			String promotion = raf.readUTF().trim();
			int studyYear = raf.readInt();
			
			internProfile = new InternProfile(surname, firstName, county, promotion, studyYear);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return internProfile;
	}
	
	/*
	 * Méthode qui place le curseur à la position passée en argument (cette position doit correspondre 
	 * au 1er byte d'un stagiaire), lit la position de l'enfant gauche du stagiaire et renvoie cette position. 
	 * @ param : raf => un objet RandomAccessFile
	 *           cursorPosition => un int qui désigne la position où placer le pointeur dans le fichier binaire
	 * @ return : un int qui désigne la position dans le fichier de l'enfant gauche. 
	 */	
	private int positionLeftChild(RandomAccessFile raf, int cursorPosition) {
		int positionToCatch=0;
		try {
			raf.seek(cursorPosition);
			positionToCatch = raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return positionToCatch;
	}
	
	/*
	 * Méthode qui place le curseur à la position passée en argument (cette position doit correspondre 
	 * au 1er byte d'un stagiaire), lit la position de l'enfant droit du stagiaire et renvoie cette position. 
	 * @ param : raf => un objet RandomAccessFile
	 *           cursorPosition => un int qui désigne la position où placer le pointeur dans le fichier binaire
	 * @ return : un int qui désigne la position dans le fichier de l'enfant droit. 
	 */
	private int positionRightChild(RandomAccessFile raf, int cursorPosition) {
		int positionToCatch=0;
		int byteForRightChild = 4;
		
		try {
			raf.seek(cursorPosition+byteForRightChild);
			positionToCatch = raf.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return positionToCatch;
	}
	
	/*
	 * Méthode récursive qui lit tout le fichier binaire et ajoute tous les stagiaires à la liste internProfiles
	 * passée en argument.
	 * @ param : raf => un RandomAccesFile
	 * 			 cursurPosition => un int qui représente la position à partir de laquelle lire le fichier binaire
	 * 			 internProfiles => une List<InternProfile> dans laquelle ajouter les stagiaires
	 */
	private void addChildToArray (RandomAccessFile raf, int cursorPosition, List<InternProfile> internProfiles) {
		int posLeftChild = positionLeftChild(raf, cursorPosition);
		int posRightChild = positionRightChild(raf, cursorPosition);
		
		if (posLeftChild!=0) {
			addChildToArray(raf, posLeftChild, internProfiles);
		}
		internProfiles.add(extractDatasIntern(raf, cursorPosition));
		if (posRightChild!=0) {
			addChildToArray(raf, posRightChild, internProfiles);
		}
	}
	
	/*
	 * Méthode qui permet d'extraire par ordre alphabétique tous les stagiaires de l'annuaire.
	 * @ return : un objet de type List<InternProfile> qui contient tous les stagiaires de l'annuaire
	 *            classés par ordre alphabétique.
	 */
	public List<InternProfile> getAll() {
		
		RandomAccessFile raf = null;
		int cursorPosition=0;
		List<InternProfile> internProfiles = new ArrayList<InternProfile>();
		try {
			raf = new RandomAccessFile(internBDD, "r");
			addChildToArray(raf, cursorPosition, internProfiles);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return internProfiles;
		}
	}

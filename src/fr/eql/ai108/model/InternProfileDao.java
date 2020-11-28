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
	private final int byteForLeftChild = 0;   			// désigne l'emplacement du 1er byte où est inscrite la position de l'enfant gauche
	private final int byteForRightChild = 4;  			// désigne l'emplacement du 1er byte où est inscrite la position de l'enfant droit
	private final int byteForSurname = 8;     			// désigne l'emplacement du 1er byte où est inscrit le nom du stagiaire
	private final int numberOfBytesForSurname = 21;		// désigne le nombre de bytes réservés pour inscrire le nom de famille
	private final int byteForFirstName = 29;			// désigne l'emplacement du 1er byte où est inscrit le prénom du stagiaire
	private final int numberOfBytesForFirstName = 20;	// désigne le nombre de bytes réservés pour inscrire le prénom
	private final int byteForCounty = 49;				// désigne l'emplacement du 1er byte où est inscrit le département du stagiaire
	private final int numberOfBytesForCounty = 2;		// désigne le nombre de bytes réservés pour inscrire le département du stagiaire
	private final int byteForPromotion = 51;			// désigne l'emplacement du 1er byte où est inscrit le nom de la promotion
	private final int numberOfBytesForPromotion = 10;	// désigne le nombre de bytes réservés pour inscrire la promotion
	private final int byteForStudyYear = 61;			// désigne l'emplacement du 1er byte où est inscrite l'année d'étude
	private final int numberOfBytesForStudyYear = 4;	// désigne le nombre de bytes réservés pour inscrire l'année d'étude
	
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
		try {
			//récupère le nom de famille
			raf.seek(cursorPosition+byteForSurname);  
			byte[] bytesSurname = new byte[numberOfBytesForSurname];
			for (int i=0;i<numberOfBytesForSurname;i++) {
				bytesSurname[i]=raf.readByte();
			}
			String surname = new String(bytesSurname);
			//récupère le prénom
			raf.seek(cursorPosition+byteForFirstName);
			byte[] bytesFirstName = new byte[numberOfBytesForFirstName];
			for (int i=0;i<numberOfBytesForFirstName;i++) {
				bytesFirstName[i]=raf.readByte();
			}
			String firstName = new String(bytesFirstName);
			//récupère le département
			raf.seek(cursorPosition+byteForCounty);
			byte[] bytesCounty = new byte[numberOfBytesForCounty];
			for (int i=0;i<numberOfBytesForCounty;i++) {
				bytesCounty[i]=raf.readByte();
			}
			String county = new String(bytesCounty);
			//récupère la promotion
			raf.seek(cursorPosition+byteForPromotion);
			byte[] bytesPromotion = new byte[numberOfBytesForPromotion];
			for (int i=0;i<numberOfBytesForPromotion;i++) {
				bytesPromotion[i]=raf.readByte();
			}
			String promotion = new String(bytesPromotion);
			//récupère l'année d'étude
			raf.seek(cursorPosition+byteForStudyYear);
			byte[] bytesStudyYear = new byte[numberOfBytesForStudyYear];
			for (int i=0;i<numberOfBytesForStudyYear;i++) {
				bytesStudyYear[i]=raf.readByte();
			}
			String studyYear = new String(bytesStudyYear);
			internProfile = new InternProfile(surname.trim(), firstName.trim(), county.trim(), promotion.trim(), Integer.parseInt(studyYear));
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
			raf.seek(cursorPosition+byteForLeftChild);
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

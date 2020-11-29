package fr.eql.ai108.model;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
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
	private final int numberOfBytesForLeftChild = 4;	// désigne le nombre de bytes réservés pour inscrire la position de l'enfant gauche
	private final int byteForRightChild = 4;  			// désigne l'emplacement du 1er byte où est inscrite la position de l'enfant droit
	private final int numberOfBytesForRightChild = 4;	// désigne le nombre de bytes réservés pour inscrire la position de l'enfant droit
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
	
	private int compare(String surname, String firstName, String county, String promotion, int studyYear, int cursorPosition, RandomAccessFile raf) {
		int result=0;
		try {
			//récupère le nom de famille
			raf.seek(cursorPosition+byteForSurname);  
			byte[] bytesSurname = new byte[numberOfBytesForSurname];
			for (int i=0;i<numberOfBytesForSurname;i++) {
				bytesSurname[i]=raf.readByte();
			}
			String surnameRead = new String(bytesSurname);
			//récupère le prénom
			raf.seek(cursorPosition+byteForFirstName);
			byte[] bytesFirstName = new byte[numberOfBytesForFirstName];
			for (int i=0;i<numberOfBytesForFirstName;i++) {
				bytesFirstName[i]=raf.readByte();
			}
			String firstNameRead = new String(bytesFirstName);
			//récupère le département
			raf.seek(cursorPosition+byteForCounty);
			byte[] bytesCounty = new byte[numberOfBytesForCounty];
			for (int i=0;i<numberOfBytesForCounty;i++) {
				bytesCounty[i]=raf.readByte();
			}
			String countyRead = new String(bytesCounty);
			//récupère la promotion
			raf.seek(cursorPosition+byteForPromotion);
			byte[] bytesPromotion = new byte[numberOfBytesForPromotion];
			for (int i=0;i<numberOfBytesForPromotion;i++) {
				bytesPromotion[i]=raf.readByte();
			}
			String promotionRead = new String(bytesPromotion);
			//récupère l'année d'étude
			raf.seek(cursorPosition+byteForStudyYear);
			byte[] bytesStudyYear = new byte[numberOfBytesForStudyYear];
			for (int i=0;i<numberOfBytesForStudyYear;i++) {
				bytesStudyYear[i]=raf.readByte();
			}
			String studyYearRead = new String(bytesStudyYear);
			if (surname.compareTo(surnameRead.trim())>0) {
				result= 1;
			}
			else if (surname.compareTo(surnameRead.trim())<0) {
				result= -1;
			}
			else {
				if (firstName.compareTo(firstNameRead.trim())>0) {
					result= 1;
				}
				else if (firstName.compareTo(firstNameRead.trim())<0) {
					result= -1;
				}
				else {
					if (county.compareTo(countyRead.trim())>0) {
						result= 1;
					}
					else if (county.compareTo(countyRead.trim())<0) {
						result= -1;
					}
					else {
						if (promotion.compareTo(promotionRead.trim())>0) {
							result= 1;
						}
						else if (promotion.compareTo(promotionRead.trim())<0) {
							result= -1;
						}
						else {
							if (studyYear > Integer.parseInt(studyYearRead)) {
								result= 1;
							}
							else if (studyYear < Integer.parseInt(studyYearRead)) {
								result= -1;
							}
							else {
								result= 0;
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private Integer findInternProfile(String surname, String firstName, String county, String promotion, int studyYear, int cursorPosition, RandomAccessFile raf) {
		if (compare(surname, firstName, county, promotion, studyYear, cursorPosition, raf)==1) {
			if(positionRightChild(raf, cursorPosition)==0) {
				return null;
			}
			else { 
				return findInternProfile(surname, firstName, county, promotion, studyYear,positionRightChild(raf, cursorPosition),raf); 
				}
		}
		else if (compare(surname, firstName, county, promotion, studyYear, cursorPosition, raf)==-1) {
			if(positionLeftChild(raf, cursorPosition)==0) {
				return null;
			}
			else { 
				return findInternProfile(surname, firstName, county, promotion, studyYear,positionLeftChild(raf, cursorPosition),raf); 
			}
		}
		else if (compare(surname, firstName, county, promotion, studyYear, cursorPosition, raf)==0) {
			return cursorPosition;
		}
		return null;
	}
	
	private int findLastRightChild(int cursorPosition, RandomAccessFile raf) {
		int posRightChild = positionRightChild(raf, cursorPosition);
		if (posRightChild==0) {
			return cursorPosition;
		}
		else {
			return findLastRightChild(posRightChild, raf);
		}
	}
	
	private void deleteDatas(int cursorPosition, RandomAccessFile raf, boolean deleteChild) {
		try {
			if (deleteChild==true) {
				raf.seek(cursorPosition+byteForLeftChild);
				for (int i=0;i<numberOfBytesForLeftChild;i++) {
					raf.write(' ');
				}
				raf.seek(cursorPosition+byteForRightChild);
				for (int i=0;i<numberOfBytesForRightChild;i++) {
					raf.write(' ');
				}
			}
			raf.seek(cursorPosition+byteForSurname);
			for (int i=0;i<numberOfBytesForSurname;i++) {
				raf.write(' ');
			}
			raf.seek(cursorPosition+byteForFirstName);
			for (int i=0;i<numberOfBytesForFirstName;i++) {
				raf.write(' ');
			}
			raf.seek(cursorPosition+byteForCounty);
			for (int i=0;i<numberOfBytesForCounty;i++) {
				raf.write(' ');
			}
			raf.seek(cursorPosition+byteForPromotion);
			for (int i=0;i<numberOfBytesForPromotion;i++) {
				raf.write(' ');
			}
			raf.seek(cursorPosition+byteForStudyYear);
			for (int i=0;i<numberOfBytesForStudyYear;i++) {
				raf.write(' ');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	private byte[] convertCharToByte(char[] data, int dataMaxLength) {

		byte[] dataInByte = new byte[dataMaxLength];

		for (int i = 0; i < data.length; i++) {

			dataInByte[i] = (byte) data[i];
		}

		if (data.length < dataMaxLength) {

			for (int i = data.length; i < dataMaxLength; i++) {

				dataInByte[i] = 0;

			}
		}

		return dataInByte;
	}
	
	private void copyDatasInFile(InternProfile internProfile, RandomAccessFile raf, int cursorPosition) {
		ByteArrayOutputStream outputStream;
		byte[] fullDatas;
		
		char[] surname = internProfile.getSurname().toCharArray();
		char[] firstName = internProfile.getFirstName().toCharArray();
		char[] county = internProfile.getCounty().toCharArray();
		char[] promotion = internProfile.getPromotion().toCharArray();
		char[] yearStudy = String.valueOf(internProfile.getStudyYear()).toCharArray();

		byte[] surnameByte = convertCharToByte(surname, numberOfBytesForSurname);
		byte[] firstNameByte = convertCharToByte(firstName, numberOfBytesForFirstName);
		byte[] countyByte = convertCharToByte(county, numberOfBytesForCounty);
		byte[] promotionByte = convertCharToByte(promotion, numberOfBytesForPromotion);
		byte[] yearStudyByte = convertCharToByte(yearStudy, numberOfBytesForStudyYear);

		outputStream = new ByteArrayOutputStream(); 
		
		try {
			outputStream.write(surnameByte);
			outputStream.write(firstNameByte); 
			outputStream.write(countyByte); 
			outputStream.write(promotionByte); 
			outputStream.write(yearStudyByte); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		fullDatas = outputStream.toByteArray(); 
		try {
			raf.seek(byteForSurname);   //!!!!!!! réfléchir à comment s'assurer que si l'ordre et la position change, ça marche quand même
			raf.write(fullDatas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteInternProfile(String surname, String firstName, String county, String promotion, int studyYear) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(internBDD, "rw");
			Integer positionInternProfile = findInternProfile(surname, firstName, county, promotion, studyYear, 0, raf);
			int posLeftChild = positionLeftChild(raf, positionInternProfile);
			int posLastRightChild = findLastRightChild(posLeftChild, raf);
			if (positionInternProfile==null) {
				///!!!!!!!!!!!!!!!!
			}
			else if (positionInternProfile==posLastRightChild) {
				//supprimer toutes les données  à posLastRightChild
			}
			else {
				InternProfile internProfile = extractDatasIntern(raf, posLastRightChild);
				//effacer les données à posLastRightChild (toutes les données y compris la position des enfants gauche et droit)
				deleteDatas(positionInternProfile, raf, false);
				//effacer les données à positionInternProfile (sauf les positions des enfants gauche et droit)
				deleteDatas(posLastRightChild, raf, true);
				//copier les données de internProfile à positionInternProfile
				copyDatasInFile(internProfile, raf, positionInternProfile);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

package fr.eql.ai108.model;

/*
 * Classe qui permet de créer des objets de type InternProfile (i.e stagiaire), de leur ajouter un enfant, de 
 * visualiser toute leur descendance.
 */
public class InternProfile {

	private String surname;      		// nom de famille
	private String firstName;			// prénom
	private String county;				// département
	private String promotion;			// nom de la promotion
	private int studyYear;				// année de la promotion
	private InternProfile leftChild;	// enfant gauche
	private InternProfile rightChild;	// enfant droit
	boolean isEmpty;					// permet de savoir si le stagiaire a déjé été renseigné

	public InternProfile() {
		super();
		isEmpty = true;
	}

	public InternProfile(String surname, String firstName, String county, String promotion, int studyYear) {
		super();
		this.surname = surname;
		this.firstName = firstName;
		this.county = county;
		this.promotion = promotion;
		this.studyYear = studyYear;
	}

	/*
	 * Méthode qui permet d'ajouter un enfant é l'objet de la classe InternProfile.
	 * @ param : un surname de type string, un firstName de type String, un county de type String, une promotion
	 * de type String et un studyYear de type int. 
	 */
	public void addChild(String surname, String firstName, String county, String promotion, int studyYear) {
		if (isEmpty) {
			isEmpty = false;
			this.surname = surname;
			this.firstName = firstName;
			this.county = county;
			this.promotion = promotion;
			this.studyYear = studyYear;
			leftChild = new InternProfile();
			rightChild = new InternProfile();
		}
		else if (surname.compareTo(this.surname)<0) {
			leftChild.addChild(surname, firstName, county, promotion, studyYear);
		}
		else if (surname.compareTo(this.surname)>0) {
			rightChild.addChild(surname, firstName, county, promotion, studyYear);
		}
		else {
			if (firstName.compareTo(firstName)<0) {
				leftChild.addChild(surname, firstName, county, promotion, studyYear);
			}
			else if (firstName.compareTo(firstName)>=0) {
				rightChild.addChild(surname, firstName, county, promotion, studyYear);
			}
		}
	}

	/*
	 * Méthode qui permet de visualiser tous les descendants du stagiaire.
	 */
	public void printDescendants() {
		if(!isEmpty) {
			leftChild.printDescendants();
			System.out.println(toString());
			rightChild.printDescendants();
		}
	}
	
	/*
	 * Méthode qui permet de compter le nombre de caracteres spéciaux dans les données (utile pour définir la taille des champs
	 */
	public int countNumberOfSpecialChars(String data) {
		
		int nbOfSpecialChars = 0;
		
		char[] letters = data.toCharArray();
		byte[] lettersInBytes = new byte[letters.length];
		
		for (int i = 0; i < letters.length; i++) {
			
			lettersInBytes[i] = (byte) letters[i];
			
			if (lettersInBytes[i] < 0) {			//Si le byte est inférieur à 0 alors présence d'un caractere spécial car les 0 < bytes < 255
				
				nbOfSpecialChars++;
				
			}
		}
		
		return nbOfSpecialChars;
		
	}
	
	/*
	 * Méthode qui permet de récupérer la taille de l'ensemble des données du stagiaire courant
	 */
	public int getSizeOfCurrentInternDatas() {
		
		int size = 0;
		int sizeOfFixedBytes = 17;				//17 octets dédiés à: la localisation des enfants, la définition de la taille des données et au sépararteur final

		int surnameSize = getSurname().length() + countNumberOfSpecialChars(getSurname());
		int firstNameSize = getFirstName().length() + countNumberOfSpecialChars(getFirstName());
		int countySize = getCounty().length() + countNumberOfSpecialChars(getCounty());
		int promotionSize = getPromotion().length() + countNumberOfSpecialChars(getPromotion());
		int yearStudySize = String.valueOf(getStudyYear()).length();

			size = sizeOfFixedBytes + surnameSize + firstNameSize + countySize + promotionSize + yearStudySize;

		return size;
	}

	/*
	 * Méthode qui permet par récursivité de récupérer la taille de l'ensemble des données de l'arborescence à partir du stagiaire courant
	 */
	public int getSizeOfChildrenDatas() {
		
		int size = 0;

		if(!isEmpty) {
			size = getSizeOfCurrentInternDatas();
			size += leftChild.getSizeOfChildrenDatas();
			size += rightChild.getSizeOfChildrenDatas();
		}

		return size;
	}
	
	@Override
	public String toString() {
		return "InternProfile [surname=" + surname + ", firstName=" + firstName + ", county=" + county + ", promotion="
				+ promotion + ", studyYear=" + studyYear + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((county == null) ? 0 : county.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((promotion == null) ? 0 : promotion.hashCode());
		result = prime * result + studyYear;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InternProfile other = (InternProfile) obj;
		if (county == null) {
			if (other.county != null)
				return false;
		} else if (!county.equals(other.county))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (promotion == null) {
			if (other.promotion != null)
				return false;
		} else if (!promotion.equals(other.promotion))
			return false;
		if (studyYear != other.studyYear)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public int getStudyYear() {
		return studyYear;
	}

	public void setStudyYear(int studyYear) {
		this.studyYear = studyYear;
	}

	public InternProfile getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(InternProfile leftChild) {
		this.leftChild = leftChild;
	}

	public InternProfile getRightChild() {
		return rightChild;
	}

	public void setRightChild(InternProfile rightChild) {
		this.rightChild = rightChild;
	}
}

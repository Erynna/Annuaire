package fr.eql.ai108.model;

public class InternProfile {
	
	private String surname;
	private String firstName;
	private String county;
	private String promotion;
	private int studyYear;
	
	public InternProfile() {
		super();
	}

	public InternProfile(String surname, String firstName, String county, String promotion, int studyYear) {
		super();
		this.surname = surname;
		this.firstName = firstName;
		this.county = county;
		this.promotion = promotion;
		this.studyYear = studyYear;
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
}

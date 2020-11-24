package fr.eql.ai108.model;

import java.util.Comparator;

/*
 * Classe qui permet de comparer des objets InternProfile en fonction de leur nom et prénom.
 */

/*
 * Méthode qui permet de comparer 2 objets de la classe InternProfile
 * @ return : 1 si l'objet 1 est inférieur (dans l'ordre alphabétique) à l'objet 2
 *            -1 si l'objet 1 est supérieur (dans l'ordre alphabétique) à l'objet 2
 *            0 si les deux objets sont égaux (nom et prénom) dans l'ordre alphabétique
 */
public class InternProfileComparator implements Comparator<InternProfile> {

	@Override
	public int compare(InternProfile ip1, InternProfile ip2) {
		if (ip1.getSurname().compareTo(ip2.getSurname())>0) {
			return 1;
		}
		else if (ip1.getSurname().compareTo(ip2.getSurname())<0) {
			return -1;
		}
		else {
			if (ip1.getFirstName().compareTo(ip2.getFirstName())>0) {
				return 1;
			}
			else if(ip1.getFirstName().compareTo(ip2.getFirstName())<0) {
				return -1;
			}
			else {return 0;}
		}
		}
	}
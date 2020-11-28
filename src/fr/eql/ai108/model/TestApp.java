package fr.eql.ai108.model;

import java.util.List;

public class TestApp {

	public static void main(String[] args) {
		
		//permet de tester la classe InternProfileDao
		InternProfileDao testDao = new InternProfileDao("C:\\Users\\formation\\Desktop\\internBDD.bin");
		List<InternProfile> ips = testDao.getAll();
		
		for (InternProfile ip : ips) {
			System.out.println(ip);
		}
		System.out.println(testDao.getAll().indexOf(new InternProfile("ALOJEVIC","Slobodan","93","AI 56",2002)));
		System.out.println(ips.size());
		
//		//Pour tester les classes CreationAnnuaire et InternProfile
//		
		//Cr�ation d'un objet annuaire qui permet de lire le fichier txt et d'en faire un arbre de stagiaires.
		//arbre => stagiaire racine qui contient tous ses enfants
//		CreationAnnuaire annuaire = new CreationAnnuaire();
//		InternProfile arbre = annuaire.createTree();
		
		//M�thode qui permet d'obtenir par ordre alphab�tique tous les stagiaires de l'arbre
//		arbre.printDescendants();
//		
//		//Permet de tester la m�thode findInternProfile avec diff�rents stagiaires
//		InternProfile toto = new InternProfile("MACAMBOU-SILLON","Olivier","92","ATOD 9",2010);
//		if (annuaire.findInternProfile(arbre, toto)==null) {
//			System.out.println("L'objet "+toto+" n'a pas �t� trouv�");
//		}
//		else {
//			System.out.println("L'objet "+toto);
//			System.out.println(" a pour enfant gauche "+annuaire.findInternProfile(arbre, toto).getLeftChild());
//			System.out.println(" et pour enfant droit "+annuaire.findInternProfile(arbre, toto).getRightChild());
//		}
//		
//		InternProfile tata = new InternProfile("FINE","Bruno","77","AI 64",2004);
//		if (annuaire.findInternProfile(arbre, tata)==null) {
//			System.out.println("L'objet "+tata+" n'a pas �t� trouv�");
//		}
//		else {
//			System.out.println("L'objet "+tata);
//			System.out.println(" a pour enfant gauche "+annuaire.findInternProfile(arbre, tata).getLeftChild());
//			System.out.println(" et pour enfant droit "+annuaire.findInternProfile(arbre, toto).getRightChild());
//		}
//		
//		InternProfile titi = new InternProfile("MACRON","Emmanuel","75","AI 01",2004);
//		if (annuaire.findInternProfile(arbre, titi)==null) {
//			System.out.println("L'objet "+titi+" n'a pas �t� trouv�");
//		}
//		else {
//			System.out.println("L'objet "+titi);
//			System.out.println(" a pour enfant gauche "+annuaire.findInternProfile(arbre, titi).getLeftChild());
//			System.out.println(" et pour enfant droit "+annuaire.findInternProfile(arbre, toto).getRightChild());
//		}
	}

}

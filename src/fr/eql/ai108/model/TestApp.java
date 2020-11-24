package fr.eql.ai108.model;

import java.util.List;

public class TestApp {

	public static void main(String[] args) {
		
		CreationAnnuaire annuaire = new CreationAnnuaire();
		
		List<InternProfile> listeProfile = annuaire.extractInternDatas();
		
		for (InternProfile ip : listeProfile) {
			
			System.out.println(ip.toString());
			
		}

	}

}

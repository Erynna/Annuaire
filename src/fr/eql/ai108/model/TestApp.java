package fr.eql.ai108.model;

import java.util.List;

public class TestApp {

	public static void main(String[] args) {
		
		InternProfileDao ipd = new InternProfileDao("C:\\Users\\formation\\Desktop\\internBDD.bin");
		//ipd.deleteInternProfile("LEPANTE", "Willy", "95", "AI 78", 2010);
		for (InternProfile ip : ipd.getAll()) {
			System.out.print(ip);
		}
	}

}

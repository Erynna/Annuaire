package fr.eql.ai108.model;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class InternProfileDao {

	private String internBDD = "test.bin";
	
	private InternProfileAj stringToIP2(String stagiaire) {
		String[] info = stagiaire.split("_");		
		InternProfileAj internProfile = new InternProfileAj(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]));
		return internProfile;
	}
	
	private InternPositionAj stringToPos(String pos) {
		String[] infoPos = pos.split(",");
		InternPositionAj internPosition = new InternPositionAj(Integer.parseInt(infoPos[0]), Integer.parseInt(infoPos[1]));
		return internPosition;
	}
	
	public List<InternProfile> getAll() {
		//List<InternPositionAj> interns = new ArrayList<InternPositionAj>();
		List<InternProfile> internProfiles = new ArrayList<InternProfile>();
		
		//String txt;
		//String txtPos = "";
		RandomAccessFile raf = null;
		int cursorPosition=0;
		//long pp = 0;
		//long posDeb = 0;
		try {
			//Lecture du fichier binaire et conversion en string
			raf = new RandomAccessFile(internBDD, "r");
			//raf.seek(0 + pp);
			raf.seek(cursorPosition);
			if(raf.readLine() != ";") {	
			}else {
				pp = raf.getFilePointer();				//Position du pointeur quant ;
			//Test ?
				System.out.println("pointeur ; " + raf.getFilePointer());
				System.out.println(pp);
				
				if(raf.readLine() != "/") {
				}else {
					long p = raf.getFilePointer();		//Position du pointeur /
			//Test ?		
					System.out.println("pointeur / " + raf.getFilePointer());
					System.out.println(p);
					
					//Lire du deb à la position de / et mettre dans une string
					for (long i = 0; i < p; i++) {
						txt = raf.readLine();
						//Position et récupération des positions
							for (long i1 = p + 1; i1 < pp; i1++) {
								int txtPos1 = raf.readInt();
								txtPos = Integer.toString(txtPos1); //A sup
							}
							//Instanciation d'un stagiaire et ajour dans la liste
						InternProfileAj internProfileAj = stringToIP2(txt);
						InternPositionAj internPosition2 = stringToPos(txtPos);
						InternPositionAj newIntern = new InternPositionAj(internProfileAj.getSurname(), internProfileAj.getFirstName(), internProfileAj.getCounty(), internProfileAj.getPromotion(), internProfileAj.getStudyYear(), internPosition2.getLeftChildPosition(), internPosition2.getRightChildPosition());
						
						interns.add(newIntern);
					}
				}
			}		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return interns;
	}
	
	
	
}

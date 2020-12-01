package fr.eql.ai108.ihm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HBoxSearchOptions extends HBox {
	
	private Button searchBtn = new Button("Rechercher");
	private Button addBtn = new Button("Ajouter");
	private Button creationbtn = new Button("Création annuaire");
	 
		public HBoxSearchOptions() {
		super();	
		setSpacing(10);
		setPadding(new Insets(10.));
		
		
		searchBtn.setPrefSize(100, 30);
		addBtn.setPrefSize(100, 30);
		creationbtn.setPrefSize(100, 30);
		
		getChildren().addAll(searchBtn, addBtn, creationbtn);
				
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				PopUpWindowAddProfile popUp = new PopUpWindowAddProfile();
				Stage dialog = new Stage();
		        dialog.initStyle(StageStyle.UTILITY);
		        popUp.setPopUpWindow(dialog);
		        Scene scene = new Scene(popUp);
		        dialog.setScene(scene);
		        dialog.show();
				
			}
		});
		
		//Ajout de la fonctionnalité création d'un annuaire dans la Hbox -> ouverture d'une fenêtre

		creationbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CreationPan root = new CreationPan();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setTitle("Création de l'annuaire");
				stage.setScene(scene);
				stage.show();	
			}
		});
//		
//		deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//					@Override
//					public void handle(ActionEvent event) {
//						ObservableList<InternProfile> observableProfiles = FXCollections.observableArrayList(dao.getAll);
//						String surname = txtSurname.getText().toUpperCase();
//						String firstname = txtFirstname.getText().substring(0,1).toUpperCase() + txtFirstname.getText().substring(1).toLowerCase();
//						String county = txtCounty.getText().toUpperCase();
//						String promotion = txtPromotion.getText().toUpperCase();
//						int studyYear = Integer.parseInt(txtStudyYear.getText());
//						
//						boolean canDelete = true
//						for (InternProfile observableProfile : observableProfiles) {
//							InternProfileComparator internProfileComparator = new InternProfileComparator();
//							if(internProfileComparator.equals(internProfile, observableProfile)) {
//							//Prendre les infos du stagiaire et les distribuer dans une nouvelle fenêtre pour vérification avant effaçage.
//								}
//							if canDelete = false;						
//							Alert alert = new Alert(AlertType.INFORMATION);
//					        alert.setTitle("Message d'alerte");
//					        alert.setHeaderText("Profil inexistant");
//					        alert.setContentText("Vous ne pouvez pas supprimer un stagiaire qui n'existe pas");
//					        alert.showAndWait();
//							break;
//								
//							}
//					}
//		});
//		
//		updateBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				ObservableList<InternProfile> observableProfiles = FXCollections.observableArrayList(dao.getAll);
//				String surname = txtSurname.getText().toUpperCase();
//				String firstname = txtFirstname.getText().substring(0,1).toUpperCase() + txtFirstname.getText().substring(1).toLowerCase();
//				String county = txtCounty.getText().toUpperCase();
//				String promotion = txtPromotion.getText().toUpperCase();
//				int studyYear = Integer.parseInt(txtStudyYear.getText());
//				
//				InternProfile internProfile = new InternProfile(surname, firstname, county, promotion, studyYear);
//				
//				boolean canUpdate = true;
//				for (InternProfile observableProfile : observableProfiles) {
//					InternProfileComparator internProfileComparator = new InternProfileComparator();
//					if(internProfileComparator.equals(internProfile, observableProfile)) {
//						//Prendre les infos du stagiaire et les distribuer dans des champs pour modification
//					}
//					if canUpdate = false;						
//					Alert alert = new Alert(AlertType.INFORMATION);
//			        alert.setTitle("Message d'alerte");
//			        alert.setHeaderText("Profil inexistant");
//			        alert.setContentText("Vous ne pouvez pas modifier un stagiaire qui n'existe pas/");
//			        alert.showAndWait();
//					break;
//				}
//					}
//				
//			}
//			
//			
//		});

		
		}

	public Button getSearchBtn() {
		return searchBtn;
	}
	public void setSearchBtn(Button searchBtn) {
		this.searchBtn = searchBtn;
	}
	public Button getAddBtn() {
		return addBtn;
	}
	public void setAddBtn(Button addBtn) {
		this.addBtn = addBtn;
	}

	public Button getCreationbtn() {
		return creationbtn;
	}

	public void setCreationbtn(Button creationbtn) {
		this.creationbtn = creationbtn;
	}
	

}

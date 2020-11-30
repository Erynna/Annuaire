package fr.eql.ai108.ihm;

import java.io.File;

import fr.eql.ai108.model.InternProfile;
import fr.eql.ai108.model.InternProfileComparator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HBoxSearchOptions extends HBox {

	private Button importBDDBtn = new Button("Importer Base de Donnée");
	private Button searchBtn = new Button("Rechercher");
	private Button addBtn = new Button("Ajouter");
	private Button deleteBtn = new Button("Supprimer");
	private Button updateBtn = new Button("Actualiser la liste");
	private VBoxSearchOptions searchBox = new VBoxSearchOptions();

	public HBoxSearchOptions() {
		super();


		getChildren().addAll(importBDDBtn, searchBtn, addBtn, deleteBtn, updateBtn);
		
		setSpacing(10);
		setPadding(new Insets(5));

		importBDDBtn.setPrefSize(200, 40);
		searchBtn.setPrefSize(100, 40);
		addBtn.setPrefSize(100, 40);
		deleteBtn.setPrefSize(100, 40);
		updateBtn.setPrefSize(120, 40);

//		addBtn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				PopUpWindowAddProfile popUp = new PopUpWindowAddProfile();
//				Stage dialog = new Stage();
//				dialog.initStyle(StageStyle.UTILITY);
//				popUp.setPopUpWindow(dialog);
//				Scene scene = new Scene(popUp);
//				dialog.setScene(scene);
//				dialog.show();
//			}
//		});
		
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				MainPannel root = (MainPannel) getScene().getRoot();
				
				root.setLeft(searchBox);
				
			}
		});
		
		importBDDBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				FileChooser fileChooser = new FileChooser();
				File initDir = new File("C:/Annuaire_BDD");
				fileChooser.setInitialDirectory(initDir);
				File file = fileChooser.showOpenDialog(null);
				
				MainPannel root = (MainPannel) getScene().getRoot();
				
				TableViewInternProfiles tableNew = new TableViewInternProfiles(file.getAbsolutePath());
			
				root.setCenter(tableNew);
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
		//						
		//
		//						
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

	public Button getImportBDD() {
		return importBDD;
	}


	public void setImportBDD(Button importBDD) {
		this.importBDD = importBDD;
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

	public Button getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(Button deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public Button getUpdateBtn() {
		return updateBtn;
	}

	public void setUpdateBtn(Button updateBtn) {
		this.updateBtn = updateBtn;
	}

}

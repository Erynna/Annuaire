package fr.eql.ai108.ihm;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HBoxSearchOptions extends HBox {
	
	private TextField textField;
	private Button searchBtn;
	private Button addBtn;
	private Button deleteBtn;
	private Button updateBtn;
	 

	
		public HBoxSearchOptions() {
		super();
	
		
		
		setSpacing(10);
		//root.setPadding(new Insets(10,10,10,10));
		
		textField = new TextField();
		textField.setPrefWidth(200);
		
		searchBtn = new Button("Rechercher");
		searchBtn.setPrefSize(100, 30);
		
		addBtn = new Button("Ajouter");
		addBtn.setPrefSize(100, 30);
		
		deleteBtn = new Button("Supprimer");
		deleteBtn.setPrefSize(100, 30);
		
		updateBtn = new Button("MàJ");
		updateBtn.setPrefSize(100, 30);
		
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
		
		getChildren().addAll(textField, searchBtn, addBtn, deleteBtn, updateBtn);
		
		}

	public TextField getTextField() {
		return textField;
	}

	public void setTextField(TextField textField) {
		this.textField = textField;
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

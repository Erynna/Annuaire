package fr.eql.ai108.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import fr.eql.ai108.model.InternProfile;
import fr.eql.ai108.model.InternProfileComparator;
import fr.eql.ai108.model.InternProfileDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PopUpWindowAddProfile extends GridPane {

	private Label lblSurname;
	private TextField txtSurname;
	private Label lblFirstname;
	private TextField txtFirstname;
	private Label lblCounty;
	private TextField txtCounty;
	private Label lblPromotion;
	private TextField txtPromotion;
	private Label lblStudyYear;
	private TextField txtStudyYear;
	private Button btnAddPopUp;
	private Stage popUpWindow;


	public PopUpWindowAddProfile() {
		super();
		InternProfileDao dao = new InternProfileDao("./internBDD.bin");
		ObservableList<InternProfile> observableProfiles = FXCollections.observableArrayList(dao.getAll());

		lblSurname = new Label("Nom :");
		txtSurname = new TextField();
		addRow(0, lblSurname, txtSurname);

		lblFirstname = new Label("Prénom :");
		txtFirstname = new TextField();
		addRow(1, lblFirstname, txtFirstname);

		lblCounty = new Label("Département :");
		txtCounty = new TextField();
		addRow(2, lblCounty, txtCounty);

		lblPromotion = new Label("Promotion :");
		txtPromotion = new TextField();
		addRow(3, lblPromotion, txtPromotion);

		lblStudyYear = new Label("Année :");
		txtStudyYear = new TextField();
		addRow(4, lblStudyYear, txtStudyYear);

		btnAddPopUp = new Button("Ajouter");
		btnAddPopUp.setPrefSize(100, 50);
		addRow(5, btnAddPopUp);
		setAlignment(Pos.CENTER);
		setVgap(15);
		//setStyle("-fx-background-color: orange");
		setPadding(new Insets(10));

		btnAddPopUp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {			

//				if(txtSurname == null) {
//					txtSurname.setPromptText("Veuillez entrer un nom");
//					txtSurname.setStyle("-fx-text-inner-color: red");
//				}
//				if(txtFirstname == null) {
//					txtFirstname.setPromptText("Veuillez entrer un prénom");
//					txtFirstname.setStyle("-fx-text-inner-color: red");
//				}
//				if(txtCounty == null) {
//					txtCounty.setPromptText("Veuillez entrer un département");
//					txtCounty.setStyle("-fx-text-inner-color: red");
//				}
//				if(txtPromotion == null) {
//					txtPromotion.setPromptText("Veuillez entrer une promotion");
//					txtPromotion.setStyle("-fx-text-inner-color: red");
//				}
//				if(txtStudyYear == null) {
//					txtStudyYear.setPromptText("Veuillez entrer une année");
//					txtStudyYear.setStyle("-fx-text-inner-color: red");
//				}				
//				else
					if (txtSurname != null && txtFirstname != null && txtCounty != null && txtPromotion != null && txtStudyYear != null) {
					String surname = txtSurname.getText().toUpperCase();
					String firstname = txtFirstname.getText().substring(0,1).toUpperCase() + txtFirstname.getText().substring(1).toLowerCase();
					String county = txtCounty.getText().toUpperCase();
					String promotion = txtPromotion.getText().toUpperCase();
					int studyYear = Integer.parseInt(txtStudyYear.getText());


					InternProfile internProfile = new InternProfile(surname, firstname, county, promotion, studyYear);

					boolean canSave = true;
					for (InternProfile observableProfile : observableProfiles) {
						InternProfileComparator internProfileComparator = new InternProfileComparator();
						if(internProfileComparator.compare(internProfile, observableProfile) == 0) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Message d'alerte");
							alert.setHeaderText("Attention doublon");
							alert.setContentText("Vous ne pouvez pas ajouter ce stagiaire car il existe déjà.");
							alert.showAndWait();
							canSave = false;
							break;
						}
					}
					if(canSave) {
						observableProfiles.add(internProfile);
						dao.addInternProfile(internProfile);				
						getPopUpWindow().close();	

					} 
				}
			}
		});





	}

	public Label getLblSurname() {
		return lblSurname;
	}

	public void setLblSurname(Label lblSurname) {
		this.lblSurname = lblSurname;
	}

	public TextField getTxtSurname() {
		return txtSurname;
	}

	public void setTxtSurname(TextField txtSurname) {
		this.txtSurname = txtSurname;
	}

	public Label getLblFirstname() {
		return lblFirstname;
	}

	public void setLblFirstname(Label lblFirstname) {
		this.lblFirstname = lblFirstname;
	}

	public TextField getTxtFirstname() {
		return txtFirstname;
	}

	public void setTxtFirstname(TextField txtFirstname) {
		this.txtFirstname = txtFirstname;
	}

	public Label getLblCounty() {
		return lblCounty;
	}

	public void setLblCounty(Label lblCounty) {
		this.lblCounty = lblCounty;
	}

	public TextField getTxtCounty() {
		return txtCounty;
	}

	public void setTxtCounty(TextField txtCounty) {
		this.txtCounty = txtCounty;
	}

	public Label getLblPromotion() {
		return lblPromotion;
	}

	public void setLblPromotion(Label lblPromotion) {
		this.lblPromotion = lblPromotion;
	}

	public TextField getTxtPromotion() {
		return txtPromotion;
	}

	public void setTxtPromotion(TextField txtPromotion) {
		this.txtPromotion = txtPromotion;
	}

	public Label getLblStudyYear() {
		return lblStudyYear;
	}

	public void setLblStudyYear(Label lblStudyYear) {
		this.lblStudyYear = lblStudyYear;
	}

	public TextField getTxtStudyYear() {
		return txtStudyYear;
	}

	public void setTxtStudyYear(TextField txtStudyYear) {
		this.txtStudyYear = txtStudyYear;
	}

	public Button getBtnAdd() {
		return btnAddPopUp;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAddPopUp = btnAdd;
	}

	public Stage getPopUpWindow() {
		return popUpWindow;
	}

	public void setPopUpWindow(Stage popUpWindow) {
		this.popUpWindow = popUpWindow;
	}


}

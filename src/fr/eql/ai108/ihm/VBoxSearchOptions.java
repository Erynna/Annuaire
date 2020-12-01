package fr.eql.ai108.ihm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VBoxSearchOptions extends VBox {
	
	private Label search = new Label("Critères de Recherche");
	private Label labelSurname = new Label("Nom");
	private TextField textFieldSurname = new TextField();
	private Label labelFirstName = new Label("Prénom");
	private TextField textFieldFirstName = new TextField();
	private Label labelCounty = new Label("Département");
	private TextField textFieldCounty = new TextField();
	private Label labelPromotion = new Label("Promotion");
	private TextField textFieldPromotion = new TextField();
	private Label labelYearStudy = new Label("Année");
	private TextField textFieldYearStudy = new TextField();
	private Button searchBtn = new Button("Rechercher");
	private Button addBtn = new Button("Ajouter");
	private Button deleteBtn;
	private Button updateBtn;
	private VBox vbAdmin;
	
		public VBoxSearchOptions() {
		super();
	
		getChildren().add(search);
		getChildren().addAll(labelSurname, textFieldSurname);
		getChildren().addAll(labelFirstName, textFieldFirstName);
		getChildren().addAll(labelCounty, textFieldCounty);
		getChildren().addAll(labelPromotion, textFieldPromotion);
		getChildren().addAll(labelYearStudy, textFieldYearStudy);
		getChildren().add(searchBtn);
		
		setSpacing(10);
		setPadding(new Insets(5));

		search.setStyle("-fx-font-size: 20");
		textFieldSurname.setPrefWidth(200);
		searchBtn.setPrefSize(200, 30);
	
		searchBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//Appel de la méthode de recherche selon les textfield non vides

			}
		});
		
		
		}

		public Label getSearch() {
			return search;
		}

		public void setSearch(Label search) {
			this.search = search;
		}

		public Label getLabelSurname() {
			return labelSurname;
		}

		public void setLabelSurname(Label labelSurname) {
			this.labelSurname = labelSurname;
		}

		public TextField getTextFieldSurname() {
			return textFieldSurname;
		}

		public void setTextFieldSurname(TextField textFieldSurname) {
			this.textFieldSurname = textFieldSurname;
		}

		public Label getLabelFirstName() {
			return labelFirstName;
		}

		public void setLabelFirstName(Label labelFirstName) {
			this.labelFirstName = labelFirstName;
		}

		public TextField getTextFieldFirstName() {
			return textFieldFirstName;
		}

		public void setTextFieldFirstName(TextField textFieldFirstName) {
			this.textFieldFirstName = textFieldFirstName;
		}

		public Label getLabelCounty() {
			return labelCounty;
		}

		public void setLabelCounty(Label labelCounty) {
			this.labelCounty = labelCounty;
		}

		public TextField getTextFieldCounty() {
			return textFieldCounty;
		}

		public void setTextFieldCounty(TextField textFieldCounty) {
			this.textFieldCounty = textFieldCounty;
		}

		public Label getLabelPromotion() {
			return labelPromotion;
		}

		public void setLabelPromotion(Label labelPromotion) {
			this.labelPromotion = labelPromotion;
		}

		public TextField getTextFieldPromotion() {
			return textFieldPromotion;
		}

		public void setTextFieldPromotion(TextField textFieldPromotion) {
			this.textFieldPromotion = textFieldPromotion;
		}

		public Label getLabelYearStudy() {
			return labelYearStudy;
		}

		public void setLabelYearStudy(Label labelYearStudy) {
			this.labelYearStudy = labelYearStudy;
		}

		public TextField getTextFieldYearStudy() {
			return textFieldYearStudy;
		}

		public void setTextFieldYearStudy(TextField textFieldYearStudy) {
			this.textFieldYearStudy = textFieldYearStudy;
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

		public VBox getVbAdmin() {
			return vbAdmin;
		}

		public void setVbAdmin(VBox vbAdmin) {
			this.vbAdmin = vbAdmin;
		}
}

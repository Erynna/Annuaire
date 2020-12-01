package fr.eql.ai108.ihm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VBoxSearchOptions extends VBox {
	
	private TextField textField;
	private Button searchBtn;
	private Button addBtn;
	private Button deleteBtn;
	private Button updateBtn;
	private VBox vbAdmin;
	

	
		public VBoxSearchOptions() {
		super();
		
		setSpacing(10);
		//root.setPadding(new Insets(10,10,10,10));
		
		textField = new TextField();
		textField.setPrefWidth(200);
		
		searchBtn = new Button("Rechercher");
		searchBtn.setPrefSize(100, 30);
		
		addBtn = new Button("Ajouter");
		addBtn.setPrefSize(100, 30);
		

		
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				PopUpWindowAddProfile root = new PopUpWindowAddProfile();
				Scene scene = new Scene(root);
				Stage stage = (Stage) getScene().getWindow();
				stage.setScene(scene);
				
				
			}
		});
		
		getChildren().addAll(textField, searchBtn, addBtn);
		setSpacing(5.);
		
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

	public VBox getVbAdmin() {
		return vbAdmin;
	}

	public void setVbAdmin(VBox vbAdmin) {
		this.vbAdmin = vbAdmin;
	}

}

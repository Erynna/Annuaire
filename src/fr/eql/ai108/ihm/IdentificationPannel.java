package fr.eql.ai108.ihm;

import fr.eql.ai108.model.AdminUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class IdentificationPannel extends GridPane {
	private Label lblLogin;
	private TextField tfLogin;
	private Label lblPassword;
	private TextField tfPassword;
	private Button btnLambdaUser;
	private Button btnConnexion;
	
	
	public IdentificationPannel() {
		super();
		
		lblLogin = new Label("Login : ");
		tfLogin = new TextField();
		addRow(0, lblLogin, tfLogin);
		
		lblPassword = new Label("Mot de passe : ");
		tfPassword = new TextField();
		addRow(1, lblPassword, tfPassword);
		
		btnConnexion = new Button("Se connecter en tant qu'Administrateur");
		btnConnexion.setPrefSize(400, 300);
		addRow(2, btnConnexion);
		
		btnLambdaUser = new Button("Utilisateur");
		btnLambdaUser.setPrefSize(400, 300);
		addRow(3, btnLambdaUser);
		
		//Si connection admin
		btnConnexion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String lg = tfLogin.getText();
				String ps = tfPassword.getText();
				AdminUser admin = new AdminUser();
				if (lg.equals(admin.getLogin()) && ps.equals(admin.getPassword())) {
					
				}
				
				
			}
		});
		
		
	}




	public Label getLblLogin() {
		return lblLogin;
	}
	public void setLblLogin(Label lblLogin) {
		this.lblLogin = lblLogin;
	}
	public TextField getTfLogin() {
		return tfLogin;
	}
	public void setTfLogin(TextField tfLogin) {
		this.tfLogin = tfLogin;
	}
	public Label getLblPassword() {
		return lblPassword;
	}
	public void setLblPassword(Label lblPassword) {
		this.lblPassword = lblPassword;
	}
	public TextField getTfPassword() {
		return tfPassword;
	}
	public void setTfPassword(TextField tfPassword) {
		this.tfPassword = tfPassword;
	}




	public Button getBtnLambdaUser() {
		return btnLambdaUser;
	}




	public void setBtnLambdaUser(Button btnLambdaUser) {
		this.btnLambdaUser = btnLambdaUser;
	}




	public Button getBtnConnexion() {
		return btnConnexion;
	}




	public void setBtnConnexion(Button btnConnexion) {
		this.btnConnexion = btnConnexion;
	}




	
	
	
	

}

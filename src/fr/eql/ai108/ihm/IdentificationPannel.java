package fr.eql.ai108.ihm;

import java.io.File;

import fr.eql.ai108.model.AdminUser;
import fr.eql.ai108.model.AdminUserDao;
import fr.eql.ai108.model.CreationAnnuaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IdentificationPannel extends VBox {
	private Label lblLogin;
	private TextField tfLogin;
	private HBox hbLog;
	private Label lblPassword;
	private TextField tfPassword;
	private HBox hbPass;
	private Button btnLambdaUser;
	private Button btnConnexion;
	private Button btnCreationAdmin;
	private HBox hbButton;
	private TextField error;
	
	public IdentificationPannel() {
		super();
		
		hbLog = new HBox();
		lblLogin = new Label("Login : ");
		lblLogin.setPrefWidth(150);
		tfLogin = new TextField();
		tfLogin.setPrefWidth(300);
		hbLog.getChildren().addAll(lblLogin, tfLogin);
		hbLog.setAlignment(Pos.TOP_CENTER);
		hbLog.setPadding(new Insets(5.));
		
		hbPass = new HBox();
		lblPassword = new Label("Mot de passe : ");
		lblPassword.setPrefWidth(150);
		tfPassword = new TextField();
		tfPassword.setPrefWidth(300);
		hbPass.getChildren().addAll(lblPassword, tfPassword);
		hbPass.setAlignment(Pos.TOP_CENTER);
		hbPass.setPadding(new Insets(5.));	
		
		hbButton = new HBox();
		btnConnexion = new Button("Se connecter");
		btnConnexion.setPrefSize(150, 100);
		btnLambdaUser = new Button("Utilisateur");
		btnLambdaUser.setPrefSize(150, 100);
		//Cr�er un compte admin
		btnCreationAdmin = new Button("Créer un compte" + "\n   administrateur");
		btnCreationAdmin.setPrefSize(150, 100);
		hbButton.getChildren().addAll(btnConnexion, btnCreationAdmin, btnLambdaUser);
		hbButton.setAlignment(Pos.BOTTOM_CENTER);
		hbButton.setSpacing(15);
		
		getChildren().addAll(hbLog, hbPass, hbButton);
		setPrefSize(600, 200);
		
		//Fonctinnalit�s admin
		Button deleteBtn = new Button("Supprimer");
		deleteBtn.setPrefSize(100, 30);
		
		Button updateBtn = new Button("MàJ");
		updateBtn.setPrefSize(100, 30);
		
		VBox vbAdmin = new VBox();
		vbAdmin.getChildren().addAll(deleteBtn, updateBtn);
		
		error = new TextField("Login et/ou mot de passe incorrect");
		
		
		
		//Si connection admin			//OK sans l'ajout des boutons au tableview
		btnConnexion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String lg = tfLogin.getText();
				String ps = tfPassword.getText();
				boolean verification = false;
				AdminUser admin = new AdminUser(lg, ps); 	
				AdminUserDao dao = new AdminUserDao();
				verification = dao.connexion(admin);
				//Si login et mot de passe correct
				if (verification) {
					//Ajout des fonctionnalités administrateur  --> Vérification à faire
//					MainPannel main = new MainPannel();
//					main.getVbSearchOptions().getChildren().add(vbAdmin);
					
					//Affichage du panneau choix de l'annaire
					ChoicePan root = new ChoicePan();
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setTitle("Choix de l'annuaire");
					stage.setScene(scene);
					stage.show();
					
				//Sinon affichage d'un message d'erreur
				}else {
					getChildren().add(error);
				}
			}
		});
		
		//Si création d'un compte admin, ouverture d'un autre panneau	//OK
		btnCreationAdmin.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent event) {
				CreationAdminPan root = new CreationAdminPan();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setTitle("Création d'un compte administrateur");
				stage.setScene(scene);
				stage.show();
			}
		});
		
		//Connexion en tant qu'utilisateur -> affichage du panneau choix de l'annuaire
		btnLambdaUser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ChoicePan root = new ChoicePan();
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.setTitle("Choix de l'annuaire");
				stage.setScene(scene);
				stage.show();
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
	public HBox getHbLog() {
		return hbLog;
	}
	public void setHbLog(HBox hbLog) {
		this.hbLog = hbLog;
	}
	public HBox getHbPass() {
		return hbPass;
	}
	public void setHbPass(HBox hbPass) {
		this.hbPass = hbPass;
	}
	public Button getBtnCreationAdmin() {
		return btnCreationAdmin;
	}
	public void setBtnCreationAdmin(Button btnCreationAdmin) {
		this.btnCreationAdmin = btnCreationAdmin;
	}
	public HBox getHbButton() {
		return hbButton;
	}
	public void setHbButton(HBox hbButton) {
		this.hbButton = hbButton;
	}




	public TextField getError() {
		return error;
	}




	public void setError(TextField error) {
		this.error = error;
	}

	

}

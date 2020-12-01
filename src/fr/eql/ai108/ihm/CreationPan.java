package fr.eql.ai108.ihm;

import java.io.File;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.MethodAccessor_Integer;

import fr.eql.ai108.model.CreationAnnuaire;
import fr.eql.ai108.model.InternProfile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * Ce panneau permet la création d'un annuaire
 */

public class CreationPan extends VBox {

	private Label lblFile;
	private Button btnBrowse;
	private HBox hbPath;
	private Label lblInfo1;
	private Button btnOk;
	private Label lblInfo2;
	private Label error;

	//Choix des HBox à revoir > Pas lisible

	public CreationPan() {
		super();	

//		hbPath = new HBox();
		lblInfo1 = new Label("Choisissez le fichier à partir duquel vous souhaitez créer un annuaire :");
		btnBrowse = new Button("Rechercher");
		btnOk = new Button("Ok");
//		hbPath.getChildren().addAll(lblInfo1, btnBrowse, btnOk);
//		hbPath.setAlignment(Pos.CENTER);
//		hbPath.setPadding(new Insets(10.));
		getChildren().add(lblInfo1);
		getChildren().addAll(btnBrowse, btnOk);
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10.));
		

		//Choix du fichier d'entrée pour création de l'annuaire
		btnBrowse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser = new FileChooser();
				File f = fileChooser.showOpenDialog(new Stage().getOwner());
				if(f != null) {
					lblFile = new Label();
					lblFile.setText(f.getAbsolutePath());
	//				hbPath.getChildren().add(lblFile);
				}
			}
		});
		//Fait appel à la méthode création d'un annuaire
		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(lblFile.getText() != null) {
					String origin = lblFile.getText();
					System.out.println(origin);
					creation(origin);
					lblInfo2 = new Label("La création de votre annuaire a bien été réalisée");
					getChildren().add(lblInfo2);		
				}
			}
		});

//		getChildren().addAll(hbPath);
		setPrefSize(500, 200);
		
		
	}

	public void creation(String fileOrigin) {
		CreationAnnuaire annuaire = new CreationAnnuaire(fileOrigin);
		annuaire.createInternsBDDFile();
	}

	public Label getLblFile() {
		return lblFile;
	}
	public void setLblFile(Label lblFile) {
		this.lblFile = lblFile;
	}
	public Button getBtnBrowse() {
		return btnBrowse;
	}
	public void setBtnBrowse(Button btnBrowse) {
		this.btnBrowse = btnBrowse;
	}
	public HBox getHbPath() {
		return hbPath;
	}
	public void setHbPath(HBox hbPath) {
		this.hbPath = hbPath;
	}
	public Label getLblInfo1() {
		return lblInfo1;
	}
	public void setLblInfo1(Label lblInfo1) {
		this.lblInfo1 = lblInfo1;
	}
	public Label getLblInfo2() {
		return lblInfo2;
	}
	public void setLblInfo2(Label lblInfo2) {
		this.lblInfo2 = lblInfo2;
	}
	public Button getBtnOk() {
		return btnOk;
	}
	public void setBtnOk(Button btnOk) {
		this.btnOk = btnOk;
	}

	public Label getError() {
		return error;
	}
	public void setError(Label error) {
		this.error = error;
	}

}

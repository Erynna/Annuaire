package fr.eql.ai108.ihm;

import java.io.File;

import fr.eql.ai108.model.CreationAnnuaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChoicePan extends VBox {
	
	private Label lblFile;
	private Button btnBrowse;
	private HBox hbPath;
	private Label lblInfo1;
	private Label lblInfo2;
	private Button btnOk;
	private Button btnBrowse2;
	private Label lblFileBin;
	private HBox hbBin;
	private Button btnOk2;
	
	
				public ChoicePan() {
		super();	
		
		hbPath = new HBox();
		lblInfo1 = new Label("Si vous n'avez pas d'annuaire, veuillez choisir un fichier source");
		btnBrowse = new Button("Rechercher");
		btnOk = new Button("Ok");
		hbPath.getChildren().addAll(lblInfo1, btnBrowse, btnOk);
		hbPath.setAlignment(Pos.CENTER);
		hbPath.setPadding(new Insets(10.));
		
		//Choix du fichier d'entrée pour création de l'annuaire
		btnBrowse.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			FileChooser fileChooser = new FileChooser();
			File f = fileChooser.showOpenDialog(new Stage().getOwner());
			if(f != null) {
				lblFile = new Label();
				lblFile.setText(f.getAbsolutePath());
				hbPath.getChildren().add(lblFile);
			}
			
		}
	});
		//Fait appel à la méthode création d'un annuaire
		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File origin = new File(lblFile.getText());
				CreationAnnuaire annuaire = new CreationAnnuaire(origin);	//Modifier la class
				annuaire.createInternsBDDFile();
			}
		});
		
		hbBin = new HBox();
		lblInfo2 = new Label("Veuillez sélectionner un annuaire :");
		btnBrowse2 = new Button("Rechercher");
		hbBin.getChildren().addAll(lblInfo2, btnBrowse2);
		hbBin.setAlignment(Pos.CENTER);
		hbBin.setPadding(new Insets(10.));
		
		//Choix d'un annuaire à partir d'un fichier binaire
		btnBrowse2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser fileChooser2 = new FileChooser();
				//Mettre à jour en fonction du dossier où sont enregistrer les fichiers binaire
				File init = new File("C:/Users/formation/Documents");		
				fileChooser2.setInitialDirectory(init);
				File file = fileChooser2.showOpenDialog(new Stage().getOwner());
				if(file != null) {
					lblFileBin = new Label();
					lblFileBin.setText(file.getAbsolutePath());
					hbBin.getChildren().add(lblFileBin);
				}
				
			}
			
		});
		
		//Action permettant l'affichage du MainPan
		btnOk2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				File annuaire = new File(lblFileBin.getText());
				//Donner cet annuaire au TableView
				
				
				
				MainPannel root = new MainPannel();  
				Scene scene = new Scene(root);
				Stage stage = (Stage) getScene().getWindow();
				stage.setTitle("Annuaire");
				stage.setScene(scene);
			}
		});
		
	}
				
}

package fr.eql.ai108.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionListener;

import fr.eql.ai108.model.InternProfile;
import fr.eql.ai108.model.InternProfileDao;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {

			VBox root = new VBox();
			
			HBoxSearchOptions hBoxSearchOptions = new HBoxSearchOptions();
			TableViewInternProfiles tableViewInternProfiles = new TableViewInternProfiles();
			
			root.getChildren().add(0, hBoxSearchOptions);
			root.getChildren().add(1, tableViewInternProfiles);
			
			
			//PopUpWindowAddProfile root = new PopUpWindowAddProfile();
			Scene scene = new Scene(root, 1000, 1000);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Intern Tracker");
			primaryStage.show();
	}


public static void main (String[] args) {
	launch (args);
	
//	InternProfileDao dao = new InternProfileDao(); // à revoir après obtention du fichier dao 
	//List<InternProfile> profiles = new ArrayList<InternProfile>();
	
	
	
	
	}
}

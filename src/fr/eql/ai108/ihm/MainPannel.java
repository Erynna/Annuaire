package fr.eql.ai108.ihm;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/*
 * Cette classe correspond au panneau principal regroupant le tableview et les boutons d'actions
 */

public class MainPannel extends BorderPane {
		
	private TableViewInternProfiles tableViewInternProfiles;
	private HBoxSearchOptions hbSearchOptions = new HBoxSearchOptions();
	private Label lblTV = new Label("L'annuaire n'existe pas");
	private Button btnR = new Button("Rafraichir");
//	private Button btnExport = new Button("Exporter");
	
	public MainPannel() {
		super();
		setPrefSize(1000, 700);
		setTop(hbSearchOptions);
		setBottom(btnR);
//		setRight(btnExport);
		
		//Ajout du TableView
		File annuaire = new File("./internBDD.bin");
		if(annuaire.exists()) {
			tableViewInternProfiles = new TableViewInternProfiles();
			setCenter(tableViewInternProfiles);
		}else {
			setCenter(lblTV);
		}
		
		btnR.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(annuaire.exists()) {
					tableViewInternProfiles = new TableViewInternProfiles();
					setCenter(tableViewInternProfiles);
				}
			}
		});
		
		//Exporter le TableView en PDF
//		btnExport.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				
//					
//			}
//		});
	}

	public TableViewInternProfiles getTableViewInternProfiles() {
		return tableViewInternProfiles;
	}
	public void setTableViewInternProfiles(TableViewInternProfiles tableViewInternProfiles) {
		this.tableViewInternProfiles = tableViewInternProfiles;
	}
	public HBoxSearchOptions getHbSearchOptions() {
		return hbSearchOptions;
	}
	public void setHbSearchOptions(HBoxSearchOptions hbSearchOptions) {
		this.hbSearchOptions = hbSearchOptions;
	}

}

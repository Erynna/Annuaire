package fr.eql.ai108.ihm;

import javafx.scene.layout.BorderPane;

/*
 * Cette classe correspond au panneau principal regroupant le tableview et les boutons d'actions
 */

public class MainPannel extends BorderPane {
	
	private TableViewInternProfiles tableViewInternProfiles = new TableViewInternProfiles();
	private VBoxSearchOptions vbSearchOptions = new VBoxSearchOptions(); 	//Quand sa marche remplacer par la hbox
	
	
	public MainPannel() {
		super();
		setLeft(vbSearchOptions);
		setCenter(tableViewInternProfiles);
	}


	public TableViewInternProfiles getTableViewInternProfiles() {
		return tableViewInternProfiles;
	}
	public void setTableViewInternProfiles(TableViewInternProfiles tableViewInternProfiles) {
		this.tableViewInternProfiles = tableViewInternProfiles;
	}
	public VBoxSearchOptions getVbSearchOptions() {
		return vbSearchOptions;
	}
	public void setVbSearchOptions(VBoxSearchOptions vbSearchOptions) {
		this.vbSearchOptions = vbSearchOptions;
	}


}

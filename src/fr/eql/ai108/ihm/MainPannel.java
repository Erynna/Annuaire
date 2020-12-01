package fr.eql.ai108.ihm;

import javafx.scene.layout.BorderPane;

/*
 * Cette classe correspond au panneau principal regroupant le tableview et les boutons d'actions
 */

public class MainPannel extends BorderPane {
	
	private HBoxSearchOptions menuBox = new HBoxSearchOptions();
	private TableViewInternProfiles tableView = new TableViewInternProfiles("./internBDD.bin");
	
	
	public MainPannel() {
		
		setTop(menuBox);
		setCenter(tableView);
		
	}

	public HBoxSearchOptions getMenuBox() {
		return menuBox;
	}


	public void setMenuBox(HBoxSearchOptions menuBox) {
		this.menuBox = menuBox;
	}


	public TableViewInternProfiles getTableView() {
		return tableView;
	}


	public void setTableView(TableViewInternProfiles tableView) {
		this.tableView = tableView;
	}
}

package fr.eql.ai108.ihm;

import fr.eql.ai108.model.InternProfile;
import fr.eql.ai108.model.InternProfileDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class InternPannel extends AnchorPane {
	
	private InternProfileDao dao = new InternProfileDao();
	private ObservableList<InternProfile> observableInternProfiles;
	private TableView<InternProfile> tableview;
	
	@SuppressWarnings("unchecked")
	public InternPannel() {
		super();
		// Affichage des stagiaires à partir d'une liste observable sur une tableview
		observableInternProfiles = FXCollections.observableArrayList(dao.getAll());
		tableview = new TableView<InternProfile>(observableInternProfiles);
		
		TableColumn<InternProfile, String> colSurname = new TableColumn<>("Nom");
		colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		
		TableColumn<InternProfile, String> colFirstName = new TableColumn<>("Prénom");
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<InternProfile, String> colCounty = new TableColumn<>("Département");
		colCounty.setCellValueFactory(new PropertyValueFactory<>("county"));
		
		TableColumn<InternProfile, String> colPromotion = new TableColumn<>("Promotion");
		colPromotion.setCellValueFactory(new PropertyValueFactory<>("promotion"));
		
		TableColumn<InternProfile, String> colStudyYear = new TableColumn<>("Année d'étude");
		colStudyYear.setCellValueFactory(new PropertyValueFactory<>("studyYear"));
		
		tableview.getColumns().addAll(colSurname, colFirstName, colCounty, colPromotion, colStudyYear);
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		getChildren().add(tableview);
		
		//Dimension et ajancement
		setPrefSize(1000, 800);
		AnchorPane.setTopAnchor(tableview, 5.);
		AnchorPane.setBottomAnchor(tableview, 5.);
		AnchorPane.setRightAnchor(tableview, 5.);
		AnchorPane.setLeftAnchor(tableview, 5.);
	}
	
	
	
	public InternProfileDao getDao() {
		return dao;
	}
	public void setDao(InternProfileDao dao) {
		this.dao = dao;
	}
	public ObservableList<InternProfile> getObservableInternProfiles() {
		return observableInternProfiles;
	}
	public void setObservableInternProfiles(ObservableList<InternProfile> observableInternProfiles) {
		this.observableInternProfiles = observableInternProfiles;
	}
	public TableView<InternProfile> getTableview() {
		return tableview;
	}
	public void setTableview(TableView<InternProfile> tableview) {
		this.tableview = tableview;
	}
	
	

}

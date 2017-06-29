package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;

import application.SqliteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Camion;
import model.Moto;
import model.Vehicule;
import model.Voiture;

public class AccueilController implements Initializable{
	@FXML private Button buttonLogout,importBtn;
	@FXML private TableView<Vehicule> tableVehicule;
	@FXML private TableColumn<Vehicule , String > colimmatriculation;
	@FXML private TableColumn<Vehicule , String > coldateEntree;
	@FXML private TableColumn<Vehicule , String > coldateSortie;
	@FXML private TableColumn<Vehicule , String > colmarque;
	@FXML private TableColumn<Vehicule , String > colmodele;
	@FXML private TableColumn<Vehicule , String > colcarburant;
	@FXML private TableColumn<Vehicule , String > coletat;
	@FXML private TextField SearchImmatriculation;
	@FXML private TextField SearchDE;
	@FXML private TextField SearchDS;
	@FXML private TextField SearchMarque;
	@FXML private TextField SearchModel;
	@FXML private TextField SearchCarburant;
	@FXML private TextField SearchEtat;
	@FXML private Label labUser;
	@FXML private Label pageActuelle;

	@FXML private Button buttonLoad;
	@FXML private Button buttonAddVehicule;
	@FXML private Button buttonExcel;
	@FXML private Button Ajouter;
	public static String immatriculatuion;
	public static String dateEntree;
	public static String marque;
	public static String modele;
	public static String etat;
	public static String kilometrage;
	public static String consommation;
	public static String pFiscal;
	public static String etatAssr;
	public static String dateDebut;
	public static String dateFin;








private String user;

	private ObservableList<Vehicule>data;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pageActuelle.setText("Ajouter Véhicule");
		try{
			Connection connection = SqliteConnection.connector();
			if(connection == null) System.out.println("erreur de connexion");
			data = FXCollections.observableArrayList();
			ResultSet res = connection.createStatement().executeQuery("select * from Vehicule");
			while(res.next()){
				if(res.getString(15)=="Moto")
				{
					data.add(new Moto(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11)));
				}
				else if(res.getString(15)=="Voiture")
				{
					data.add(new Voiture(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11), res.getString(12), res.getInt(13)));
				}
				else
				{
					data.add(new Camion(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11), res.getString(12), res.getInt(13),res.getInt(14)));
				}
			}

		}catch(SQLException e){
			System.out.println(e);
		}
		colimmatriculation.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("immatriculation"));
		coldateEntree.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("dateEntreeParc"));

		coldateSortie.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("dateSortieParc"));

		colmarque.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("marque"));

		colmodele.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("modele"));

		colcarburant.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("carburant"));

		coletat.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("etat"));

		tableVehicule.setItems(null);
		tableVehicule.setItems(data);






		LoginController d = new LoginController();
		user = d.user;
		labUser.setText("Bonjour "+d.user);

		tableVehicule.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
		            Node node = ((Node) event.getTarget()).getParent();
		            TableRow row;
		            if (node instanceof TableRow) {
		                row = (TableRow) node;
		            } else {
		                // clicking on text part
		                row = (TableRow) node.getParent();
		            }
		            Vehicule v = new Vehicule();
		            v=(Vehicule) row.getItem();
		            try{
		    			Connection connection = SqliteConnection.connector();
		    			if(connection == null) System.out.println("erreur de connexion");
		    			ResultSet res = connection.createStatement().executeQuery("select *,assurance.datefin from Vehicule,assurance where immatriculation='"+v.getImmatriculation()+"' and assurance.id_assurance=vehicule.id_assurance");
		    			res.next();
		    			dateFin=res.getString("dateFin");
		    			etatAssr = res.getString("etatassr");
		    			immatriculatuion = res.getString(1);
		    			dateEntree = res.getString(2);
		    			marque = res.getString(4);
		    			modele = res.getString(5);
		    			etat = res.getString(6);
		    			kilometrage = res.getString(9);
		    			consommation = res.getString(10);
		    			pFiscal = res.getString(13);
		    				if(res.getString(15).equals("Moto"))
		    				{
		    					try{
		    						Stage primaryStage = null;
		    						primaryStage = (Stage) Ajouter.getScene().getWindow();
		    						FXMLLoader loader = new FXMLLoader();
		    						Pane root = loader.load(getClass().getResource("/view/MotoDetails.fxml").openStream());

		    						Scene scene = new Scene(root);

		    						primaryStage.setScene(scene);
		    						primaryStage.show();

		    					}catch(Exception e){
		    						e.printStackTrace();
		    					}

		    				}
		    				else if(res.getString(15).equals("Voiture"))
		    				{
		    					try{
		    						Stage primaryStage = null;
		    						primaryStage = (Stage) Ajouter.getScene().getWindow();
		    						FXMLLoader loader = new FXMLLoader();
		    						Pane root = loader.load(getClass().getResource("/view/VehiculeDetails.fxml").openStream());

		    						Scene scene = new Scene(root);

		    						primaryStage.setScene(scene);
		    						primaryStage.show();

		    					}catch(Exception e){
		    						e.printStackTrace();
		    					}
		    				}
		    				else  if(res.getString(15).equals("Camion"))
		    				{
		    					try{
		    						Stage primaryStage = null;
		    						primaryStage = (Stage) Ajouter.getScene().getWindow();
		    						FXMLLoader loader = new FXMLLoader();
		    						Pane root = loader.load(getClass().getResource("/view/CamionDetails.fxml").openStream());

		    						Scene scene = new Scene(root);

		    						primaryStage.setScene(scene);
		    						primaryStage.show();

		    					}catch(Exception e){
		    						e.printStackTrace();
		    					}

		    				}

		    		}catch(SQLException e){
		    			System.out.println(e);
		    		}


		        }
		    }
		});




	}
	public void logout(ActionEvent event){
		try{
			Stage st= null;
			st = (Stage) buttonLogout.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/Login.fxml").openStream());
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			st.setScene(scene);
			st.setHeight(700);
			st.setWidth(510);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			st.setX((primScreenBounds.getWidth() - st.getWidth()) / 2);
			st.setY((primScreenBounds.getHeight() - st.getHeight()) / 2);
			st.show();

		}catch (Exception e){
			e.printStackTrace();
		}


	}
	public void acceuil(ActionEvent event){
		try{
			Stage st= null;
			st = (Stage) Ajouter.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/PanelAcceuil.fxml").openStream());
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			st.setScene(scene);
			st.show();

		}catch (Exception e){
			e.printStackTrace();
		}


	}
	public void Retour(){
		try{
			Stage primaryStage = null;
			primaryStage = (Stage) Ajouter.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/PanelAcceuil.fxml").openStream());
			System.out.println("heeere");

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void loadData(ActionEvent event){

		try{
			Connection connection = SqliteConnection.connector();
			if(connection == null) System.out.println("erreur de connexion");
			data = FXCollections.observableArrayList();
			ResultSet res = connection.createStatement().executeQuery("select * from Vehicule");
			while(res.next()){
				if(res.getString(15)=="Moto")
				{
					data.add(new Moto(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11)));
				}
				else if(res.getString(15)=="Voiture")
				{
					data.add(new Voiture(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11), res.getString(12), res.getInt(13)));
				}
				else
				{
					data.add(new Camion(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11), res.getString(12), res.getInt(13),res.getInt(14)));
				}
			}

		}catch(SQLException e){
			System.out.println(e);
		}
		colimmatriculation.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("immatriculation"));
		coldateEntree.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("dateEntreeParc"));

		coldateSortie.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("dateSortieParc"));

		colmarque.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("marque"));

		colmodele.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("modele"));

		colcarburant.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("carburant"));

		coletat.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("etat"));

		tableVehicule.setItems(null);
		tableVehicule.setItems(data);



	}

	public void loadDataSearch(ActionEvent event){
		try{

			String s1 = new String(SearchImmatriculation.getText());
			 String s2 = new String(SearchDE.getText());
			 String s3 = new String(SearchDS.getText());
			 String s4 = new String(SearchMarque.getText());
			 String s5 = new String(SearchModel.getText());
			 String s6 = new String(SearchCarburant.getText());
			 String s7 = new String(SearchEtat.getText());

			Connection connection = SqliteConnection.connector();
			if(connection == null) System.out.println("erreur de connexion");
			data = FXCollections.observableArrayList();
			ResultSet res = connection.createStatement().executeQuery("select * from Vehicule");
			while(res.next()){
				if ((res.getString(1).matches(s1+"(.*)") && res.getString(2).matches(s2+"(.*)") && res.getString(3).matches(s3+"(.*)") && res.getString(4).matches(s4+"(.*)") && res.getString(5).matches(s5+"(.*)") && res.getString(12).matches(s6+"(.*)") && res.getString(6).matches(s7+"(.*)"))){
				if(res.getString(15)=="Moto")
				{
					data.add(new Moto(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11)));
				}
				else if(res.getString(15)=="Voiture")
				{
					data.add(new Voiture(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11), res.getString(12), res.getInt(13)));
				}
				else
				{
					data.add(new Camion(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6), res.getString(7), res.getDouble(8), res.getLong(9), res.getDouble(10), res.getInt(11), res.getString(12), res.getInt(13),res.getInt(14)));
				}
			}
			}

		}catch(SQLException e){
			System.out.println(e);
		}
		colimmatriculation.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("immatriculation"));
		coldateEntree.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("dateEntreeParc"));

		coldateSortie.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("dateSortieParc"));

		colmarque.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("marque"));

		colmodele.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("modele"));

		colcarburant.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("carburant"));

		coletat.setCellValueFactory(new PropertyValueFactory<Vehicule,String>("etat"));

		tableVehicule.setItems(null);
		tableVehicule.setItems(data);



	}
	public void ajouterVehicule() throws Exception{
		Stage st = new Stage();

		Parent rt = FXMLLoader.load(getClass().getResource("/view/ChoixType.fxml"));
		Scene scene = new Scene(rt);
		st.setScene(scene);

		st.initModality(Modality.APPLICATION_MODAL);
		st.initOwner(buttonAddVehicule.getScene().getWindow());
		st.show();

	}




	 public void method(){
			String tt = "";

			try{
				Connection connection = SqliteConnection.connector();
				String queryy = "select type from employee where username='"+user+"'" ;
				ResultSet res = connection.createStatement().executeQuery(queryy);
				res.next();
				tt = res.getString(1);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(tt.equals("user")){
				Notifications.create()
				.title("Impossible d'effectuer cette action")
				.text("Vous n'avez pas le droit d'ajouter un utilisateur !")
				.position(Pos.BOTTOM_RIGHT)
				.showWarning();

			}
			else {
				try{
					Stage primaryStage = null;
					primaryStage = (Stage) Ajouter.getScene().getWindow();
					FXMLLoader loader = new FXMLLoader();
					Pane root = loader.load(getClass().getResource("/view/AjouterUser.fxml").openStream());
					System.out.println("here");
					Scene scene = new Scene(root);

					primaryStage.setScene(scene);
					primaryStage.show();

				}catch(Exception e){
					e.printStackTrace();
				}

			}
	 }
	 public void importer() throws IOException{
			Stage stage = new Stage();
			Parent rt = FXMLLoader.load(getClass().getResource("/view/ImportFile.fxml"));
			Scene scene = new Scene(rt);
			stage.setScene(scene);

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(importBtn.getScene().getWindow());
			stage.show();
		}


	public void exporter(){
		try{
			String query = "select * from Vehicule";
			Connection connection = SqliteConnection.connector();
			ResultSet rs = connection.createStatement().executeQuery(query);

			XSSFWorkbook wk = new XSSFWorkbook();
			XSSFSheet sheet = wk.createSheet("User Details");
			XSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("immatriculation");
			header.createCell(1).setCellValue("dateEntree");
			header.createCell(2).setCellValue("dateSortie");
			header.createCell(3).setCellValue("marque");
			header.createCell(4).setCellValue("modele");
			header.createCell(5).setCellValue("etat");
			header.createCell(6).setCellValue("Assurance");
			header.createCell(7).setCellValue("emission");
			header.createCell(8).setCellValue("kilometrage");
			header.createCell(9).setCellValue("consommation");
			header.createCell(10).setCellValue("age");
			header.createCell(11).setCellValue("carburant");
			header.createCell(12).setCellValue("pfiscal");
			header.createCell(13).setCellValue("poidsmax");
			header.createCell(14).setCellValue("type");


			int index = 1 ;
			while(rs.next()){
				XSSFRow row = sheet.createRow(index);
				row.createCell(0).setCellValue(rs.getString("immatriculation"));
				row.createCell(1).setCellValue(rs.getString("dateEntree"));
				row.createCell(2).setCellValue(rs.getString("dateSortie"));
				row.createCell(3).setCellValue(rs.getString("marque"));
				row.createCell(4).setCellValue(rs.getString("modele"));
				row.createCell(5).setCellValue(rs.getString("etat"));
				row.createCell(6).setCellValue(rs.getString("id_assurance"));
				row.createCell(7).setCellValue(rs.getString("emission"));
				row.createCell(8).setCellValue(rs.getString("kilometrage"));
				row.createCell(9).setCellValue(rs.getString("consommation"));
				row.createCell(10).setCellValue(rs.getString("age"));
				row.createCell(11).setCellValue(rs.getString("carburant"));
				row.createCell(12).setCellValue(rs.getString("pfiscal"));
				row.createCell(13).setCellValue(rs.getString("poidsmax"));
				row.createCell(14).setCellValue(rs.getString("type"));

				index++;
			}
			FileOutputStream fileout = new FileOutputStream("UserDetails.xlsx");
			wk.write(fileout);
			fileout.close();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("c'est fait !!");

		}catch(Exception e){
			e.printStackTrace();
		}


	}
}
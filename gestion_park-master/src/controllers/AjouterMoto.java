package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.SqliteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AjouterMoto implements Initializable {
	@FXML private Button add;
	@FXML private TextField fieldImmatriculation ;
	@FXML private DatePicker fieldEntree ;
	@FXML private DatePicker fieldSortie ;
	@FXML private TextField fieldMarque ;
	@FXML private TextField fieldModele,age;
	@FXML private ComboBox<String> comboetat;
//	@FXML private ComboBox<String> assurance;
	@FXML private DatePicker fieldAssDebut ;
	@FXML private DatePicker fieldAssExp ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboetat.getItems().add("En panne");
		comboetat.getItems().add("Disponible");

//		assurance.getItems().add("Oui");
//		assurance.getItems().add("Non");
		fieldEntree.setStyle("-fx-text-fill: #ffffff; ");
		fieldMarque.setStyle("-fx-text-fill: #ffffff; ");
		fieldImmatriculation.setStyle("-fx-text-fill: #ffffff; ");
		fieldModele.setStyle("-fx-text-fill: #ffffff; ");
	}
	public void addMoto(ActionEvent e) throws SQLException{
		Stage s = (Stage) add.getScene().getWindow();
		Connection connection = SqliteConnection.connector();
		 String s1 = new String(fieldImmatriculation.getText());
		 String s2 = new String(fieldEntree.getValue().toString());
		 String s3 = new String(fieldSortie.getValue().toString());
		 String s4 = new String(fieldMarque.getText());
		 String s5 = new String(fieldModele.getText());
		 String s6 = new String(comboetat.getSelectionModel().getSelectedItem());
		 String s71 = new String(fieldAssDebut.getValue().toString());
		 String s72 = new String(fieldAssDebut.getValue().toString());

		 int s11 = Integer.parseInt(age.getText());
		 String query ="INSERT INTO `assurance`(`etatAssr`, `DateDebut`, `datefin`) VALUES('oui','"+s71+"','"+s72+"')";
		 System.out.println(connection.createStatement().executeUpdate(query));
		 String query2 ="SELECT `id_assurance` FROM `assurance` WHERE DateDebut = '"+s71+"'and Datefin='"+s72+"'";
		 ResultSet re= connection.createStatement().executeQuery(query2);
		 re.next();
		 System.out.println(re.getInt(1));
		 query = "INSERT INTO Vehicule VALUES('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+re.getInt(1)+"','0','0','0','"+s11+"','0','0','0','Voiture')";
		 System.out.println(connection.createStatement().executeUpdate(query));
		s.close();
	}



}
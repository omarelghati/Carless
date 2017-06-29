package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.SqliteConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Camion;
import model.Moto;
import model.Voiture;

public class VueCamionController implements Initializable {

	@FXML private Label lab1;
	@FXML private Label lab2;
	@FXML private Label lab3;
	@FXML private Label lab4;
	@FXML private Label lab5;
	@FXML private Label lab6;
	@FXML private Label lab7;
	@FXML private Label lab8;
	@FXML private Label lab10;
	@FXML private Label lab9;

	@FXML private Button retour;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AccueilController ac = new AccueilController();
		lab1.setText(ac.immatriculatuion);
		lab2.setText(ac.dateEntree);
		lab3.setText(ac.marque);
		lab4.setText(ac.modele);
		lab5.setText(ac.etat);
		lab6.setText(ac.kilometrage);
		lab7.setText(ac.consommation);
		lab8.setText(ac.pFiscal);
		lab10.setText(ac.dateFin);
		lab9.setText(ac.etatAssr);




	}
	public void revenir(){
		try{
			Stage primaryStage = null;
			primaryStage = (Stage) retour.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/Accueil.fxml").openStream());

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
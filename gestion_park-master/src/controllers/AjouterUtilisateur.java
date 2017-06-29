package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import application.SqliteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AjouterUtilisateur implements Initializable {

	@FXML private Button Ajouterb;
	@FXML private Button Ajouter;
	@FXML private Button buttonLogout;


	@FXML private TextField nomm;
	@FXML private TextField prenom;
	@FXML private TextField email;
	@FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private ComboBox<String> comboType;
    @FXML private Label labUser;
    private String user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comboType.getItems().add("Utilisateur");
		comboType.getItems().add("Administrateur");
		LoginController d = new LoginController();
		user = d.user;
		labUser.setText("Bonjour "+d.user);

	}
	public void ajouterUser() throws Exception{
		Connection connection = SqliteConnection.connector();
		Statement st = connection.createStatement();

		 String s1 = new String(username.getText());
		 String s2 = new String(password.getText());
		 String s3 = new String(comboType.getSelectionModel().getSelectedItem());
		 String s4 = new String(email.getText());
		 String s5 = new String(nomm.getText());
		 String s6 = new String(prenom.getText());

		String query = "INSERT INTO employee (`username`, `password`, `type`, `email`, `nom`, `prenom`)  VALUES('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"')";
		int ree = st.executeUpdate(query);
		Notifications.create()
		.title("Ajout Utilisateur")
		.text("L'utilisateur a bien été ajouté!")
		.position(Pos.BOTTOM_RIGHT)
		.showWarning();
	}
	public void AjouterAction(){
		try{
			Stage primaryStage = null;
			primaryStage = (Stage) Ajouter.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/Accueil.fxml").openStream());
			System.out.println("heeere");

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void Retour(){
		try{
			Stage primaryStage = null;
			primaryStage = (Stage) username.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/view/PanelAcceuil.fxml").openStream());

			Scene scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();

		}catch(Exception e){
			e.printStackTrace();
		}

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




}

package controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.UserName;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.LoginModel;

public class LoginController implements Initializable{
	public LoginModel loginModel = new LoginModel();

	@FXML
	private Label isConnected;
	@FXML
	private TextField txtUserName ;
	@FXML
	private PasswordField motDePasse;
	@FXML
	private Button auth ;
	public static String user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        txtUserName.setStyle("-fx-text-fill: #ffffff; ");
        motDePasse.setStyle("-fx-text-fill: #ffffff; ");



		if(loginModel.isDatabaseConnected()){
			//isConnected.setText("connected");
			System.out.println("connected");
		}else {
			//isConnected.setText("not connected");
			System.out.println("not connected");
		}

	}
	public void login(ActionEvent event){
		try{
			if(loginModel.isLogin(txtUserName.getText(), motDePasse.getText())){
				isConnected.setText("your are logged in !");
				user = txtUserName.getText();
				Stage primaryStage = null;
				primaryStage = (Stage) auth.getScene().getWindow();
				ObservableList<Screen> screens = Screen.getScreensForRectangle(new Rectangle2D(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight()));
				Screen screen = Screen.getPrimary();
				Rectangle2D bounds = screen.getVisualBounds();
				primaryStage.setX(bounds.getMinX());
				primaryStage.setY(bounds.getMinY());
				primaryStage.setWidth(bounds.getWidth());
				primaryStage.setHeight(bounds.getHeight());
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/view/PanelAcceuil.fxml").openStream());


				Scene scene = new Scene(root);

				primaryStage.setScene(scene);
				primaryStage.setMaximized(true);


				primaryStage.show();
			}else{
				isConnected.setText("username or password is not correct !");

			}

		}catch(Exception e){
			e.printStackTrace();
			isConnected.setText("username or password is not correct");
		}
	}



}

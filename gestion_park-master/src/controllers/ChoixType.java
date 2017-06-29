package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChoixType implements Initializable {
	@FXML private ComboBox<String> type;
	@FXML private Button buttonValiderChoix;
	@FXML private Pane panechoix;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		type.getItems().add("Voiture");
		type.getItems().add("Camion");
		type.getItems().add("Moto");
	}
	
	public void ajouterVehicule() throws Exception{
		Stage st = new Stage();		
		Stage a= new Stage();
		a= (Stage) buttonValiderChoix.getScene().getWindow();
		a.close();
		String choix =type.getSelectionModel().getSelectedItem();
			if(choix=="Voiture")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("/view/AddVoiture.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="Camion")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("/view/AddCamion.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
			else if(choix=="Moto")
			{
				Parent rt = FXMLLoader.load(getClass().getResource("/view/AddMoto.fxml"));
				Scene scene = new Scene(rt);
				st.setScene(scene);
			}
		
			st.initModality(Modality.APPLICATION_MODAL);
			st.initOwner(buttonValiderChoix.getScene().getWindow());
			st.show();
		

	}

}

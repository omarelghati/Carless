package controllers;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.SqliteConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Statistics implements Initializable{

	@FXML  BarChart<String,Number> graphe;
//	@FXML  BarChart<String,Number> graphe2;
    	@FXML Button vehiculesI_O,vehiculeCum;
    	@FXML Label titreGraphe;
    	Connection connection = SqliteConnection.connector();
    	final String jan = "Janvier";
    	final  String fev = "février";
        final  String mars = "Mars";
        final  String avril = "avril";
        final  String mai = "Mai";
        final  String juin = "Juin";
        final  String juillet = "Juillet";
        final  String aout = "Aout";
        final  String septembre = "Septembre";
        final  String octobre = "Obtobre";
        final  String novembre = "Novembre";
        final  String decembre = "Decembre";
	      XYChart.Series<String,Number> series = new XYChart.Series<>();
	      XYChart.Series<String,Number> series2 = new XYChart.Series<>();
	//System.out.println(Month.of(4).name());

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		graphe.setAnimated(false);
		series2.setName("Véhicules sortis");
		series.setName("Véhicules entrés");

	}
	public void Retour(){
		try{
			Stage primaryStage = null;
			primaryStage = (Stage) vehiculesI_O.getScene().getWindow();
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


@SuppressWarnings("unchecked")
@FXML public void graphe1() throws SQLException {
	titreGraphe.setText("Enrtrée/Sortie des véhicules");
//	graphe2.setVisible(false);
//	graphe1.setVisible(true);
	series.getData().clear();
	series2.getData().clear();
	graphe.getData().remove(series);
	graphe.getData().remove(series2);
	try {

		String reqIn="SELECT  COUNT(immatriculation), MONTH(dateentree) FROM vehicule WHERE YEAR(dateentree) = '2016' GROUP BY  MONTH(dateentree)";
	    String reqOut="SELECT  COUNT(immatriculation),MONTH(dateSortie) FROM vehicule WHERE YEAR(dateSortie) = '2016' GROUP BY  MONTH(dateSortie)";
	    ResultSet resIn,resOut;

		resIn = connection.createStatement().executeQuery(reqIn);
		resOut = connection.createStatement().executeQuery(reqOut);

		int[] resultat = new int[12];

		int[] resultat2 = new int[12];
	    int count =0;
	    while(resIn.next())
	    {
	    	resultat[resIn.getInt(2)-1]=resIn.getInt(1);
	    	//System.out.println(resIn.getInt(2));
	    }
	    for(int i=0;i<12;i++)
	    {
	    	if(resultat[i]==0 && i!=0)
	    		resultat[i]=resultat[i-1];
	    }
	    count=0;
	    while(resOut.next())
	    {
	    	resultat2[resOut.getInt(2)-1]=resOut.getInt(1);
	    }
	    for(int i=0;i<12;i++)
	    {
	    	if(resultat2[i]==0 && i!=0)
	    		resultat2[i]=resultat2[i-1];
	    }
	    series.getData().add(new Data<String, Number>(jan,resultat[0]));
        series.getData().add(new Data<String, Number>(fev, resultat[1]));
        series.getData().add(new Data<String, Number>(mars, resultat[2]));
        series.getData().add(new Data<String, Number>(avril, resultat[3]));
        series.getData().add(new Data<String, Number>(mai, resultat[4]));
        series.getData().add(new Data<String, Number>(juin, resultat[5]));
        series.getData().add(new Data<String, Number>(juillet, resultat[6]));
        series.getData().add(new Data<String, Number>(aout, resultat[7]));
        series.getData().add(new Data<String, Number>(septembre,resultat[8]));
        series.getData().add(new Data<String, Number>(octobre, resultat[9]));
        series.getData().add(new Data<String, Number>(novembre, resultat[10]));
        series.getData().add(new Data<String, Number>(decembre, resultat[11]));

    	series2.getData().add(new Data<String, Number>(jan, resultat2[0]));
        series2.getData().add(new Data<String, Number>(fev, resultat2[1]));
        series2.getData().add(new Data<String, Number>(mars, resultat2[2]));
        series2.getData().add(new Data<String, Number>(avril, resultat2[3]));
        series2.getData().add(new Data<String, Number>(mai, resultat2[4]));
        series2.getData().add(new Data<String, Number>(juin, resultat2[5]));
        series2.getData().add(new Data<String, Number>(juillet, resultat2[6]));
        series2.getData().add(new Data<String, Number>(aout, resultat2[7]));
        series2.getData().add(new Data<String, Number>(septembre,resultat2[8]));
        series2.getData().add(new Data<String, Number>(octobre, resultat2[9]));
        series2.getData().add(new Data<String, Number>(novembre, resultat2[10]));
        series2.getData().add(new Data<String, Number>(decembre, resultat2[11]));

//        graphe.getData().retainAll();


        graphe.getData().addAll(series,series2);

      graphe.getStylesheets().add(getClass().getResource("graphe.css").toExternalForm());

//        for(Node n:graphe.lookupAll(".default-color0.chart-bar")) {
//            n.setStyle("-fx-bar-fill: #daad03;");
//        }
//        for(Node n:graphe.lookupAll(".default-color1.chart-bar")) {
//            n.setStyle("-fx-bar-fill: #303030;");
//        }


	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
//graphe 2

@FXML public void graphe2(){
	titreGraphe.setText("Evolution de nombre des vehicule dans le parc");
//	graphe2.setVisible(true);
//	graphe1.setVisible(false);
	series2.getData().clear();
	series.getData().clear();
	graphe.getData().remove(series2);
	graphe.getData().remove(series);
	try {
		String reqIn="SELECT  COUNT(immatriculation), MONTH(dateentree) FROM vehicule WHERE YEAR(dateentree) = '2016' GROUP BY  MONTH(dateentree)";
	    String reqOut="SELECT  COUNT(immatriculation),MONTH(dateSortie) FROM vehicule WHERE YEAR(dateSortie) = '2016' GROUP BY  MONTH(dateSortie)";
	    ResultSet resIn,resOut;

		resIn = connection.createStatement().executeQuery(reqIn);
		resOut = connection.createStatement().executeQuery(reqOut);

		int[] resultat = new int[12];
	    int count =0;
	    while(resIn.next())
	    {
	    	count+=resIn.getInt(1);
	    	resultat[resIn.getInt(2)-1]=count;
	    	//System.out.println(resIn.getInt(2));
	    }
	    for(int i=0;i<12;i++)
	    {
	    	if(resultat[i]==0 && i!=0)
	    		resultat[i]=resultat[i-1];
	    }
	    while(resOut.next())
	    {
	    	resultat[resOut.getInt(2)-1]-=resOut.getInt(1);
	    }
	    series.getData().add(new Data<String, Number>(jan, resultat[0]));
        series.getData().add(new Data<String, Number>(fev, resultat[1]));
        series.getData().add(new Data<String, Number>(mars, resultat[2]));
        series.getData().add(new Data<String, Number>(avril, resultat[3]));
        series.getData().add(new Data<String, Number>(mai, resultat[4]));
        series.getData().add(new Data<String, Number>(juin, resultat[5]));
        series.getData().add(new Data<String, Number>(juillet, resultat[6]));
        series.getData().add(new Data<String, Number>(aout, resultat[7]));
        series.getData().add(new Data<String, Number>(septembre,resultat[8]));
        series.getData().add(new Data<String, Number>(octobre, resultat[9]));
        series.getData().add(new Data<String, Number>(novembre, resultat[10]));
        series.getData().add(new Data<String, Number>(decembre, resultat[11]));

        graphe.getData().add(series);
        for(Node n:graphe.lookupAll(".default-color0.chart-bar")) {
          n.setStyle("-fx-bar-fill: #daad03;");
        }
        for(Node n:graphe.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: #303030;");
        }
        series.getData().forEach(d->
        d.getNode().setStyle("-fx-bar-fill: #daad03"));
		series2.getData().forEach(d->
        d.getNode().setStyle("-fx-bar-fill: #303030"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
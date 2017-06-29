package model;

import javafx.beans.property.SimpleStringProperty;

public class Vehicule {
	private SimpleStringProperty immatriculation;
	private SimpleStringProperty dateEntreeParc;
	private SimpleStringProperty dateSortieParc;
	private SimpleStringProperty marque;
	private SimpleStringProperty modele;
	private SimpleStringProperty assurance;
	private double emission;
	private long kilometrage;
	private double consommation;
	private int age;
	private SimpleStringProperty etat;
	public Vehicule(){

	}
	public Vehicule(String immatriculation,String dateEntreeParc,String dateSortieParc,String marque,String modele,String etat,double emission,long kilometrage,double consommation,int age,String assurance){
		super();
		this.immatriculation = new SimpleStringProperty(immatriculation);
		this.dateEntreeParc = new SimpleStringProperty(dateEntreeParc);
		this.dateSortieParc = new SimpleStringProperty(dateSortieParc);
		this.marque = new SimpleStringProperty(marque);
		this.modele = new SimpleStringProperty(modele);
		this.emission = emission;
		this.consommation=consommation;
		this.kilometrage=kilometrage;
		this.age=age;
		this.assurance =new SimpleStringProperty(assurance);
		this.etat = new SimpleStringProperty(etat);
	}


	public String getImmatriculation() {
		return immatriculation.get();
	}
	public String getDateEntreeParc() {
		return dateEntreeParc.get();
	}
	public String getDateSortieParc() {
		return dateSortieParc.get();
	}
	public String getMarque() {
		return marque.get();
	}
	public String getModele() {
		return modele.get();
	}

	public double getEmission() {
		return emission;
	}

	public long getKilometrage() {
		return kilometrage;
	}

	public double getConsommation() {
		return consommation;
	}

	public int getAge() {
		return age;
	}

	public String getEtat() {
		return etat.get();
	}

	public SimpleStringProperty getAssurance() {
		return assurance;
	}




}
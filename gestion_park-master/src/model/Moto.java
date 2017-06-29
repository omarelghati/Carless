package model;

public class Moto extends Vehicule {
	
public Moto(String immatriculation, String dateEntreeParc, String dateSortieParc, String marque, String modele,
			String etat,String assurance,double emission, long kilometrage, double consomation, int age) {
		super(immatriculation, dateEntreeParc, dateSortieParc, marque, modele, etat, emission, kilometrage, consomation, age,assurance);
		
}
}

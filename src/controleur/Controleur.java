package controleur;

import metier.Plateau;
import metier.Piece;

import ihm.Frame;

import java.util.*;

public class Controleur
{
	public final int NIVEAU_MAX = 60;

	private Plateau plateau;
	private Frame frame;

	private int niveauActuel = 1;
	private int nbCoup = 0;
	private String message = "";

	public Controleur()
	{
		plateau = new Plateau(this);
		frame = new Frame(this);
	}

	public boolean deplacer(int colDep, int ligDep, int colArr, int ligArr)
	{
		if(this.plateau.deplacer(colDep, ligDep, colArr, ligArr)) {return true;}
		return false;
	}

	public boolean niveauGagne () {return this.plateau.niveauGagne() && this.niveauActuel < this.NIVEAU_MAX;}
	public boolean partieGagne () {return this.plateau.niveauGagne() && this.niveauActuel >= this.NIVEAU_MAX;}

	public void initNiveau() 
	{
		this.plateau.initNiveau(this.niveauActuel);
		this.nbCoup = 0;
		if(this.message.equals("Erreur")) {this.message = "";}
	}

	//getters et setters de la classe Metier
	public List<Piece> getPlateau (){return this.plateau.getPlateau();}

	public void incrNiveauActuel() {if(this.niveauActuel < this.NIVEAU_MAX) {this.niveauActuel++;}}
	public void decrNiveauActuel() {if(this.niveauActuel > 1) this.niveauActuel--;}
	public void choisirNiveauActuel(int nbNiveau) {if(nbNiveau > 0 && nbNiveau <= this.NIVEAU_MAX) {this.niveauActuel = nbNiveau;}}
	public int getNiveauActuel() {return this.niveauActuel;}
	public int getNiveauMax() {return this.NIVEAU_MAX;}

	public char getDifficulte() {return this.plateau.getDifficulte();}
	
	public void incrNbCoup() {this.nbCoup++;}
	public void decrNbCoup() {if(this.nbCoup > 0) {this.nbCoup--;}}
	public int getNbCoup(){ return this.nbCoup;}

	public String getMessage() {return this.message;}

	//Methode réagissant suite au clique des boutons et autre
	public void action (char action)
	{
		switch (action)
		{
			case 'R' : this.initNiveau();break;
			case 'S' : this.incrNiveauActuel();
					   this.initNiveau();break;
			case 'P' : this.decrNiveauActuel();
					   this.initNiveau();break;
			case 'G' : this.incrNbCoup();break;
			case 'E' : this.message = "Erreur";break;
			case 'F' : this.message = "Bravo";break;
			case 'N' : this.message = this.nbCoup + " coups";break;
			case ' ' : this.message = "";break;
		}

	}

	//methode appellé par le ctrl-z
	public void annuler()
	{
		this.plateau.annuler();
		this.decrNbCoup();
	}

	//methode permettant de placer les images sur l'echiquier
	public char getSymbole (int lig, int col) 
	{
		return this.plateau.getPiece(col, lig);
	}

	public static void main(String[] args) 
	{
		Controleur ctrl = new Controleur();
	}
}
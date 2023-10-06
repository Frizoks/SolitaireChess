package metier;

import java.util.*;

public abstract class Piece 
{	
	private int colonne;
	private int ligne;

	public Piece (int colonne, int ligne)
	{
		this.colonne = colonne;
		this.ligne = ligne;
	}

	public int getColonne () {return this.colonne;}
	public int getLigne() {return this.ligne;}
	public abstract char getType();

	public boolean deplacer (int colonne, int ligne, List<Piece> plateau)
	{
		//vérifie si la pièce ne se déplace pas sur elle-même
		if (this.getColonne() == colonne && this.getLigne() == ligne) {return false;}

		//vérifie si le déplacement est possible
		if (!this.peutDeplacer(colonne, ligne, plateau)) {return false;}

		//on supprime la piece qui doit etre prise
		retirer(colonne, ligne, plateau);

		//on modifie les valeurs de la pièce qu'on vient de déplacer
		this.colonne = colonne;
		this.ligne   = ligne;

		return true;
	}

	public void retirer (int col, int lig, List<Piece> plateau)
	{
		Piece piece = null;

		//vérification de l'existence de la piece à retirer
		for (Piece p: plateau)
		{
			if (p.getColonne() == col && p.getLigne() == lig)
				piece = p;
		}
		//suppression
		if(piece != null) {plateau.remove(piece);}
	}

	//methode permettant de savoir si la case où l'on veut deplacer notre piece n'est pas vide et qu'il ne s'agit pas d'un roi
	public boolean priseValide(int col, int lig, List<Piece> plateau)
	{
		for (Piece p: plateau)
		{
			if (p.getColonne() == col && p.getLigne() == lig && p.getType() != 'K') {return true;}
		}
		return false;
	}

	//methode pour savoir si la piece peut se deplacer pour attraper cette piece(à définir précisement pour chaque type de pièce)
	public abstract boolean peutDeplacer (int ligne, int colonne,  List<Piece> plateau);

	/*methode permettant de savoir si il existe une autre piece sur le chemin de notre piece à deplacer, 
	dans le cas échéant notre pièce ne peut pas bouger(à part le cavalier)*/
	public boolean autrePieces(int colDep, int ligDep, int colArr, int ligArr, List<Piece> liste)
	{
		//trouver si l'on se déplace sur une ligne, une colonne ou les deux(diagonale)
		int pasCol = 0, pasLig = 0;
		if(colArr < colDep) {pasCol = -1;}
		if(colArr > colDep) {pasCol =  1;}
		if(ligArr < ligDep) {pasLig = -1;}
		if(ligArr > ligDep) {pasLig =  1;}

		for(int col = colDep + pasCol, lig = ligDep + pasLig; col != colArr || lig != ligArr; col += pasCol, lig += pasLig)
		{
			for(Piece p:liste)
			{
				if(p.getColonne() == col && p.getLigne() == lig) {return true;}
			}
		}
		return false;
	}
}
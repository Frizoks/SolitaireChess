package metier;

import java.util.*;

public class Tour extends Piece
{
	public Tour (int colonne, int ligne)
	{
		super (colonne, ligne);
	}

	public char getType () {return 'T';}

	public boolean peutDeplacer (int colonne, int ligne, List<Piece> plateau)
	{
		if (colonne <  this.getColonne() && ligne == this.getLigne() || 
			colonne == this.getColonne() && ligne <  this.getLigne() ||
			colonne >  this.getColonne() && ligne == this.getLigne() ||
			colonne == this.getColonne() && ligne >  this.getLigne() &&
			!this.autrePieces(this.getColonne(), this.getLigne(), colonne, ligne, plateau))
		{
			if(this.priseValide(colonne, ligne, plateau)) {return true;}
		}
		
		return false;
	}
}

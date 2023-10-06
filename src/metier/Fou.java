package metier;

import java.util.*;

public class Fou extends Piece
{
	public Fou(int col, int lig)
	{
		super(col,lig);
	}

	public char getType() { return 'F';}

	public boolean peutDeplacer(int col, int lig, List<Piece> plateau)
	{
		if(((this.getColonne() == col - 1 && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig - 1) ||
			(this.getColonne() == col - 2 && this.getLigne() == lig - 2) ||
			(this.getColonne() == col + 2 && this.getLigne() == lig + 2) ||
			(this.getColonne() == col - 2 && this.getLigne() == lig + 2) ||
			(this.getColonne() == col + 2 && this.getLigne() == lig - 2) ||
			(this.getColonne() == col - 3 && this.getLigne() == lig - 3) ||
			(this.getColonne() == col + 3 && this.getLigne() == lig + 3) ||
			(this.getColonne() == col - 3 && this.getLigne() == lig + 3) ||
			(this.getColonne() == col + 3 && this.getLigne() == lig - 3)) &&
			!this.autrePieces(this.getColonne(), this.getLigne(), col, lig, plateau))
		{
			if(this.priseValide(col, lig, plateau)) {return true;}
		}
		return false;
	}
}

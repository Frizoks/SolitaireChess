package metier;

import java.util.*;

public class Roi extends Piece
{
	public Roi(int col, int lig)
	{
		super(col,lig);
	}

	public char getType() { return 'K';}

	public boolean peutDeplacer(int col, int lig, List<Piece> plateau)
	{
		if((this.getColonne() == col && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig - 1))
		{
			if(this.priseValide(col, lig, plateau)) {return true;}
		}
		return false;
	}
}

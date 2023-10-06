package metier;

import java.util.List;

public class Cavalier extends Piece
{
	public Cavalier (int colonne, int ligne)
	{
		super (colonne, ligne);
	}

	public char getType() {return 'C';}

	public boolean peutDeplacer (int col, int lig, List<Piece> plateau)
	{
		if ((this.getColonne() == col - 2 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 2 && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig - 2) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig - 2) ||
			(this.getColonne() == col + 2 && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 2 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig + 2) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig + 2))
		{
			if(this.priseValide(col, lig, plateau)) {return true;}
		}

		return false;
	}
}

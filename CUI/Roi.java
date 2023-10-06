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
		/*System.out.println(this.getColonne() + " " + col);
		System.out.println(this.getLigne() + " " + lig);
		System.out.println(this.autrePieces(this.getColonne(), this.getLigne(), col, lig, plateau));*/

		if((this.getColonne() == col && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig - 1) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig) ||
			(this.getColonne() == col + 1 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig + 1) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig) ||
			(this.getColonne() == col - 1 && this.getLigne() == lig - 1))
		{
			if(this.estOccupe(col, lig, plateau)) {return true;}
		}
		return false;
	}
}

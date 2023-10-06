import java.util.*;

public class Dame extends Piece
{
	public Dame(int col, int lig)
	{
		super(col,lig);
	}

	public char getType() { return 'Q';}

	public boolean peutDeplacer(int col,int lig,List<Piece> plateau)
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
			(this.getColonne() == col + 3 && this.getLigne() == lig - 3) ||
			(col <  this.getColonne() && lig == this.getLigne()) || 
			(col == this.getColonne() && lig <  this.getLigne()) ||
			(col >  this.getColonne() && lig == this.getLigne()) ||
			(col == this.getColonne() && lig >  this.getLigne())) &&
			!this.autrePieces(this.getColonne(), this.getLigne(), col, lig, plateau))
		{
			if(this.estOccupe(col, lig, plateau)) {return true;}
		}

		return false;
	}
}

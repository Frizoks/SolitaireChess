import java.util.*;

public class Pion extends Piece
{
	public Pion (int colonne, int ligne)
	{
		super (colonne, ligne);
	}

	public boolean peutDeplacer (int colonne, int ligne, List<Piece> plateau)
	{
		if ((colonne == this.getColonne() + 1 && ligne == this.getLigne() - 1) || 
			(colonne == this.getColonne() - 1 && ligne == this.getLigne() - 1))
		{
			if (this.estOccupe(colonne, ligne, plateau))
			{
				return true;
			}
		}
		return false;

	}

	public char getType() {return 'P';}
}
import java.util.ArrayList;
import java.util.*;
import iut.algo.*;

public abstract class Piece 
{
	public final static int NB_MAX_COLONNE = 4;
	public final static int NB_MAX_LIGNE   = 4;
	
	private int		colonne;
	private int		ligne;

	public Piece (int colonne, int ligne)
	{
		this.colonne   = colonne;
		this.ligne     = ligne;
	}

	public boolean deplacer (int colonne, int ligne, List<Piece> plateau)
	{
		if (this.getColonne() == colonne && this.getLigne() == ligne)
		{
			System.out.println("\n\terreur : ne vous mangez pas vous-meme\n");
			Clavier.lireString();
			return false;
		}
		
		if (!this.peutDeplacer(colonne, ligne, plateau))
		{
			System.out.println("\n\terreur : Déplacement impossible\n");
			Clavier.lireString();
			return false;
		}
		retirer(colonne, ligne, plateau);
		
		this.colonne = colonne;
		this.ligne   = ligne;

		return true;
	}

	public void retirer (int col, int lig, List<Piece> plateau)
	{
		Piece piece = null;
		for (Piece p: plateau)
		{
			if (p.getColonne() == col && p.getLigne() == lig)
				piece = p;
		}
		if(piece != null) {plateau.remove(piece);}
	}

	public boolean estOccupe (int col, int lig, List<Piece> plateau)
	{
		for (Piece p: plateau)
		{
			if (p.getColonne() == col && p.getLigne() == lig)
				return true;
		}

		return false;
	}

	public abstract boolean peutDeplacer (int ligne, int colonne,  List<Piece> plateau);

	public int  getColonne () {return this.colonne;}
	public int  getLigne   () {return this.ligne  ;}

	public boolean estSorti (int colonne, int ligne)
	{
		if (this.colonne < NB_MAX_COLONNE && this.colonne >= 0 &&
		    this.ligne   < NB_MAX_LIGNE   && this.ligne   >= 0)
			return false;
		
		return true;
	}

	public boolean autrePieces(int colDep, int ligDep, int colArr, int ligArr, List<Piece> liste)
	{
		int pasCol = 0, pasLig = 0;
		if(colArr < colDep) {pasCol = -1;}
		if(colArr > colDep) {pasCol = 1;}
		if(ligArr < ligDep) {pasLig = -1;}
		if(ligArr > ligDep) {pasLig = 1;}

		//System.out.println("Piece : autre piece");

		for(int col = colDep + pasCol, lig = ligDep + pasLig; col != colArr || lig != ligArr; col += pasCol, lig += pasLig)
		{
			for(Piece p:liste)
			{
				if(p.getColonne() == col && p.getLigne() == lig) {return true;}
			}
		}

		//System.out.println("Piece : autre piece (false)");
		return false;
	}

	public abstract char getType();
}
import java.util.*;
import java.util.Scanner;
import java.io.FileInputStream;
import iut.algo.*;

public class Plateau 
{
	private List<Piece>	plateau;
	private char		difficulte;

	public Plateau ()
	{
		this.plateau = new ArrayList<Piece>();
	}

	public List<Piece> getPlateau () {return this.plateau;}
	public char getDifficulte() {return this.difficulte;}

	public boolean partieGagne() {return this.plateau.size() == 1;}

	public void initNiveau(int nbNiveau)
	{
		this.plateau = new ArrayList<Piece>();
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./niveaux/niveau" + nbNiveau + ".txt" ) );

			String ligne;
			char piece;
			int pos = 2;
			this.difficulte = sc.nextLine().charAt(0);

			for(int cpt = 0; cpt < 4; cpt++)
			{
				sc.nextLine();
				ligne = sc.nextLine();

				for(int cptBis = 0; cptBis < 4; cptBis++)
				{
					piece = ligne.charAt(pos);
					switch(piece)
					{
						case 'P' : this.plateau.add(new Pion(cptBis, cpt));break;
						case 'K' : this.plateau.add(new Roi(cptBis, cpt));break;
						case 'Q' : this.plateau.add(new Dame(cptBis, cpt));break;
						case 'C' : this.plateau.add(new Cavalier(cptBis, cpt));break;
						case 'F' : this.plateau.add(new Fou(cptBis, cpt));break;
						case 'T' : this.plateau.add(new Tour(cptBis, cpt));break;
					}
					pos += 4;
				}
				pos = 2;
			}


			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
	}

	public void deplacer(int colDep, int ligDep, int colArr, int ligArr)
	{
		Piece piece = null;


		for (Piece p : this.plateau)
		{
			//System.out.println(p.getColonne() + " " + colDep);
			//System.out.println(p.getLigne() + " " + ligDep);
			if (p.getColonne() == colDep && p.getLigne() == ligDep)
				{piece = p;}
		}

		if(piece != null) {piece.deplacer(colArr, ligArr, this.plateau);}
		else
		{
			System.out.println("\n\terreur : Pièce inexistante\n");
			Clavier.lireString();
		}
	}


}

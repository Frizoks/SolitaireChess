package metier;

import controleur.Controleur;

import java.util.*;
import java.io.FileInputStream;

public class Plateau 
{
	private Controleur	ctrl;

	private List<Piece>	plateau;
	private List<Coup>	coupJoue;

	private char		difficulte;
	

	public Plateau (Controleur ctrl)
	{
		this.plateau  = new ArrayList<Piece>();
		this.coupJoue = new ArrayList<Coup> ();
		this.ctrl = ctrl;
	}

	public boolean niveauGagne() {return this.plateau.size() == 1;}

	//getters et setters de la classe Plateau
	public List<Piece> getPlateau () {return this.plateau;}
	public char getDifficulte() {return this.difficulte;}
	public char getPiece(int col, int lig)
	{
		for(Piece p:this.plateau)
		{
			if(p.getColonne() == col && p.getLigne() == lig) {return p.getType();}
		}
		return ' ';
	}

	//methode permettant la lecture dans un fichier texte d'un niveau de solitaire chess
	public void initNiveau(int nbNiveau)
	{
		this.plateau = new ArrayList<Piece>();
		this.coupJoue = new ArrayList<Coup>();
		try
		{
			Scanner sc = new Scanner ( new FileInputStream ( "./donnees/niveaux/niveau" + nbNiveau + ".txt" ) );

			String ligne;
			char piece;
			int pos = 2;

			this.difficulte = sc.nextLine().charAt(0);

			for(int lig = 0; lig < 4; lig++)
			{
				sc.nextLine();
				ligne = sc.nextLine();

				for(int col = 0; col < 4; col++)
				{
					piece = ligne.charAt(pos);
					switch(piece)
					{
						case 'P' : this.plateau.add(new Pion    (col, lig) ); break;
						case 'K' : this.plateau.add(new Roi     (col, lig) ); break;
						case 'Q' : this.plateau.add(new Dame    (col, lig) ); break;
						case 'C' : this.plateau.add(new Cavalier(col, lig) ); break;
						case 'F' : this.plateau.add(new Fou     (col, lig) ); break;
						case 'T' : this.plateau.add(new Tour    (col, lig) ); break;
					}
					pos += 4;
				}
				pos = 2;
			}
			sc.close();
		}
		catch (Exception e){}
	}

	//methode permettant le deplacement d'une piece
	public boolean deplacer(int colDep, int ligDep, int colArr, int ligArr)
	{
		Piece piece = null;

		//verification que la piece que nous voulons deplacer existe bien dans la liste
		for (Piece p : this.plateau)
		{
			if (p.getColonne() == colDep && p.getLigne() == ligDep)
				{piece = p;}
		}

		//si elle existe, on essaye alors de la deplacer, et si c'est possible on ajoute le coup dans une autre liste(pour gerer le ctrl-z)
		if(piece != null) 
		{
			if(piece.deplacer(colArr, ligArr, this.plateau)) 
			{
				this.coupJoue.add(new Coup(colDep, ligDep, colArr, ligArr));
				return true;
			}
		}
		return false;
	}


	//methode qui permet de gerer le ctrl-z
	public void annuler()
	{
		int taille = this.coupJoue.size() - 1;

		//On verifie qu'il y a bien une action à annuler
		if(taille >= 0) 
		{
			//on rejoue tout les coups depuis le plateau de départ, sauf le dernier(qu'il faut supprimer)
			this.coupJoue.remove(taille);

			//copie de la liste des coups
			List<Coup> copieCoup = new ArrayList<Coup>();
			for (Coup c: this.coupJoue)
			{
				copieCoup.add(c);
			}

			this.initNiveau(this.ctrl.getNiveauActuel()); //initialisation du niveau
			
			Piece piece = null;
			
			//parcours de tout les coups à joué, et vérification si ils sont tous possibles
			for(Coup c: copieCoup)
			{
				for (Piece p : this.plateau)
				{
					if (p.getColonne() == c.getColDep() && p.getLigne() == c.getLigDep()) {piece = p;}
				}

				if(piece != null) {this.deplacer(c.getColDep(), c.getLigDep(), c.getColArr(), c.getLigArr());}
			}
		}
	}

}

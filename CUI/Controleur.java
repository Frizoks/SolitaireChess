//import ihmgui.*;
import iut.algo.*;
import java.util.*;

public class Controleur //extends Controle
{
	public final int NIVEAU_MAX = 62;

	private   Plateau     metier;
	//private   FrameGrille frame;
	private int niveauActuel = 1;

	public Controleur()
	{
		metier = new Plateau();
		//frame  = new FrameGrille ( this );
	}

	public String afficherPlateau(List<Piece> liste, char difficulte)
	{
		boolean val = false;
		String sRet = "\n", sSep = "  +---+---+---+---+\n";

		/*for(Piece p:liste)
		{
			System.out.println(p.getType());
		}*/
		
		switch(difficulte)
		{
			case 'D' : sRet += "Débutant";break;
			case 'I' : sRet += "Intermédiaire";break;
			case 'A' : sRet += "Avancé";break;
			case 'E' : sRet += "Expert";break;
			default : sRet += "???";break;
		}
		sRet += "(" + this.getNiveauActuel() + ")";

		for(int cptLig = 0, cpt = 3; cptLig < 4; cptLig++, cpt--)
		{
			sRet += "\n" + sSep + cpt + " |"; 
			for(int cptCol = 0; cptCol < 4; cptCol ++)
			{
				sRet += " ";
				for(Piece p:liste) 
				{
					if(p.getColonne() == cptCol && p.getLigne() == cptLig) 
					{
						sRet += p.getType();
						val = true;
					}
				}
				if(!val) {sRet += " ";}
				sRet += " |";
				val = false;
			}
		}

		sRet += "\n" + sSep + "    A   B   C   D";

		return sRet;
	}

	public void deplacer(String coordonneesDep, String coordonneesArr)
	{
		int colDep, ligDep, colArr, ligArr;

		try
		{
			colDep = (int)(coordonneesDep.charAt(0) - 'A');
			ligDep = 3 - Integer.parseInt("" + (coordonneesDep.charAt(1)));
			colArr = (int)(coordonneesArr.charAt(0) - 'A');
			ligArr = 3 - Integer.parseInt("" + (coordonneesArr.charAt(1)));

			this.metier.deplacer(colDep, ligDep, colArr, ligArr);
		}
		catch(Exception e) 
		{
			System.out.println("\n\tSaisie invalide\n");
			Clavier.lireString();
		}
	}

	public boolean partieGagne () {return this.metier.partieGagne();}
	public List<Piece> getPlateau (){return this.metier.getPlateau();}
	public void initNiveau() {this.metier.initNiveau(this.niveauActuel);}
	public void incrNiveauActuel() {this.niveauActuel++;}
	public void incrNiveauActuel(int nbNiveau) {this.niveauActuel = nbNiveau;}
	public int getNiveauActuel() {return this.niveauActuel;}
	public char getDifficulte() {return this.metier.getDifficulte();}
	public int getNiveauMax() {return this.NIVEAU_MAX;}

	public static void main(String[] arg)
	{
		Controleur ctrl = new Controleur();
		boolean val = true;
		char action;
		String coordonnees;

		ctrl.initNiveau();

		System.out.println("\n\t\t\tDEBUT DE LA PARTIE\n");

		while(val && ctrl.getNiveauActuel() <= ctrl.getNiveauMax())
		{
			System.out.println(ctrl.afficherPlateau(ctrl.getPlateau(), ctrl.getDifficulte()));
			System.out.print("\nQuelle est votre action :\n\t[D]eplacer une pièce\n\t[R]ecommencer\n\t[N]iveau suivant\n\t[C]hoisir Niveau\n\t[Q]uitter\n\t ");
			action = Clavier.lire_char();
			switch(action)
			{
				case 'D' :  System.out.print("\t\tQuelle sont les coordonnées de votre piece à deplacer : ");
							coordonnees = Clavier.lireString();
							System.out.print("\t\tQuelle sont les coordonnées de votre déplacement      : ");
							ctrl.deplacer(coordonnees, Clavier.lireString());break;
				case 'R' :  ctrl.initNiveau();
							System.out.println("\n\n\n\t\t\tNIVEAU RECOMMENCER\n");break;
				case 'N' :  ctrl.incrNiveauActuel();
							ctrl.initNiveau();break;
				case 'C' :  System.out.print("Quelle niveau voulez-vous faire ? ");
							ctrl.incrNiveauActuel(Clavier.lire_int());
							System.out.print("\n\n");
							ctrl.initNiveau();break;
				case 'Q' :  val = false;break;
			}

			if(ctrl.partieGagne())
			{
				ctrl.incrNiveauActuel();
				if(ctrl.getNiveauActuel() < ctrl.getNiveauMax())
				{
					ctrl.initNiveau();
					System.out.println("\n\n\n\t\t\tNIVEAU SUIVANT\n");
				}
			}
		}

		if(ctrl.getNiveauActuel() >= ctrl.getNiveauMax() && ctrl.partieGagne()) {System.out.println("\n\t\t\tGGGGGGGGGGGGGGGGGGGGGGGGG\n");}
	}

}
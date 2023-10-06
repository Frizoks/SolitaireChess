package ihm;

import controleur.Controleur;

import ihmgui.*;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;

public class Frame extends Controle
{
	private Controleur ctrl;
	private FrameGrille frame;
	private JFrame frameChoisirNiveau;
	private JTextField txtChoisir;

	public Frame(Controleur controleur)
	{
		ctrl = controleur;
		frame  = new FrameGrille (this);
		frameChoisirNiveau = new JFrame();

		//Chargement du 1er niveau
		this.bouton(0);

		//Definition des composants GUI
		JLabel lblChoisir = new JLabel(" Veuillez choisir un niveau");
		this.txtChoisir = new JTextField(15);

		this.frameChoisirNiveau.setLayout(new FlowLayout());
		this.frameChoisirNiveau.add(lblChoisir);
		this.frameChoisirNiveau.add(this.txtChoisir);

		this.frameChoisirNiveau.setSize(200, 100);
		this.frameChoisirNiveau.setLocation(460, 250);
		this.frameChoisirNiveau.setVisible(false);

		this.frame.setSize(650, 400);
		this.frame.setLocation (400, 200);
		this.frame.setTitle("Solitaire Chess");
		this.frame.setVisible(true);

		this.txtChoisir.addActionListener(new GereTextField());
	}

	public void afficherFrame() {this.frameChoisirNiveau.setVisible(true);}
	public void cacherFrame() {this.frameChoisirNiveau.setVisible(false);}

	//classe privé permettant de gérer l'action du TextField
	private class GereTextField implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == Frame.this.txtChoisir) 
			{
				try 
				{
					ctrl.choisirNiveauActuel(Integer.parseInt(Frame.this.txtChoisir.getText()));
					Frame.this.bouton(0);
					Frame.this.cacherFrame();
				}
				catch(Exception ex) {}
			}
		}
	}

	/*toutes les méthodes suivantes (hormis le main) sont issus du fichier ihmgui.jar, 
	elles permettent de faire fonctionner les différentes actions comme le glisser déposer, ou encore les boutons et les zones d'affichages*/
	public String setBouton(int numBtn)
	{
		String lib;

		switch ( numBtn )
		{
			case 0 : lib = "Recommencer";break;
			case 1 : lib = "Niveau Suivant";break;
			case 2 : lib = "Niveau Précédent";break;
			case 3 : lib = "Choisir Niveau";break;
			default: lib = null;                      // cette derniere ligne est importante, elle met fin a la contruction des boutons
		}

		return lib;
	}

	public String setLabel (int numLbl)
	{
		String lib;

		switch ( numLbl )
		{
			case 0 : lib = "nb Coup :";        break;
			case 1 : lib = "Niveau :";        break;
			case 2 : lib = " ";        break;
			default: lib = null;					// cette derniere ligne est importante, elle met fin a la contruction des labels
		}

		return lib;
	}

	public void jouer (String touche)
	{
		if ( touche.equals ( "CR-Z" ) ) ctrl.annuler();
		frame.majIHM();
	}

	public void glisser (int ligne1, int colonne1, int ligne2, int colonne2)
	{
		if(ctrl.deplacer(colonne1, ligne1, colonne2, ligne2)) {this.bouton(7);}
		else {this.bouton(4);}

		if(ctrl.niveauGagne()) {this.bouton(6);this.bouton(1);}
		if(ctrl.partieGagne()) {this.bouton(5);}
		frame.majIHM();
	}

	public void bouton(int action)
	{
		if ( action == 0 ) { ctrl.action ('R'); frame.majIHM(); } //Recommencer niveau
		if ( action == 1 ) { ctrl.action ('S'); frame.majIHM(); } //Niveau suivant
		if ( action == 2 ) { ctrl.action ('P'); frame.majIHM(); } //Niveau précédent
		if ( action == 3 ) { this.afficherFrame(); frame.majIHM(); }
		if ( action == 4 ) { ctrl.action ('E'); frame.majIHM(); } //Message d'erreur
		if ( action == 5 ) { ctrl.action ('F'); frame.majIHM(); } //Message de fin
		if ( action == 6 ) { ctrl.action ('N'); frame.majIHM(); } //Nombre de coups
		if ( action == 7 ) 
		{
			ctrl.action(' '); //Remise à zero de la zone d'affichage
			ctrl.action('G'); //incrémenter le nombre de coups
			frame.majIHM();}
	}

	public String setTextLabel(int numLbl)
	{
		switch ( numLbl )
		{
			case 0 : return "" + this.ctrl.getNbCoup();
			case 1 : return "" + this.ctrl.getNiveauActuel() + " / " + this.ctrl.getNiveauMax();
			case 2 : return this.ctrl.getMessage();
			default: return null;					// cette derniere ligne est importante, elle met fin a la contruction des labels
		}
	}

	public String setImage ( int colonne, int ligne, int couche)
	{
		char   symbole;
		String rep = "./donnees/images/";
		String sImage = null;

		if ( couche==0)
		{
			symbole = ctrl.getSymbole (colonne, ligne);

			if      ( symbole == 'C') sImage = rep + "cavalier.gif";
			else if ( symbole == 'F') sImage = rep + "fou.gif";
			else if ( symbole == 'T') sImage = rep + "tour.gif";
			else if ( symbole == 'Q') sImage = rep + "reine.gif";
			else if ( symbole == 'K') sImage = rep + "roi.gif";
			else if ( symbole == 'P') sImage = rep + "pion.gif";
			else                      sImage = rep + "vide52.gif";
		}

		return sImage;
	}

	public int     setNbLigne        () { return 4;}
	public int     setNbColonne      () { return 4;}
	public boolean setNumLigneColonne() { return false;}
	public int     setLargeurImg     () { return 50;}
	public String  setFondGrille     () 
	{ 
		switch(this.ctrl.getDifficulte())
		{
			case 'D' : return "./donnees/images/plateauvert.gif";
			case 'I' : return "./donnees/images/plateaubleu.gif";
			case 'A' : return "./donnees/images/plateauviolet.gif";
			case 'E' : return "./donnees/images/plateaurouge.gif";
		}
		return "./donnees/images/plateauvert.gif";
	}
}
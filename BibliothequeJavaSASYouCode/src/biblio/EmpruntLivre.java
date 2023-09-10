package biblio;

import java.util.Date;
import java.util.Scanner;

public class EmpruntLivre {
	 private int id;
	 private String livre_ISBN;
	 private int id_emprunteur;
	 private Date date_emprunt;
	 private Date date_retour;
	 
	 private ISBN isbnClasse = new ISBN();
	 private Emprunteur emprunteur = new Emprunteur();
	 
	 private static Scanner scanner = new Scanner(System.in);
		 
	 public void empruntterLivre()
	 {
		 // demander le 	isbn à empruntééé
		 System.out.println("donner le ISBN du livre à emprunté");
		 this.livre_ISBN = scanner.nextLine();
		 
		 //System.out.println(this.livre_ISBN);
		 
		 //Isbn existe?????
		 if(isbnClasse.ISBNexiste(this.livre_ISBN))
		 {
			 //status est 1????
			 if(isbnClasse.ISBNDispo(this.livre_ISBN))
			 {
				 emprunteur.ajoutEmprunteur();
			 }else {
				 System.out.println("ISBN n'est pas disponible");
			 }
			 
		 }else
		 {
			 System.out.println("ISBN n'existe pas");
		 }
	 }
}

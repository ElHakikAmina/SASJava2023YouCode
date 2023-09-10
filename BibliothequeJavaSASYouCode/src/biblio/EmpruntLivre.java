package biblio;

import java.util.Date;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpruntLivre {
	 private int id;
	 private String livre_ISBN;
	 private int id_emprunteur;
	 private Date date_emprunt;
	 private Date date_retour;
	 
	 public static Connection connexion = ConnexionDB.getInstance().getConnexion();

	 
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
				 
				 
				 
				 
				 // Générer automatiquement la date d'emprunt (date actuelle)
	                LocalDate dateEmprunt = LocalDate.now();
	                System.out.println("Date d'emprunt : " + dateEmprunt);
	                
	                
	             // Demander la date de retour à l'utilisateur
	                System.out.println("Entrez la date de retour (AAAA-MM-JJ) :");
	                String dateRetourStr = scanner.nextLine();
	                LocalDate dateRetour = LocalDate.parse(dateRetourStr);
	                
	                
	                
	             // Insérer les informations dans la table emprunt_livre
	                try {
	                    String requeteInsertion = "INSERT INTO emprunt_livre (livre_ISBN, id_emprunteur, Date_emprunt, Date_retour) VALUES (?, ?, ?, ?)";
	                    PreparedStatement preparedStatement = connexion.prepareStatement(requeteInsertion);
	                    preparedStatement.setString(1, livre_ISBN);
	                    preparedStatement.setInt(2, emprunteur.getId()); // Supposons que vous avez un getter pour l'ID de l'emprunteur
	                    preparedStatement.setDate(3, java.sql.Date.valueOf(dateEmprunt)); // Convertir LocalDate en java.sql.Date
	                    preparedStatement.setDate(4, java.sql.Date.valueOf(dateRetour)); // Convertir LocalDate en java.sql.Date

	                    int lignesAffectees = preparedStatement.executeUpdate();

	                    if (lignesAffectees > 0) {
	                        System.out.println("L'emprunt a été enregistré avec succès.");
	                    } else {
	                        System.out.println("L'enregistrement de l'emprunt a échoué.");
	                    }

	                    preparedStatement.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	                /////////////////////////
				 
			 }else {
				 System.out.println("ISBN n'est pas disponible");
			 }
			 
		 }else
		 {
			 System.out.println("ISBN n'existe pas");
		 }
	 }
}

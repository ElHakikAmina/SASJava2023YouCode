package biblio;

import java.util.Date;
import java.sql.ResultSet;
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
		 
	
	///////////////////////////
	 public void statistiques() {
	        Connection connexion = ConnexionDB.getInstance().getConnexion();
	        LocalDate aujourdhui = LocalDate.now();

	        try {
	            // Nombre total d'emprunteurs
	            String requeteEmprunteurs = "SELECT COUNT(*) AS totalEmprunteurs FROM emprunteur";
	            PreparedStatement preparedStatementEmprunteurs = connexion.prepareStatement(requeteEmprunteurs);
	            ResultSet resultSetEmprunteurs = preparedStatementEmprunteurs.executeQuery();
	            resultSetEmprunteurs.next();
	            int totalEmprunteurs = resultSetEmprunteurs.getInt("totalEmprunteurs");

	            // Nombre d'emprunteurs qui n'ont pas encore retourné le livre
	            String requeteEmprunteursNonRetournes = "SELECT COUNT(*) AS emprunteursNonRetournes "
                        + "FROM emprunteur "
                        + "WHERE id IN (SELECT DISTINCT id_emprunteur "
                        + "FROM emprunt_livre el "
                        + "JOIN isbn i ON el.livre_ISBN = i.ISBN "
                        + "WHERE el.Date_retour < ? AND i.status = '0')";
	            PreparedStatement preparedStatementEmprunteursNonRetournes = connexion.prepareStatement(requeteEmprunteursNonRetournes);
	            preparedStatementEmprunteursNonRetournes.setDate(1, java.sql.Date.valueOf(aujourdhui));
	            ResultSet resultSetEmprunteursNonRetournes = preparedStatementEmprunteursNonRetournes.executeQuery();
	            resultSetEmprunteursNonRetournes.next();
	            int emprunteursNonRetournes = resultSetEmprunteursNonRetournes.getInt("emprunteursNonRetournes");

	         // Nombre de livres disponibles
	            String requeteLivresDisponibles = "SELECT COUNT(*) AS livresDisponibles FROM isbn WHERE status = '1'";
	            PreparedStatement preparedStatementLivresDisponibles = connexion.prepareStatement(requeteLivresDisponibles);
	            ResultSet resultSetLivresDisponibles = preparedStatementLivresDisponibles.executeQuery();

	            int livresDisponibles = 0; // Initialisation à zéro

	            if (resultSetLivresDisponibles.next()) {
	                livresDisponibles = resultSetLivresDisponibles.getInt("livresDisponibles");
	            }

	         // Nombre de livres en attente de retour
	            String requeteLivresEnAttente = "SELECT COUNT(*) AS livresEnAttente FROM isbn WHERE status = '0'";
	            PreparedStatement preparedStatementLivresEnAttente = connexion.prepareStatement(requeteLivresEnAttente);
	            ResultSet resultSetLivresEnAttente = preparedStatementLivresEnAttente.executeQuery();

	            int livresEnAttente = 0; // Initialisation à zéro

	            if (resultSetLivresEnAttente.next()) {
	                livresEnAttente = resultSetLivresEnAttente.getInt("livresEnAttente");
	            }

	            // Nombre de livres qui ont dépassé la date de retour
	            String requeteLivresDepasses = "SELECT COUNT(*) AS livresDepasses "
	                    + "FROM emprunt_livre "
	                    + "WHERE Date_retour < ?";
	            PreparedStatement preparedStatementLivresDepasses = connexion.prepareStatement(requeteLivresDepasses);
	            preparedStatementLivresDepasses.setDate(1, java.sql.Date.valueOf(aujourdhui));
	            ResultSet resultSetLivresDepasses = preparedStatementLivresDepasses.executeQuery();
	            resultSetLivresDepasses.next();
	            int livresDepasses = resultSetLivresDepasses.getInt("livresDepasses");

	            // Affichage des statistiques
	            System.out.println("                                     Statistiques de la bibliothèque :");
	            System.out.println("+----------------------------------------------------------+-----------------------+");
	            System.out.println("| Nombre total d'emprunteurs                                     " + totalEmprunteurs);
	            System.out.println("+----------------------------------------------------------+-----------------------+");
	            System.out.println("| Nombre d'emprunteurs qui ont dépasser la date de retour        " + emprunteursNonRetournes);
	            System.out.println("+----------------------------------------------------------+-----------------------+");
	            System.out.println("| Nombre de livres disponibles                                   " + livresDisponibles);
	            System.out.println("+----------------------------------------------------------+-----------------------+");
	            System.out.println("| Nombre de livres en attente de retour                          " + livresEnAttente);
	            System.out.println("+----------------------------------------------------------+-----------------------+");
	            System.out.println("| Nombre de livres qui ont dépassé la date de retour             " + livresDepasses);
	            System.out.println("+----------------------------------------------------------+-----------------------+");

	            resultSetEmprunteurs.close();
	            preparedStatementEmprunteurs.close();
	            resultSetEmprunteursNonRetournes.close();
	            preparedStatementEmprunteursNonRetournes.close();
	            resultSetLivresDisponibles.close();
	            preparedStatementLivresDisponibles.close();
	            resultSetLivresEnAttente.close();
	            preparedStatementLivresEnAttente.close();
	            resultSetLivresDepasses.close();
	            preparedStatementLivresDepasses.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 //////
	 
	 public void livrePerdus() {
	        Connection connexion = ConnexionDB.getInstance().getConnexion();
	        LocalDate aujourdhui = LocalDate.now();

	        try {
	            String requete = "SELECT livre.titre, livre.auteur, emprunt_livre.Date_retour "
	                    + "FROM emprunt_livre "
	                    + "JOIN isbn ON emprunt_livre.livre_ISBN = isbn.ISBN "
	                    + "JOIN livre ON isbn.id_livre = livre.id "
	                    + "WHERE isbn.status = 0 AND emprunt_livre.Date_retour < ?";
	            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
	            preparedStatement.setDate(1, java.sql.Date.valueOf(aujourdhui));

	            ResultSet resultSet = preparedStatement.executeQuery();

	            int nombreTotalLivresPerdus = 0;

	            System.out.println("Livres perdus :");
	            while (resultSet.next()) {
	                String titre = resultSet.getString("titre");
	                String auteur = resultSet.getString("auteur");
	                Date dateRetour = resultSet.getDate("Date_retour");

	                System.out.println("Titre                   : " + titre);
	                System.out.println("Auteur                  : " + auteur);
	                System.out.println("Date de retour dépassée : " + dateRetour);
	                System.out.println();
	                nombreTotalLivresPerdus++;
	            }

	            System.out.println("Nombre total de livres perdus : " + nombreTotalLivresPerdus);

	            resultSet.close();
	            preparedStatement.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 /////
	 public void retournerLivre()
	 {
		 // demander le 	isbn à empruntééé
		 System.out.println("donner le ISBN du livre à retourner");
		 this.livre_ISBN = scanner.nextLine();
		 
		 //System.out.println(this.livre_ISBN);
		 
		 //Isbn existe?????
		 if(isbnClasse.ISBNexiste(this.livre_ISBN))
		 {
			 //status est 1????
			 if(!isbnClasse.ISBNDispo(this.livre_ISBN))
			 {   
	             // Insérer les informations dans la table emprunt_livre
	                try {          
	                    	isbnClasse.rendreStatusDispo(livre_ISBN);
	                        System.out.println(ConsoleColors.GREEN+"L'emprunt a été enregistré avec succès."+ ConsoleColors.RESET);
	                   
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	                /////////////////////////
				 
			 }else {
				 System.out.println(ConsoleColors.RED+"ISBN est déjà retourné"+ ConsoleColors.RESET);
			 }
		 }else
		 {
			 System.out.println(ConsoleColors.RED+"ISBN n'existe pas"+ ConsoleColors.RESET);
		 }
	 }	 
	 ////////////////////////
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
				 //emprunteur.ajoutEmprunteur();
				 String numTel;
				 System.out.println("donner le numero de telephone du emprunter:");
				 numTel=scanner.nextLine();
				 
				 int idEmprunteur = getIdEmprunteurParNumTel(numTel);
				 
				 if(idEmprunteur==-1)
				 {
				        System.out.println(ConsoleColors.RED+"Aucun emprunteur avec ce numéro de téléphone n'a été trouvé."+ ConsoleColors.RESET);

				 }else
				 {
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
		                    preparedStatement.setInt(2, idEmprunteur); // Supposons que vous avez un getter pour l'ID de l'emprunteur
		                    preparedStatement.setDate(3, java.sql.Date.valueOf(dateEmprunt)); // Convertir LocalDate en java.sql.Date
		                    preparedStatement.setDate(4, java.sql.Date.valueOf(dateRetour)); // Convertir LocalDate en java.sql.Date

		                    int lignesAffectees = preparedStatement.executeUpdate();

		                    if (lignesAffectees > 0) {
		                    	isbnClasse.rendreStatusNonDispo(livre_ISBN);
		                        System.out.println(ConsoleColors.GREEN+"L'emprunt a été enregistré avec succès."+ ConsoleColors.RESET);
		                    } else {
		                        System.out.println(ConsoleColors.RED+"L'enregistrement de l'emprunt a échoué."+ ConsoleColors.RESET);
		                    }

		                    preparedStatement.close();
		                } catch (SQLException e) {
		                    e.printStackTrace();
		                }
		                //
					 
				 
				 }
			 }else {
				 System.out.println(ConsoleColors.RED+"ISBN n'est pas disponible"+ ConsoleColors.RESET);
			 }
			 
		 }else
		 {
			 System.out.println(ConsoleColors.RED+"ISBN n'existe pas"+ ConsoleColors.RESET);
		 }
	 }
	 
	 //
	 
	 public int getIdEmprunteurParNumTel(String numTel) {
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;

		    try {
		        String query = "SELECT id FROM emprunteur WHERE numTel = ?";
		        preparedStatement = connexion.prepareStatement(query);
		        preparedStatement.setString(1, numTel);
		        resultSet = preparedStatement.executeQuery();

		        if (resultSet.next()) {
		            return resultSet.getInt("id");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (resultSet != null) {
		            try {
		                resultSet.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		        if (preparedStatement != null) {
		            try {
		                preparedStatement.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }

		    return -1; // Retourne -1 si le numéro de téléphone n'a pas été trouvé
		}
}

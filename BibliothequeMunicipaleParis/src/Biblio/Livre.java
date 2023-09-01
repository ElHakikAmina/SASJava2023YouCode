package Biblio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Livre {
	private String ISBN; 
	private String titre; 
	private String auteur; 
	private int quantite;
	//
	protected static void afficherTousLesLivres() {
        try {
            Connection connexion = ConnexionBDD.seConnecterDB();
            String query = "SELECT * FROM livre"; // Assurez-vous que la table s'appelle "Livres" dans votre base de données

            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                String isbn = resultSet.getString("ISBN");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                int quantite = resultSet.getInt("quantite");
                // Vous pouvez ajouter d'autres colonnes ici selon votre schéma de base de données

                
                System.out.println("ISBN: " + isbn);
                System.out.println("Titre: " + titre);
                System.out.println("Auteur: " + auteur);
                System.out.println("Quantite: " + quantite);
                // Affichez d'autres informations si nécessaire
                System.out.println();
            }

            ConnexionBDD.fermerConnexion(connexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	//
}

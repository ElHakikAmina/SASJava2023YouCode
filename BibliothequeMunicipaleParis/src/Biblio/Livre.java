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
            String query = "SELECT * FROM livre"; 

            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                String isbn = resultSet.getString("ISBN");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                int quantite = resultSet.getInt("quantite");

                
                System.out.println("ISBN: " + isbn);
                System.out.println("Titre: " + titre);
                System.out.println("Auteur: " + auteur);
                System.out.println("Quantite: " + quantite);
                System.out.println();
            }

            ConnexionBDD.fermerConnexion(connexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	//
}

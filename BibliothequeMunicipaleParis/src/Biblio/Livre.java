package Biblio;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;


public class Livre {
	private String ISBN; 
	private String titre; 
	private String auteur; 
	private int quantite;
	
	 private static Scanner scanner = new Scanner(System.in);
	//
	protected  void saisi()
	{
		System.out.println("Saisissez l'ISBN du livre : ");
        this.ISBN = scanner.nextLine();

        System.out.println("Saisissez le titre du livre : ");
        this.titre = scanner.nextLine();

        System.out.println("Saisissez l'auteur du livre : ");
        this.auteur = scanner.nextLine();

        System.out.println("Saisissez la quantité du livre : ");
        this.quantite = scanner.nextInt();
	}
	
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
	
	public static void ajouterLivre(String ISBN, String titre, String auteur, int quantite) {
        try {
            Connection connexion = ConnexionBDD.seConnecterDB();
            String query = "INSERT INTO livre (ISBN, titre, auteur, quantite) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, ISBN);
            preparedStatement.setString(2, titre);
            preparedStatement.setString(3, auteur);
            preparedStatement.setInt(4, quantite);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le livre a été ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout du livre.");
            }

            ConnexionBDD.fermerConnexion(connexion);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}

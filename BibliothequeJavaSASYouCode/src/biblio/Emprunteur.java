package biblio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emprunteur {
	private int id;
	private String nom_complet; 
	private int numTel;  
	private String adresse;
	
	public void ajoutEmprunteur(String nomComplet, int numTel, String adresse) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connexion = ConnexionBDD.seConnecterDB();

	        
	        String query = "INSERT INTO emprunteur (nom_complet, numTel, adresse) VALUES (?, ?, ?)";

	        
	        preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setString(1, nomComplet);
	        preparedStatement.setInt(2, numTel);
	        preparedStatement.setString(3, adresse);

	        
	        int rowsInserted = preparedStatement.executeUpdate();

	        if (rowsInserted > 0) {
	            System.out.println("Emprunteur ajouté avec succès.");
	        } else {
	            System.out.println("L'ajout de l'emprunteur a échoué.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } finally {
	        
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        /*if (connexion != null) {
	            ConnexionBDD.fermerConnexion(connexion);
	        }*/
	    }
	}

}

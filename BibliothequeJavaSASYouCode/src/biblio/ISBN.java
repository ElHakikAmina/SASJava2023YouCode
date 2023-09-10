package biblio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ISBN {
	
	private String ISBN;
	private String status;
	
	public static Connection connexion = ConnexionDB.getInstance().getConnexion();
	
	
	public boolean ISBNexiste(String isbn)
	{
		try {
	        //Connection connexion = ConnexionDB.seConnecterDB();
	        String query = "SELECT * from isbn where ISBN = ? ";
	        PreparedStatement preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setString(1, isbn);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	          
	            return true;
	        } else {
	        	if(isbn.equals("Q")) System.out.println("Modification quittée");
	        	else System.out.println("Le livre avec l'ISBN " + isbn + " n'existe pas.");

	            
	            //ConnexionDB.fermerConnexion(connexion);
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        return false;
	    }

	}
	public  void ajouterISBN( String ISBN, String status, int id_livre) {
        try {
                       
            String query = "INSERT INTO isbn ( ISBN, status, id_livre) VALUES ( ?, ?, ?)";

            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            //preparedStatement.setString(1, ISBN);
            preparedStatement.setString(1, ISBN);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, id_livre);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le livre a été ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout du livre.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

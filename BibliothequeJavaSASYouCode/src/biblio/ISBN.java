package biblio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ISBN {
	
	private String ISBN;
	private String status;
	
	public static Connection connexion = ConnexionDB.getInstance().getConnexion();
	
	
	 public boolean rendreStatusDispo(String ISBN) throws SQLException {
	        PreparedStatement preparedStatement = null;

	        try {
	            String query = "UPDATE isbn SET status = 1 WHERE ISBN = ?";
	            preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setString(1, ISBN);

	            int rowsUpdated = preparedStatement.executeUpdate();

	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            // Fermez les ressources (PreparedStatement, etc.) et gérez les exceptions appropriées ici
	        }
	    }

	    // Fonction pour rendre le statut de l'ISBN non disponible (status != 1)
	    public boolean rendreStatusNonDispo(String ISBN) {
	        PreparedStatement preparedStatement = null;

	        try {
	            String query = "UPDATE isbn SET status = 0 WHERE ISBN = ?";
	            preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setString(1, ISBN);

	            int rowsUpdated = preparedStatement.executeUpdate();

	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            // Fermez les ressources (PreparedStatement, etc.) et gérez les exceptions appropriées ici
	        }
	    }
	
	
	public boolean ISBNDispo(String isbn)
	{
		try {
	       
	        String query = "SELECT * from isbn where ISBN = ? and status='1' ";
	        PreparedStatement preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setString(1, isbn);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	          
	            return true;
	        } else {
	        	

	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        return false;
	    }
	}
	public  boolean ISBNexiste(String isbn)
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
                System.out.println(ConsoleColors.GREEN+"Le livre a été ajouté avec succès."+ ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED+"Erreur lors de l'ajout du livre."+ ConsoleColors.RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

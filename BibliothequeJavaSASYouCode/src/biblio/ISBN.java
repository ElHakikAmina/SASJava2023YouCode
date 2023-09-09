package biblio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ISBN {
	
	private String ISBN;
	private String status;
	
	public static Connection connexion = ConnexionDB.getInstance().getConnexion();
	
	
	
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

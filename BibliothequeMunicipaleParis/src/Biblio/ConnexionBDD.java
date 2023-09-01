package Biblio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3307/Bibliotheque"; 
        String utilisateur = "root"; 
        String motDePasse = "1234"; 

        try {
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
System.out.println("gg");

            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
       
            
}

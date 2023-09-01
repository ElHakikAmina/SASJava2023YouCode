package Biblio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {

	
		private static final String url = "jdbc:mysql://localhost:3307/Bibliotheque"; 
        private static final String utilisateur = "root"; 
        private static final String motDePasse = "1234"; 

        public static Connection seConnecterDB() throws SQLException {
            return DriverManager.getConnection(url, utilisateur, motDePasse);
        }
       
        public static void fermerConnexion(Connection connexion) throws SQLException {
            if (connexion != null) {
                connexion.close();
            }
        }
}

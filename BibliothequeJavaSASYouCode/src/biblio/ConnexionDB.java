package biblio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {

    // Informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3307/bibliotheque";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "1234";

    // Instance unique de la classe ConnexionDB
    private static ConnexionDB instance;

    // Connexion à la base de données
    private Connection connexion;

    // Constructeur privé pour empêcher l'instanciation directe
    private ConnexionDB() {
        // Initialiser la connexion à la base de données
        try {
            connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode statique pour obtenir l'instance unique de ConnexionDB
    public static ConnexionDB getInstance() {
        if (instance == null) {
            instance = new ConnexionDB();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion à la base de données
    public Connection getConnexion() {
        return connexion;
    }
}


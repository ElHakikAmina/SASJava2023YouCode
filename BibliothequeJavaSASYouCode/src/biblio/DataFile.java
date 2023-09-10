package biblio;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataFile {
    private Connection connexion;

    public DataFile(Connection connexion) {
        this.connexion = connexion;
    }

    public void ecrireStatistiquesDansFichier(String nomFichier) {
        String statistiques = obtenirStatistiquesDepuisBD();
        if (statistiques != null) {
            try {
                FileWriter writer = new FileWriter(nomFichier);
                writer.write(statistiques);
                writer.close();
                System.out.println("Les statistiques ont été écrites dans le fichier " + nomFichier);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'écriture des statistiques dans le fichier.");
            }
        } else {
            System.err.println("Erreur lors de la récupération des statistiques depuis la base de données.");
        }
    }

istiques.append("| Livres disponibles    | ").append(resultSetLivresDisponibles.getInt("livresDisponibles")).append(" |\n");
            }

            // Add other statistics in a similar manner

            statistiques.append("+-----------------------+-----------------------+\n");

            // Close the ResultSet objects here
            resultSetEmprunteurs.close();
            resultSetLivresDisponibles.close();
            // Close other ResultSet objects if you have more

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la récupération des statistiques depuis la base de données.");
            return null;
        }
        return statistiques.toString();
    }

}

package biblio;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DataFile {
    // ... Autres méthodes et champs de classe ...

    public void statistiques() {
        Connection connexion = ConnexionDB.getInstance().getConnexion();
        LocalDate aujourdhui = LocalDate.now();

        try {
            // Nombre total d'emprunteurs
            String requeteEmprunteurs = "SELECT COUNT(*) AS totalEmprunteurs FROM emprunteur";
            PreparedStatement preparedStatementEmprunteurs = connexion.prepareStatement(requeteEmprunteurs);
            ResultSet resultSetEmprunteurs = preparedStatementEmprunteurs.executeQuery();
            resultSetEmprunteurs.next();
            int totalEmprunteurs = resultSetEmprunteurs.getInt("totalEmprunteurs");

            // Nombre d'emprunteurs qui n'ont pas encore retourné le livre
            String requeteEmprunteursNonRetournes = "SELECT COUNT(*) AS emprunteursNonRetournes "
                    + "FROM emprunteur "
                    + "WHERE id IN (SELECT DISTINCT id_emprunteur "
                    + "FROM emprunt_livre el "
                    + "JOIN isbn i ON el.livre_ISBN = i.ISBN "
                    + "WHERE el.Date_retour < ? AND i.status = '0')";
            PreparedStatement preparedStatementEmprunteursNonRetournes = connexion.prepareStatement(requeteEmprunteursNonRetournes);
            preparedStatementEmprunteursNonRetournes.setDate(1, java.sql.Date.valueOf(aujourdhui));
            ResultSet resultSetEmprunteursNonRetournes = preparedStatementEmprunteursNonRetournes.executeQuery();
            resultSetEmprunteursNonRetournes.next();
            int emprunteursNonRetournes = resultSetEmprunteursNonRetournes.getInt("emprunteursNonRetournes");

            // Nombre de livres disponibles
            String requeteLivresDisponibles = "SELECT COUNT(*) AS livresDisponibles FROM isbn WHERE status = '1'";
            PreparedStatement preparedStatementLivresDisponibles = connexion.prepareStatement(requeteLivresDisponibles);
            ResultSet resultSetLivresDisponibles = preparedStatementLivresDisponibles.executeQuery();

            int livresDisponibles = 0; // Initialisation à zéro

            if (resultSetLivresDisponibles.next()) {
                livresDisponibles = resultSetLivresDisponibles.getInt("livresDisponibles");
            }

            // Nombre de livres en attente de retour
            String requeteLivresEnAttente = "SELECT COUNT(*) AS livresEnAttente FROM isbn WHERE status = '0'";
            PreparedStatement preparedStatementLivresEnAttente = connexion.prepareStatement(requeteLivresEnAttente);
            ResultSet resultSetLivresEnAttente = preparedStatementLivresEnAttente.executeQuery();

            int livresEnAttente = 0; // Initialisation à zéro

            if (resultSetLivresEnAttente.next()) {
                livresEnAttente = resultSetLivresEnAttente.getInt("livresEnAttente");
            }

            // Nombre de livres qui ont dépassé la date de retour
            String requeteLivresDepasses = "SELECT COUNT(*) AS livresDepasses "
                    + "FROM emprunt_livre "
                    + "WHERE Date_retour < ?";
            PreparedStatement preparedStatementLivresDepasses = connexion.prepareStatement(requeteLivresDepasses);
            preparedStatementLivresDepasses.setDate(1, java.sql.Date.valueOf(aujourdhui));
            ResultSet resultSetLivresDepasses = preparedStatementLivresDepasses.executeQuery();
            resultSetLivresDepasses.next();
            int livresDepasses = resultSetLivresDepasses.getInt("livresDepasses");

            // Affichage des statistiques
            System.out.println("Statistiques de la bibliothèque :");
            System.out.println("+----------------------------------------------------------+-----------------------+");
            System.out.println("| Nombre total d'emprunteurs                                     " + totalEmprunteurs);
            System.out.println("+----------------------------------------------------------+-----------------------+");
            System.out.println("| Nombre d'emprunteurs qui ont dépassé la date de retour        " + emprunteursNonRetournes);
            System.out.println("+----------------------------------------------------------+-----------------------+");
            System.out.println("| Nombre de livres disponibles                                   " + livresDisponibles);
            System.out.println("+----------------------------------------------------------+-----------------------+");
            System.out.println("| Nombre de livres en attente de retour                          " + livresEnAttente);
            System.out.println("+----------------------------------------------------------+-----------------------+");
            System.out.println("| Nombre de livres qui ont dépassé la date de retour             " + livresDepasses);
            System.out.println("+----------------------------------------------------------+-----------------------+");

            resultSetEmprunteurs.close();
            preparedStatementEmprunteurs.close();
            resultSetEmprunteursNonRetournes.close();
            preparedStatementEmprunteursNonRetournes.close();
            resultSetLivresDisponibles.close();
            preparedStatementLivresDisponibles.close();
            resultSetLivresEnAttente.close();
            preparedStatementLivresEnAttente.close();
            resultSetLivresDepasses.close();
            preparedStatementLivresDepasses.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateStatisticsToFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("statistiques.txt")))) {
            // Appelez la fonction statistiques pour générer les statistiques
            statistiques();

            // Enregistrez les statistiques dans un fichier statistiques.txt
            writer.println("Statistiques de la bibliothèque :");
            writer.println("+----------------------------------------------------------+-----------------------+");
            writer.println("| Nombre total d'emprunteurs                                     " + totalEmprunteurs);
            writer.println("+----------------------------------------------------------+-----------------------+");
            writer.println("| Nombre d'emprunteurs qui ont dépassé la date de retour        " + emprunteursNonRetournes);
            writer.println("+----------------------------------------------------------+-----------------------+");
            writer.println("| Nombre de livres disponibles                                   " + livresDisponibles);
            writer.println("+----------------------------------------------------------+-----------------------+");
            writer.println("| Nombre de livres en attente de retour                          " + livresEnAttente);
            writer.println("+----------------------------------------------------------+-----------------------+");
            writer.println("| Nombre de livres qui ont dépassé la date de retour             " + livresDepasses);
            writer.println("+----------------------------------------------------------+-----------------------+");
            System.out.println("Les statistiques ont été enregistrées dans le fichier statistiques.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

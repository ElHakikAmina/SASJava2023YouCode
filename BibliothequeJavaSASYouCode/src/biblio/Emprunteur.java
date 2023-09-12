package biblio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Emprunteur {
	private int id;
	private String nom_complet; 
	private String numTel;  
	private String adresse;
	
	public static Connection connexion = ConnexionDB.getInstance().getConnexion();
	
	private static Scanner scanner = new Scanner(System.in);
	
	
	

	
	public void afficherEmprunteurEnRetard() {
        try {
            // Obtenir la date d'aujourd'hui
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateAujourdhui = new Date();
            String dateAujourdhuiStr = dateFormat.format(dateAujourdhui);

            // Requête SQL pour sélectionner les emprunteurs en retard
            String requeteSQL = "SELECT e.nom_complet, e.numTel, e.adresse " +
                                "FROM emprunteur e " +
                                "JOIN emprunt_livre el ON e.id = el.id_emprunteur " +
                                "JOIN isbn i ON el.livre_ISBN = i.ISBN " +
                                "WHERE i.status = '0' AND el.Date_retour < ?";

            PreparedStatement preparedStatement = connexion.prepareStatement(requeteSQL);
            preparedStatement.setString(1, dateAujourdhuiStr);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Emprunteurs en retard :");
                do {
                    String nomComplet = resultSet.getString("nom_complet");
                    String numTel = resultSet.getString("numTel");
                    String adresse = resultSet.getString("adresse");
                    
                    System.out.println("Nom complet : " + nomComplet);
                    System.out.println("Numéro de téléphone : " + numTel);
                    System.out.println("Adresse : " + adresse);
                    System.out.println();
                } while (resultSet.next());
            } else {
                System.out.println("Aucun emprunteur en retard trouvé.");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	
	
	
	
	public void afficherTousLesEmprunteurs() 
	{
		 try {
	            Statement statement = connexion.createStatement();
	            String query = "SELECT * FROM emprunteur";

	            ResultSet resultSet = statement.executeQuery(query);
	            System.out.println("        -----------------------------------------------------------------------");
	            System.out.println("       | Voilà les informations de tous les emprunteurs dans la base de donnée |");
	            System.out.println("        -----------------------------------------------------------------------");

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String nomComplet = resultSet.getString("nom_complet");
	                String numTel = resultSet.getString("numTel");
	                String adresse = resultSet.getString("adresse");

	                System.out.println("ID : " + id);
	                System.out.println("Nom complet : " + nomComplet);
	                System.out.println("Numéro de téléphone : " + numTel);
	                System.out.println("Adresse : " + adresse);
	                System.out.println("+-------------------------------------+");
	                System.out.println();
	            }

	            resultSet.close();
	            statement.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public int getId()
	{
		return this.id;
	}
	//
	
	public void ajoutEmprunteur() {
	    System.out.println("Donner le nom complet : ");
	    this.nom_complet = scanner.nextLine();
	    System.out.println("Donner le numéro de tel : ");
	    this.numTel = scanner.nextLine();

	    if (numeroDeTelephoneExisteDeja(this.numTel)) {
	        System.out.println(ConsoleColors.RED+"Ce numéro de téléphone existe déjà. L'emprunteur n'a pas été ajouté."+ ConsoleColors.RESET);
	    } else {
	        System.out.println("Donner l'adresse : ");
	        this.adresse = scanner.nextLine();
	        PreparedStatement preparedStatement = null;

	        try {
	            String query = "INSERT INTO emprunteur (nom_complet, numTel, adresse) VALUES (?, ?, ?)";

	            preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setString(1, this.nom_complet);
	            preparedStatement.setString(2, this.numTel);
	            preparedStatement.setString(3, this.adresse);

	            int rowsInserted = preparedStatement.executeUpdate();

	            if (rowsInserted > 0) {
	                System.out.println(ConsoleColors.GREEN+"Emprunteur ajouté avec succès."+ ConsoleColors.RESET);
	            } else {
	                System.out.println(ConsoleColors.RED+"L'ajout de l'emprunteur a échoué."+ ConsoleColors.RESET);
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
	        }
	    }
	}

	
		//
	public boolean numeroDeTelephoneExisteDeja(String numTel) {
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        String query = "SELECT COUNT(*) FROM emprunteur WHERE numTel = ?";
	        preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setString(1, numTel);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            int count = resultSet.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return false;
	}
	
}

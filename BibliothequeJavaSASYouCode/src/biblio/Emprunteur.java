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
	private int numTel;  
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
	
	public void ajoutEmprunteur() {
	    System.out.println("Donner le nom complet : ");
	    this.nom_complet=scanner.nextLine();
	    System.out.println("Donner le numero de tel : ");
	    this.numTel=scanner.nextInt();
	    scanner.nextLine();
	    System.out.println("Donner l'adresse : ");
	    this.adresse=scanner.nextLine();
	    PreparedStatement preparedStatement = null;

	    try {
	         
	        String query = "INSERT INTO emprunteur (nom_complet, numTel, adresse) VALUES (?, ?, ?)";

	        
	        preparedStatement = connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, this.nom_complet);
	        preparedStatement.setInt(2, this.numTel);
	        preparedStatement.setString(3, this.adresse);

	        
	        int rowsInserted = preparedStatement.executeUpdate();
	        ResultSet rs = preparedStatement.getGeneratedKeys();
	        if (rs.next()) {
	        	  int lastInsertedID = rs.getInt(1);
	                //System.out.println("Last Inserted ID: " + lastInsertedID);
	                this.id=lastInsertedID;
            }

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
	    }
	}
}

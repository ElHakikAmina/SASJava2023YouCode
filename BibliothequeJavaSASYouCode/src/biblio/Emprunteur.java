package biblio;

import java.sql.Connection;
import java.sql.ResultSet;
 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Emprunteur {
	private int id;
	private String nom_complet; 
	private int numTel;  
	private String adresse;
	
	public static Connection connexion = ConnexionDB.getInstance().getConnexion();
	
	private static Scanner scanner = new Scanner(System.in);
	
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

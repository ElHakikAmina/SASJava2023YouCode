package Biblio;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;



public class Livre {
	private String ISBN; 
	private String titre; 
	private String auteur; 
	private int quantite;
	
	 private static Scanner scanner = new Scanner(System.in);
	//
	 
	 
 public Livre(){ 
		 
	 }
 
 
 public void miseAJourQuantiteLivre(String isbn, int nouvelleQuantite) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connexion = ConnexionBDD.seConnecterDB();

	        
	        String query = "UPDATE livre SET quantite = ? WHERE ISBN = ?";

	        preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setInt(1, nouvelleQuantite);
	        preparedStatement.setString(2, isbn);

	        int rowsUpdated = preparedStatement.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("La quantité du livre avec l'ISBN " + isbn + " a été mise à jour avec succès.");
	        } else {
	            System.out.println("La mise à jour de la quantité du livre a échoué.");
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
	        /*if (connexion != null) {
	            ConnexionBDD.fermerConnexion(connexion);
	        }*/
	    }
	}

 public boolean rechercherLivreParISBN(String isbn) {
	    try {
	        Connection connexion = ConnexionBDD.seConnecterDB();
	        String query = "SELECT * FROM livre WHERE ISBN = ?";

	        PreparedStatement preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setString(1, isbn);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            String isbnResult = resultSet.getString("ISBN");
	            String titre = resultSet.getString("titre");
	            String auteur = resultSet.getString("auteur");
	            int quantite = resultSet.getInt("quantite");

	            System.out.println("ISBN: " + isbnResult);
	            System.out.println("Titre: " + titre);
	            System.out.println("Auteur: " + auteur);
	            System.out.println("Quantité: " + quantite);

	            
	            ConnexionBDD.fermerConnexion(connexion);
	            return true;
	        } else {
	            System.out.println("Le livre avec l'ISBN " + isbn + " n'existe pas.");

	            
	            ConnexionBDD.fermerConnexion(connexion);
	            return false;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        return false;
	    }
	}


	
 
 
 
 
 
 
	 
	 //
	 
	

	 
	
	 public void rechercherLivre() {
		 String choix,titre,auteur,isbn;
		 System.out.println("chooisiser comment chercher");
		 System.out.println("1- par titre");
		 System.out.println("2 - par auteur");
		 System.out.println("3 - par ISBN");
		 System.out.println("Donner votre choix: ");
		 choix = scanner.nextLine();
		 while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") )
		 {
			 System.out.println("chooisiser comment chercher");
			 System.out.println("1- par titre");
			 System.out.println("2 - par auteur");
			 System.out.println("3 - par ISBN");
			 System.out.println("Donner votre choix: ");
			 choix = scanner.nextLine();
		 }
		 
		 if(choix.equals("1"))
		 {
			 System.out.println("donner le titre à chercher: ");
			 titre = scanner.nextLine();
			 rechercheParTitre( titre);
		 }else if(choix.equals("2"))
		 {
			 System.out.println("donner l'auteur à chercher: ");
			 auteur = scanner.nextLine();
			 rechercheParAuteur( auteur);
		 }else if(choix.equals("3"))
		 {
			 System.out.println("donner ISBN à chercher: ");
			 isbn = scanner.nextLine();
			 rechercherLivreParISBN( isbn);
		 }
	 }
	
	public void rechercheParTitre(String titreChercher)
	{
		 try {
	            Connection connexion = ConnexionBDD.seConnecterDB();
	            String query = "SELECT * FROM livre WHERE titre = ?";

	            PreparedStatement preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setString(1, titreChercher);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                String isbn = resultSet.getString("ISBN");
	                String titre = resultSet.getString("titre");
	                String auteur = resultSet.getString("auteur");
	                int quantite = resultSet.getInt("quantite");

	                System.out.println("ISBN: " + isbn);
	                System.out.println("Titre: " + titre);
	                System.out.println("Auteur: " + auteur);
	                System.out.println("Quantité: " + quantite);
	                System.out.println();
	            }

	            ConnexionBDD.fermerConnexion(connexion);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void rechercheParAuteur(String auteurChercher)
	{
		try {
		Connection connexion = ConnexionBDD.seConnecterDB();
		String query = "SELECT * FROM livre where auteur = ? ";
        PreparedStatement preparedStatement = connexion.prepareStatement(query);

		preparedStatement.setString(1, auteurChercher);
		
        
        ResultSet resultSet =  preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            String isbn = resultSet.getString("ISBN");
            String titre = resultSet.getString("titre");
            String auteur = resultSet.getString("auteur");
            int quantite = resultSet.getInt("quantite");

            System.out.println("ISBN: " + isbn);
            System.out.println("Titre: " + titre);
            System.out.println("Auteur: " + auteur);
            System.out.println("Quantité: " + quantite);
            System.out.println();
        }

        ConnexionBDD.fermerConnexion(connexion);

    } catch (SQLException e) {
        e.printStackTrace();
    }
		
}
	
	
	 
	
	 public void supprimerLivre(String isbn) {
		 String confirmation;
		 System.out.println("Etes vous sur de vouloir supprimer le livre ISBN = "+isbn);
		 System.out.println("yes/non ** [ ATTENTION CETTE ETAPE EST IRREVERSIBLE] **");
		 confirmation = scanner.nextLine();
		 if (!confirmation.equals("yes")) System.out.println("supprission annulé");
		 else if(confirmation.equals("yes"))
		 try {
			 Connection connection = ConnexionBDD.seConnecterDB();
			 String query = "DELETE FROM livre WHERE ISBN= ? ";
			 PreparedStatement preparedStatement= connection.prepareStatement(query);
			  preparedStatement.setString(1,isbn);
			  
			  preparedStatement.executeUpdate();
			  System.out.println("le livre avec ISBN ="+isbn+" a bien été suprimé");
		 }catch (SQLException e){
			 e.printStackTrace();
		 }
		 
		
		  
	 }
	 
	 public void modifierInfoLivre (String isbn) {
		 
	
		
	 }
	 
	 
	 
	 //
	protected  void saisi()
	{
		System.out.println("Saisissez l'ISBN du livre : ");
        this.ISBN = scanner.nextLine();

        System.out.println("Saisissez le titre du livre : ");
        this.titre = scanner.nextLine();

        System.out.println("Saisissez l'auteur du livre : ");
        this.auteur = scanner.nextLine();

        System.out.println("Saisissez la quantité du livre : ");
        this.quantite = scanner.nextInt();
        
        ajouterLivre(ISBN,titre,auteur,quantite);
	}
	
	//
	
	protected static void afficherTousLesLivres() {
        try {
            Connection connexion = ConnexionBDD.seConnecterDB();
            String query = "SELECT * FROM livre"; 

            
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            

            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                int quantite = resultSet.getInt("quantite");

                
                System.out.println("ISBN: " + isbn);
                System.out.println("Titre: " + titre);
                System.out.println("Auteur: " + auteur);
                System.out.println("Quantite: " + quantite);
                System.out.println();
            }

            ConnexionBDD.fermerConnexion(connexion);
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//
	
	public  void ajouterLivre(String ISBN, String titre, String auteur, int quantite) {
        try {
            Connection connexion = ConnexionBDD.seConnecterDB();
            
            String query = "INSERT INTO livre (ISBN, titre, auteur, quantite) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, ISBN);
            preparedStatement.setString(2, titre);
            preparedStatement.setString(3, auteur);
            preparedStatement.setInt(4, quantite);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le livre a été ajouté avec succès.");
            } else {
                System.out.println("Erreur lors de l'ajout du livre.");
            }

            ConnexionBDD.fermerConnexion(connexion);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}

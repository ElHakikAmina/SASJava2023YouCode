package biblio;

import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;



public class Livre {
	private String ISBN; 
	private String titre; 
	private String auteur; 
	private String quantite;
	private int id;
	
	 private static Scanner scanner = new Scanner(System.in);
	//
	 public static Connection connexion = ConnexionDB.getInstance().getConnexion();
	 
	 
 public Livre(){ 
		 
	 }
 
 public void modifierAuteur(String isbn) {
     try {
    	 System.out.println("Donner le nouveau auteur : ");
    	 String newAuteur = scanner.nextLine();
         String query = "UPDATE livre JOIN isbn ON livre.id = isbn.id_livre SET livre.auteur = ? WHERE isbn.ISBN = ? ";
         PreparedStatement preparedStatement = connexion.prepareStatement(query);
         preparedStatement.setString(1, newAuteur);
         preparedStatement.setString(2, isbn);
         int rowsUpdated = preparedStatement.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Auteur modifié avec succès.");
         } else {
             System.out.println("Erreur lors de la modification de l'auteur.");
         }
         
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
 
 
 public void modifierInfoLivre () {
	 String ibnsAModifier;
	 System.out.println("Donner le ISBN du livre à modifier");
	 ibnsAModifier= scanner.nextLine();
	 //le ivre existe ou non????
	 //la cas ou : si livre existe 
	 System.out.println("1- modifer le titre");
	 System.out.println("2 - modifier l'auteur");
	 System.out.println("3 - Quité");
	 String choix = scanner.nextLine();
	 while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3"))
	 {
		 System.out.println("1- modifer le titre");
		 System.out.println("2 - modifier l'auteur");
		 System.out.println("3 - modifier les deux");
		 System.out.println("4 - Quité");
		 choix = scanner.nextLine();
	 }
	 
	 switch (choix)
	 {
	 //case "1":;break;
	 case "2":System.out.println("hhh");this.modifierAuteur(ibnsAModifier);break;
	 //case "3":;break;
	 //case "4":;break;
	 }
	 // 
	 /*try {
		 Connection connexion = 
	 }catch(SQLException e)
	 {
		 e.printStackTrace();
	 }*/
 }
 
 
 
 
 
 
 public void miseAJourQuantiteLivre(String isbn, int nouvelleQuantite) {
	    Connection connexion = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        //connexion = ConnexionDB.seConnecterDB();

	        
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
	        //Connection connexion = ConnexionDB.seConnecterDB();
	
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

	            
	            //ConnexionDB.fermerConnexion(connexion);
	            return true;
	        } else {
	            System.out.println("Le livre avec l'ISBN " + isbn + " n'existe pas.");

	            
	            //ConnexionDB.fermerConnexion(connexion);
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
	            //Connection connexion = ConnexionDB.seConnecterDB();
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

	            //ConnexionDB.fermerConnexion(connexion);

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void rechercheParAuteur(String auteurChercher)
	{
		try {
		//Connection connexion = ConnexionDB.seConnecterDB();
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

        //ConnexionDB.fermerConnexion(connexion);

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
			 //Connection connection = ConnexionDB.seConnecterDB();
			 String query = "DELETE FROM livre WHERE ISBN= ? ";
			 PreparedStatement preparedStatement= connection.prepareStatement(query);
			  preparedStatement.setString(1,isbn);
			  
			  preparedStatement.executeUpdate();
			  System.out.println("le livre avec ISBN ="+isbn+" a bien été suprimé");
		 }catch (SQLException e){
			 e.printStackTrace();
		 }
		 
		
		  
	 }
	 
	 
	 
	 // Méthode pour vérifier si une chaîne est un entier
	    private boolean estEntier(String chaine) {
	        try {
	            Integer.parseInt(chaine);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }

	    // Méthode pour vérifier si une chaîne est un float
	    private boolean estFloat(String chaine) {
	        try {
	            Float.parseFloat(chaine);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	 
	 //
	protected  void saisi()
	{
		

        System.out.println("Saisissez le titre du livre : ");
        this.titre = scanner.nextLine();

        System.out.println("Saisissez l'auteur du livre : ");
        this.auteur = scanner.nextLine();
        
         

       /* System.out.println("Saisissez la quantité du livre : ");
        this.quantite = scanner.nextInt();
        
        System.out.println("Saisissez l'ISBN du livre : ");
        this.ISBN = scanner.nextLine();
        */
        // Demander la quantité jusqu'à ce qu'elle soit un entier valide
        while (true) {
            System.out.println("Saisissez la quantité du livre : ");
            String quantiteStr = scanner.nextLine();

            if (estEntier(quantiteStr)) {
                // Vérifier si la quantité est un entier
                this.quantite = quantiteStr;
                break;
            } else if (estFloat(quantiteStr)) {
                // Vérifier si la quantité est un float (nombre à virgule flottante)
                System.out.println("La quantité ne peut pas être un nombre à virgule flottante. Veuillez réessayer.");
            } else {
                // La quantité n'est ni un entier ni un float
                System.out.println("La quantité doit être un nombre entier. Veuillez réessayer.");
            }
        }
        ajouterLivre(titre,auteur,quantite);
        // Demander l'ISBN quantité fois
        for (int i = 0; i < Integer.parseInt(quantite); i++) {
            System.out.println("Saisissez l'ISBN du livre " + (i + 1) + " : ");
            String isbn = scanner.nextLine();
            ISBN isbnClasse = new ISBN();
            isbnClasse.ajouterISBN(isbn,"1",this.id);
            // Faites quelque chose avec l'ISBN (par exemple, ajouter à une liste ou à la base de données)
        }
        
        
        
      
	}
	
	//
	
	public static  void afficherTousLesLivres() {
        try {
            //Connection connexion = ConnexionDB.seConnecterDB();
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

           // ConnexionDB.fermerConnexion(connexion);
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//
	
	public  void ajouterLivre( String titre, String auteur, String quantite) {
        try {
                       
            String query = "INSERT INTO livre ( titre, auteur, quantite) VALUES ( ?, ?, ?)";

            PreparedStatement preparedStatement = connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            //preparedStatement.setString(1, ISBN);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, quantite);

            int rowsInserted = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            
            if (resultSet.next()) {
                int lastInsertedID = resultSet.getInt(1);
                //System.out.println("Last Inserted ID: " + lastInsertedID);
                this.id=lastInsertedID;
            }
            
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

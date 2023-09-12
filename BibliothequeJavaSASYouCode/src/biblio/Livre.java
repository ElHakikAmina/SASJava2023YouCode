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
	
	private ISBN isbnClasse= new ISBN();
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
             System.out.println(ConsoleColors.GREEN+"Auteur modifié avec succès."+ ConsoleColors.RESET);
         } else {
             System.out.println(ConsoleColors.RED+"Erreur lors de la modification de l'auteur."+ ConsoleColors.RESET);
         }
         
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
 
 //
 
 public void modifierTitre(String isbn) {
     try {
    	 System.out.println("Donner le nouveau titre : ");
    	 String newTitre = scanner.nextLine();
         String query = "UPDATE livre JOIN isbn ON livre.id = isbn.id_livre SET livre.titre = ? WHERE isbn.ISBN = ? ";
         PreparedStatement preparedStatement = connexion.prepareStatement(query);
         preparedStatement.setString(1, newTitre);
         preparedStatement.setString(2, isbn);
         int rowsUpdated = preparedStatement.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println(ConsoleColors.GREEN+"Titre modifié avec succès."+ ConsoleColors.RESET);
         } else {
             System.out.println(ConsoleColors.RED+"Erreur lors de la modification de l'auteur."+ ConsoleColors.RESET);
         }
         
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
 
 //
 
 public void modifierTitreAuteur(String isbn) {
     try {
    	 System.out.println("Donner le nouveau titre : ");
    	 String newTitre = scanner.nextLine();
         
    	 System.out.println("Donner le nouveau auteur : ");
    	 String newAuteur = scanner.nextLine();
    	 
    	 String query = "UPDATE livre JOIN isbn ON livre.id = isbn.id_livre SET livre.auteur= ? , livre.titre = ? WHERE isbn.ISBN = ? ";
         PreparedStatement preparedStatement = connexion.prepareStatement(query);
         preparedStatement.setString(1, newAuteur);
         preparedStatement.setString(2, newTitre);
         preparedStatement.setString(3, isbn);
         int rowsUpdated = preparedStatement.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println(ConsoleColors.GREEN+"Auteur modifié avec succès."+ ConsoleColors.RESET);
         } else {
             System.out.println(ConsoleColors.RED+"Erreur lors de la modification de l'auteur."+ ConsoleColors.RESET);
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
	 while(!isbnClasse.ISBNexiste(ibnsAModifier))
	 {
		 System.out.println(ConsoleColors.RED+"ATENTION! "+ibnsAModifier+" n'existe pas! "+ ConsoleColors.RESET);
		 System.out.println("Donner le ISBN du livre à modifier tapper Q pour quitter : ");
		 ibnsAModifier= scanner.nextLine();
		
		 if (ibnsAModifier.equals("Q")) break;
	 }
	 if(ibnsAModifier.equals("Q")) System.out.println("Modification quittée");
	 
	 //la cas ou : si livre existe 
	 if(isbnClasse.ISBNexiste(ibnsAModifier))
	 {
		 System.out.println("1- modifer le titre");
		 System.out.println("2 - modifier l'auteur");
		 System.out.println("3 - modifier les deux");
		 System.out.println("4 - Quité");
		 String choix = scanner.nextLine();
		 while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4"))
		 {
			 System.out.println("1- modifer le titre");
			 System.out.println("2 - modifier l'auteur");
			 System.out.println("3 - modifier les deux");
			 System.out.println("4 - Quité");
			 choix = scanner.nextLine();
		 }
		 
		 switch (choix)
		 {
		 case "1":this.modifierTitre(ibnsAModifier);;break;
		 case "2":this.modifierAuteur(ibnsAModifier);break;
		 case "3":this.modifierTitreAuteur(ibnsAModifier);break;
		 case "4":break;
		 }
	 }
	
	
 }
 
 public void decrementerQuantiteLivre(String isbn) {

	    try {

	        String query = "UPDATE livre SET quantite = quantite - 1 WHERE id IN (SELECT id_livre FROM isbn WHERE ISBN = ?)";

	        PreparedStatement preparedStatement = connexion.prepareStatement(query);
	        preparedStatement.setString(1, isbn);

	        int rowsUpdated = preparedStatement.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("La quantité du livre avec l'ISBN " + isbn + " a été mise à jour avec succès.");
	        } else {
	            System.out.println("La mise à jour de la quantité du livre a échoué.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	}

 public void rechercherLivreParISBN(String isbn) {
	    try {
	        
	        String query = "SELECT * from isbn i INNER JOIN livre l ON i.id_livre = l.id where i.ISBN = ? ";
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

	        } else {
	            System.out.println(ConsoleColors.RED+"Le livre avec l'ISBN " + isbn + " n'existe pas."+ ConsoleColors.RESET);

	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	       
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
	            String query = "SELECT * FROM livre  WHERE titre = ?";

	            PreparedStatement preparedStatement = connexion.prepareStatement(query);
	            preparedStatement.setString(1, titreChercher);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                String titre = resultSet.getString("titre");
	                String auteur = resultSet.getString("auteur");
	                int quantite = resultSet.getInt("quantite");

	               
	                System.out.println(" Titre    : " + titre);
	                System.out.println(" Auteur   : " + auteur);
	                System.out.println(" Quantité : " + quantite);
	                System.out.println();
	            }

	           

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void rechercheParAuteur(String auteurChercher)
	{
		try {
		
		String query = "SELECT * FROM livre where auteur = ? ";
        PreparedStatement preparedStatement = connexion.prepareStatement(query);

		preparedStatement.setString(1, auteurChercher);
		
        
        ResultSet resultSet =  preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            
            String titre = resultSet.getString("titre");
            String auteur = resultSet.getString("auteur");
            int quantite = resultSet.getInt("quantite");

            
            System.out.println(" Titre    : " + titre);
            System.out.println(" Auteur   : " + auteur);
            System.out.println(" Quantité : " + quantite);
            System.out.println();
        }


    } catch (SQLException e) {
        e.printStackTrace();
    }
		
}
	//
	
	public static boolean isbnExistDansEmpruntLivre(String isbn) {
        try  {
            String query = "SELECT COUNT(*) FROM emprunt_livre WHERE livre_ISBN = ?";
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    
                    }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	 
	//
	 public void supprimerLivre() {
		 String isbn;
		 System.out.println("donner le ISBN à supprimer : ");
		 isbn = scanner.nextLine();
		 if(!Livre.isbnExistDansEmpruntLivre(isbn))
		 {
			 String confirmation;
			 System.out.println("Etes vous sur de vouloir supprimer le livre ISBN = "+isbn);
			 System.out.println(ConsoleColors.RED+"yes/non ** [ ATTENTION CETTE ETAPE EST IRREVERSIBLE] **"+ ConsoleColors.RESET);
			 confirmation = scanner.nextLine();
			 if (!confirmation.equals("yes")) System.out.println(ConsoleColors.RESET+"supprission annulé"+ ConsoleColors.RESET);
			 else if(confirmation.equals("yes"))
			 try {
				 
				 String query = "DELETE FROM isbn WHERE ISBN= ? ";
				 PreparedStatement preparedStatement= connexion.prepareStatement(query);
				  preparedStatement.setString(1,isbn);
				  
				  preparedStatement.executeUpdate();
				  System.out.println("le livre avec ISBN ="+isbn+" a bien été suprimé");
				  decrementerQuantiteLivre(isbn);
			 }catch (SQLException e){
				 e.printStackTrace();
			 }	  
		 }else {
			 System.out.println(ConsoleColors.RED+"Vous ne pouvez pas supprimer ce livre, le livre est deja emprunté"+ ConsoleColors.RESET);
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
        
        
        while (true) {
            System.out.println("Saisissez la quantité du livre : ");
            String quantiteStr = scanner.nextLine();

            if (estEntier(quantiteStr)) {
                // Vérifier si la quantité est un entier
                this.quantite = quantiteStr;
                break;
            } else if (estFloat(quantiteStr)) {
                // Vérifier si la quantité est un float (nombre à virgule flottante)
                System.out.println(ConsoleColors.BACKGROUND_RED +"La quantité ne peut pas être un nombre à virgule flottante. Veuillez réessayer."+ ConsoleColors.RESET);
            } else {
                // La quantité n'est ni un entier ni un float
                System.out.println(ConsoleColors.RED+"La quantité doit être un nombre entier. Veuillez réessayer."+ ConsoleColors.RESET);
            }
        }
        ajouterLivre(titre,auteur,quantite);
        
        for (int i = 0; i < Integer.parseInt(quantite); i++) {
        	System.out.println("Saisissez l'ISBN du livre " + (i + 1) + " : ");
            String isbn = scanner.nextLine();
        	while(isbnClasse.ISBNexiste(isbn))
        	{
        		System.out.println(ConsoleColors.RED+isbn +"existe déjà!"+ConsoleColors.RESET+" Saisissez l'ISBN du livre " + (i + 1) + " : ");
        		 isbn = scanner.nextLine();
        	}
            
            isbnClasse.ajouterISBN(isbn,"1",this.id);
            
        }
        
	}
	
	//
	
	public static  void afficherTousLesLivresDisponible() {
        try {
            
            String query = "SELECT * FROM isbn i INNER JOIN livre l on i.id_livre =l.id where status ='1'"; 

            
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            

            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                int quantite = resultSet.getInt("quantite");

                
                System.out.println("ISBN     : " + isbn);
                System.out.println("Titre    : " + titre);
                System.out.println("Auteur   : " + auteur);
                System.out.println("Quantite : " + quantite);
                System.out.println();
            }

        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//
	
	public static  void afficherTousLesLivresEmprunte() {
        try {
            String query = "SELECT * FROM isbn i INNER JOIN livre l on i.id_livre =l.id where status ='0'"; 

            
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            

            while (resultSet.next()) {
                String isbn = resultSet.getString("ISBN");
                String titre = resultSet.getString("titre");
                String auteur = resultSet.getString("auteur");
                int quantite = resultSet.getInt("quantite");

                
                System.out.println("ISBN     : " + isbn);
                System.out.println("Titre    : " + titre);
                System.out.println("Auteur   : " + auteur);
                System.out.println("Quantite : " + quantite);
                System.out.println();
            }

        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	//
	public  void ajouterLivre( String titre, String auteur, String quantite) {
        try {
                       
            String query = "INSERT INTO livre ( titre, auteur, quantite) VALUES ( ?, ?, ?)";

            PreparedStatement preparedStatement = connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, quantite);

            int rowsInserted = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            
            if (resultSet.next()) {
                int lastInsertedID = resultSet.getInt(1);
                
                this.id=lastInsertedID;
            }
            
            if (rowsInserted > 0) {
                System.out.println(ConsoleColors.GREEN+"Le livre a été ajouté avec succès."+ ConsoleColors.RESET);
                
            } else {
                System.out.println(ConsoleColors.RED+"Erreur lors de l'ajout du livre."+ ConsoleColors.RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

}

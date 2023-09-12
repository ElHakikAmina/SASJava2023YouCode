package biblio;

import java.util.Scanner;
import java.io.IOException;
import java.sql.Connection;

public class Biblio {

	public static void afficherMenu() {
		System.out.println("\n                ---------------------------------------------------");
		System.out.println("               |                        MENU                       |");
		System.out.println("                ---------------------------------------------------");
        System.out.println("               |       1. Ajouter un livre                         |");
        System.out.println("               |       2. Afficher la liste des livres disponibles |");
        System.out.println("               |       3. Rechercher un livre                      |");
        System.out.println("               |       4. Emprunter un livre                       |");
        System.out.println("               |       5. Retourner un livre                       |");
        System.out.println("               |       6. Afficher la liste des livres empruntés   |");
        System.out.println("               |       7. Supprimer un livre                       |");
        System.out.println("               |       8. Modifier les informations d'un livre     |");
        System.out.println("               |       9. Afficher les livre perdus                |");
        System.out.println("               |       10. Statistique                             |");
        System.out.println("               |       11. Afficher tous les emprunteur            |");
        System.out.println("               |       12. Afficher les emprunteur en retard       |");
        System.out.println("               |       13. Ajouter un emprunteur                   |");
        System.out.println("               |       0. Quitter                                  |");
        System.out.println("               |---------------------------------------------------|");
    }
    public static void main(String[] args) throws InterruptedException  {
    	
    	
        
    	
    	
    	
    	
    	
    	System.out.print("\n"); 
    	System.out.println(ConsoleColors.YELLOW+"               /*------------------------------------------------------------------------*/"+ConsoleColors.RESET);
        String texte = "BIBLIOTHEQUE MUNICIPALE DE PARIS ";
        System.out.print("\n");
        System.out.print("                                     ");
        for (int i = 0; i < texte.length(); i++) {
            System.out.print(texte.charAt(i));
            Thread.sleep(100); // Pause de 100 millisecondes entre chaque caractère
        }
        System.out.print("\n");
        System.out.print("\n");
        System.out.println(ConsoleColors.YELLOW+"               /*-------------------- "+ConsoleColors.RESET+"Un Projet de SAS YouCode Java"+ConsoleColors.YELLOW+" ---------------------*/"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW+"               /*-------------------- "+ConsoleColors.RESET+"Réalisé par : EL HAKIK Amina"+ConsoleColors.YELLOW+" ----------------------*/"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW+"               /*------------------------------------------------------------------------*/"+ConsoleColors.RESET);
        System.out.println(); // Aller à la ligne après l'affichage complet
    
     
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
         
         
         
     






    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//DataFile dataFile = new DataFile();
    	//dataFile.generateStatisticsToFile();
    	Livre livre = new Livre ();
    	EmpruntLivre empruntLivre = new EmpruntLivre();
    	//System.out.println(empruntLivre.totalNombreEmprunteur());
    	//System.out.println(livre.rechercherLivreParISBN("1khf"));
    	// String pwd="1234";
         Scanner scanner = new Scanner(System.in); 
         
         Emprunteur emprunteur = new Emprunteur();
     
         
         
         
         while (true) {
             // Demandez à l'utilisateur de saisir le mot de passe
             System.out.print("Entrez le mot de passe : ");
             String inputPassword = scanner.nextLine();

             try {
                 if (Login.verifyPassword(inputPassword)) {
                     System.out.println(ConsoleColors.GREEN +"Mot de passe correct. Accès autorisé."+ ConsoleColors.RESET);
                     break; // Sortez de la boucle si le mot de passe est correct.
                 } else {
                     System.out.println(ConsoleColors.RED +"Mot de passe incorrect. Veuillez réessayer."+ ConsoleColors.RESET);
                 }
             } catch (IOException e) {
                 System.err.println(ConsoleColors.RED +"Une erreur s'est produite lors de la vérification du mot de passe."+ ConsoleColors.RESET);
                 break; // Sortez de la boucle en cas d'erreur.
             }
         }
         
         
         
         
         
         
         
         /*System.out.println("Mot e passe: ");
    	 String pwsSaisi = scanner.nextLine();
         while(!pwd.equals(pwsSaisi)) {
        	 System.out.println("Mot e passe: ");
        	 pwsSaisi = scanner.nextLine();
         }*/
         String choix;
         afficherMenu(); 
         System.out.println("\n   donner votre choix: ");
         choix=scanner.nextLine();
         while(!choix.equals("0"))
         {
        	 switch (choix)
        	 {
        	 //case "0":System.out.println("Bye");break;
        	 case "1": livre.saisi();break;
        	 case "2":Livre.afficherTousLesLivresDisponible();break;
        	 case "3":livre.rechercherLivre();break;
        	 case "4":empruntLivre.empruntterLivre();break;
        	 case "5":empruntLivre.retournerLivre();break;
        	 case "6":Livre.afficherTousLesLivresEmprunte();break;
        	 case "7":livre.supprimerLivre();break;
        	 case "8":livre.modifierInfoLivre();break;
        	 case "9":empruntLivre.livrePerdus();break;
        	 case "10":empruntLivre.statistiques();break;
        	 case "11":emprunteur.afficherTousLesEmprunteurs();break;
        	 case "12":emprunteur.afficherEmprunteurEnRetard();break;
        	 case "13":emprunteur.ajoutEmprunteur();break;
        	 
        	 
        	 /*
        	 
        	 
        	 */
        	 //default:break;
        	 }
        		 
        	 afficherMenu(); 
             System.out.println("donner votre choix: ");
             choix=scanner.nextLine();
         }
    }
}
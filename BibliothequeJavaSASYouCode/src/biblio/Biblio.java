package biblio;

import java.util.Scanner;
import java.io.IOException;
//import java.sql.Connection;

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
	//
	public static void afficheMessageErreurChoix(String c)
	{
		if(
   			 !c.equals("1") &&
   			 !c.equals("2") &&
   			 !c.equals("3") &&
   			 !c.equals("4") &&
   			 !c.equals("5") &&
   			 !c.equals("6") &&
   			 !c.equals("7") &&
   			 !c.equals("8") &&
   			 !c.equals("9") &&
   			 !c.equals("10") &&
   			 !c.equals("11") &&
   			 !c.equals("12") &&
   			 !c.equals("13") 
   			 )
	   	 {
	   		 System.out.print(ConsoleColors.BACKGROUND_RED +" ATTENTION! "+ ConsoleColors.RESET);
	   		 System.out.println(ConsoleColors.RED+" Entrer un nombre dans le menu."+ ConsoleColors.RESET);
	   	 }
	}
    public static void main(String[] args) throws InterruptedException  {
    	/*
    
    	        System.out.println(ConsoleColors.RESET + "Texte en couleur par défaut");
    	        System.out.println(ConsoleColors.BLACK + "Texte en noir");
    	        System.out.println(ConsoleColors.RED + "Texte en rouge");
    	        System.out.println(ConsoleColors.GREEN + "Texte en vert");
    	        System.out.println(ConsoleColors.YELLOW + "Texte en jaune");
    	        System.out.println(ConsoleColors.BLUE + "Texte en bleu");
    	        System.out.println(ConsoleColors.MAGENTA + "Texte en magenta");
    	        System.out.println(ConsoleColors.CYAN + "Texte en cyan");
    	        System.out.println(ConsoleColors.WHITE + "Texte en blanc");

    	        System.out.println(ConsoleColors.BOLD + "Texte en gras");
    	        System.out.println(ConsoleColors.UNDERLINE + "Texte souligné");

    	        System.out.println(ConsoleColors.BACKGROUND_BLACK + "Fond noir");
    	        System.out.println(ConsoleColors.BACKGROUND_RED + "Fond rouge");
    	        System.out.println(ConsoleColors.BACKGROUND_GREEN + "Fond vert");
    	        System.out.println(ConsoleColors.BACKGROUND_YELLOW + "Fond jaune");
    	        System.out.println(ConsoleColors.BACKGROUND_BLUE + "Fond bleu");
    	        System.out.println(ConsoleColors.BACKGROUND_MAGENTA + "Fond magenta");
    	        System.out.println(ConsoleColors.BACKGROUND_CYAN + "Fond cyan");
    	        System.out.println(ConsoleColors.BACKGROUND_WHITE + "Fond blanc");
    	 
*/
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	System.out.print("\n"); 
    	System.out.println(ConsoleColors.CYAN+"               /*------------------------------------------------------------------------*/"+ConsoleColors.RESET);
        String texte = "BIBLIOTHEQUE MUNICIPALE DE PARIS ";
        System.out.print("\n");
        System.out.print("                                     ");
        System.out.print(ConsoleColors.BACKGROUND_CYAN);
        for (int i = 0; i < texte.length(); i++) {
            System.out.print(texte.charAt(i));
            Thread.sleep(100); 
        }
        System.out.print(ConsoleColors.RESET);
        System.out.print("\n");
        System.out.print("\n");
        System.out.println(ConsoleColors.CYAN+"               /*-------------------- "+ConsoleColors.RESET+ConsoleColors.BACKGROUND_CYAN + "Un Projet de SAS YouCode Java"+ConsoleColors.RESET+ConsoleColors.CYAN+" ---------------------*/"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN+"               /*-------------------- "+ConsoleColors.RESET+ConsoleColors.BACKGROUND_CYAN +"Réalisé par : EL HAKIK Amina"+ConsoleColors.RESET+ConsoleColors.CYAN+" ----------------------*/"+ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN+"               /*------------------------------------------------------------------------*/"+ConsoleColors.RESET);
        System.out.println(); 
    

    	Livre livre = new Livre ();
    	EmpruntLivre empruntLivre = new EmpruntLivre();
    
         Scanner scanner = new Scanner(System.in); 
         
         Emprunteur emprunteur = new Emprunteur();
     

         
         while (true) {
             
             System.out.print("Entrez le mot de passe : ");
             String inputPassword = scanner.nextLine();

             try {
                 if (Login.verifyPassword(inputPassword)) {
                     System.out.println(ConsoleColors.GREEN +"Mot de passe correct. Accès autorisé."+ ConsoleColors.RESET);
                     break; 
                 } else {
                     System.out.println(ConsoleColors.BACKGROUND_RED  +"Mot de passe incorrect. Veuillez réessayer."+ ConsoleColors.RESET);
                 }
             } catch (IOException e) {
                 System.err.println(ConsoleColors.RED +"Une erreur s'est produite lors de la vérification du mot de passe."+ ConsoleColors.RESET);
                 break; 
             }
         }
         

          String choix;
         afficherMenu(); 
         System.out.println("\n\n                                           Donner votre choix: ");
         choix=scanner.nextLine();
         while(!choix.equals("0"))
         {
        	 Biblio.afficheMessageErreurChoix(choix);
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
	        	 
        	 //default:break;
        	 }
        		 
        	 afficherMenu(); 
             System.out.println("\n\n                                           Donner votre choix: ");
             choix=scanner.nextLine();
         }
         System.out.println("\n\n                    Bye! Fermez Le consol.\n\n");
    }
}
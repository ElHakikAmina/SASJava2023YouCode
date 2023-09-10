package biblio;

import java.util.Scanner;

public class Biblio {

	public static void afficherMenu() {
        System.out.println("\nMenu :");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Afficher la liste des livres disponibles");
        System.out.println("3. Rechercher un livre");
        System.out.println("4. Emprunter un livre");
        System.out.println("5. Retourner un livre");
        System.out.println("6. Afficher la liste des livres empruntés");
        System.out.println("7. Supprimer un livre");
        System.out.println("8. Modifier les informations d'un livre");
        System.out.println("9. Statistique");
        System.out.println("0. Quitter");
        
    }
    public static void main(String[] args) {
    	Livre livre = new Livre ();
    	EmpruntLivre empruntLivre = new EmpruntLivre();
    	
    	//System.out.println(livre.rechercherLivreParISBN("1khf"));
    	 String pwd="1234";
         Scanner scanner = new Scanner(System.in); 
         
         Emprunteur emprunteur = new Emprunteur();
     
         
         System.out.println("Mot e passe: ");
    	 String pwsSaisi = scanner.nextLine();
         while(!pwd.equals(pwsSaisi)) {
        	 System.out.println("Mot e passe: ");
        	 pwsSaisi = scanner.nextLine();
         }
         String choix;
         afficherMenu(); 
         System.out.println("donner votre choix: ");
         choix=scanner.nextLine();
         while(!choix.equals("0"))
         {
        	 switch (choix)
        	 {
        	 //case "0":System.out.println("Bye");break;
        	 case "1": livre.saisi();break;
        	 case "2":Livre.afficherTousLesLivres();break;
        	 case "3":livre.rechercherLivre();break;
        	 case "4":
        		 empruntLivre.empruntterLivre();break;
        	 case "7":livre.supprimerLivre("1");break;
        	 case "8":livre.modifierInfoLivre();break;
        	 /*case "5":;break;
        	 case "6":;break;
        	 case "9":;break;
        	 */
        	 //default:break;
        	 }
        		 
        	 afficherMenu(); 
             System.out.println("donner votre choix: ");
             choix=scanner.nextLine();
         }
    }
}
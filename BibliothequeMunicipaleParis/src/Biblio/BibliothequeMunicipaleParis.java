package Biblio;

import java.util.Scanner;

public class BibliothequeMunicipaleParis {
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
        System.out.print("Choisissez une option : ");
    }
    public static void main(String[] args) {
        Livre.afficherTousLesLivres();
         String pwd="1234";
         Scanner scanner = new Scanner(System.in); 
         
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
         while(choix.equals("0"))
         {
        	 afficherMenu(); 
             System.out.println("donner votre choix: ");
             choix=scanner.nextLine();
         }
    }
}
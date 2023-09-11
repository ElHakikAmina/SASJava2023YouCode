package biblio;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Login {
    private static final String PASSWORD_FILE = "login.txt";

    // Méthode pour vérifier le mot de passe saisi par l'utilisateur
    public static boolean verifyPassword(String inputPassword) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            String storedPassword = reader.readLine();
            return inputPassword.equals(storedPassword);
        }
    }
}

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Connexion {
    public static Connection connection() {
        Connection connexion = null;
        String url = "jdbc:postgresql://localhost:5432/boulangerie";
        String utilisateur = "postgres";
        String motDePasse = "rnmpost5";
        try {
            Class.forName("org.postgresql.Driver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion etablie");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement du driver PostgreSQL : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion a la base de donnee : " + e.getMessage());
        }
        return connexion;
    }
}

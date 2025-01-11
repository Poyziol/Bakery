package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Connexion;

public class Users {
    int idUser;
    String email;
    String motDePasse;
    Employe employe;
    
    public Users() {
    }

    public Users(int idUser, String email, String motDePasse, Employe employe) {
        this.idUser = idUser;
        this.email = email;
        this.motDePasse = motDePasse;
        this.employe = employe;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public static String insert(String email, String motDePasse, int idEmploye) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "insert into Users(email, motDePasse, idUser) values (?, ?, ?)";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, email);
            prepStat.setString(2, motDePasse);
            prepStat.setInt(3, idEmploye);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                message = "Insertion reussi";
            }
            else {
                message = "Insertion echoue";
            }
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(prepStat != null) {
                prepStat.close();
            }
            if(connect != null) {
                connect.close();
            }
        }
        return message;
    }

    public static List<Users> getAll() throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        List<Users> listUsers = new ArrayList<>();
        Users user = null;
        String requete = "select * from Users";
        
        try {
            prepStat = connect.prepareStatement(requete);
            result = prepStat.executeQuery();
            while(result.next()) {
                user = new Users();

                user.setIdUser(result.getInt("idUser"));
                user.setEmail(result.getString("email"));
                user.setMotDePasse(result.getString("motDePasse"));
                user.setEmploye(Employe.getById(result.getInt("idEmploye")));

                listUsers.add(user);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(result != null) {
                result.close();
            }
            if(prepStat != null) {
                prepStat.close();
            }
            if(connect != null) {
                connect.close();
            }
        }

        return listUsers;
    }

    public static Users getById(int idUser) throws Exception {
        Connection connect = Connexion.connection();
        PreparedStatement prepStat = null;
        ResultSet result = null;
        Users user = new Users();
        String requete = "select * from Users where idUser = ?";
        
        try {
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idUser);
            result = prepStat.executeQuery();
            if(result.next()) {          
                user.setIdUser(result.getInt("idUser"));
                user.setEmail(result.getString("email"));
                user.setMotDePasse(result.getString("motDePasse"));
                user.setEmploye(Employe.getById(result.getInt("idEmploye")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(result != null) {
                result.close();
            }
            if(prepStat != null) {
                prepStat.close();
            }
            if(connect != null) {
                connect.close();
            }
        }

        return user;
    }

    public static String update(int idUser, String email, String motDePasse, int idEmploye) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "update Users set email = ?, motDePasse = ?, idEmploye = ? where idUser = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setString(1, email);
            prepStat.setString(2, motDePasse);
            prepStat.setInt(3, idEmploye);
            prepStat.setInt(4, idUser);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                message = "Modification reussi";
            }
            else {
                message = "Modification echoue";
            }
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(prepStat != null) {
                prepStat.close();
            }
            if(connect != null) {
                connect.close();
            }
        }
        return message;
    }

    public static String delete(int idUser) throws Exception {
        Connection connect = null;
        PreparedStatement prepStat = null;
        String requete = "delete from Users where idUser = ?";
        String message = "";
        try {
            connect = Connexion.connection();
            prepStat = connect.prepareStatement(requete);
            prepStat.setInt(1, idUser);
            int result = prepStat.executeUpdate();
            if(result > 0) {
                message = "Suppression reussi";
            }
            else {
                message = "Suppression echoue";
            }
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(prepStat != null) {
                prepStat.close();
            }
            if(connect != null) {
                connect.close();
            }
        }
        return message;
    }

}

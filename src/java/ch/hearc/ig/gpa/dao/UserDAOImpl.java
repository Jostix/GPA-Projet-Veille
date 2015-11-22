/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class UserDAOImpl extends AbstractDAOOracle implements UserDAO {
    
    //Insertion d'un hashtag dans la base de donnée

    @Override
    public Set<User> researchAll() throws ConnectionProblemException {
        Statement stmt = null;
        ResultSet rs = null;

        Set<User> utilisateurs = new HashSet<>();

        try {
            stmt = getConnection().createStatement();
            String query = "SELECT NUMERO, NOM,PRENOM,DATENAISSANCE,PAYS,USERNAME_TWITTER FROM UTILISATEUR";
            rs = stmt.executeQuery(query);

            //On ajoute les utilisateurs à la liste
            while (rs.next()) {
                utilisateurs.add(getUtilisateur(rs));
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading all users", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }

        return utilisateurs;
    }

    @Override
    public Set<User> researchByName(String name) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Set<User> utilisateurs = new HashSet<>();
        User currentUtilisateur = null;

        try {

            String query = "SELECT NUMERO, NOM,PRENOM,DATENAISSANCE,PAYS,USERNAME_TWITTER FROM UTILISATEUR WHERE UPPER(nom) LIKE UPPER(?)";

            stmt = getConnection().prepareStatement(query);
            name = "%" + name + "%";
            stmt.setString(1, name);

            rs = stmt.executeQuery();

            // On ajoute les utilisateurs à la liste
            while (rs.next()) {
                currentUtilisateur = getUtilisateur(rs);
                utilisateurs.add(currentUtilisateur);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while loading users by name", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
        return utilisateurs;
    }

    @Override
    public Set<User> research(User user) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean and = false;

        Set<User> utilisateurs = new HashSet<>();
        User currentUtilisateur = null;

        try {

            StringBuilder query = new StringBuilder("SELECT NUMERO, NOM,PRENOM,DATENAISSANCE,PAYS,USERNAME_TWITTER FROM UTILISATEUR");

            if (!user.isNull()) {
                query.append(" where ");
                if (user.getIdentifiant() != -1) {
                    query.append("numero = '");
                    query.append(user.getIdentifiant());
                    query.append("'");
                    and = true;
                }

                if (user.getNom() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("NOM = '");
                    query.append(user.getNom());
                    query.append("'");
                    and = true;
                }
                if (user.getPrenom() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("PRENOM = '");
                    query.append(user.getPrenom());
                    query.append("'");
                    and = true;
                }

                if (user.getPays() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("PAYS = '");
                    query.append(user.getPays());
                    query.append("'");
                    and = true;
                }
                if (user.getDateNaissance() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("DATENAISSANCE = '");
                    query.append(user.getDateNaissance());
                    query.append("'");
                    and = true;
                }
                if (user.getUsername_twitter() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("USERNAME_TWITTER = '");
                    query.append(user.getUsername_twitter());
                    query.append("'");
                    and = true;
                }
            }

            stmt = getConnection().prepareStatement(query.toString());

            rs = stmt.executeQuery();

            // On ajoute les utilisateurs à la liste
            while (rs.next()) {
                currentUtilisateur = getUtilisateur(rs);
                utilisateurs.add(currentUtilisateur);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while loading users by name", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
        return utilisateurs;
    }

    @Override
    public User insert(String name, String lastname, Date birthdate, String country, String usernameTwitter) throws ConnectionProblemException {
        User utilisateur = new User(null, name, lastname, birthdate, country, usernameTwitter);

        this.insert(utilisateur);

        return utilisateur;
    }

    @Override
    public void insert(User utilisateur) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User newUtilisateur = null;
        final String generatedColumns[] = {"numero"};

        try {
            String query = "INSERT INTO utilisateur (nom,prenom,datenaissance,pays,username_twitter) VALUES (?,?,?,?,?,?)";
            stmt = getConnection().prepareStatement(query, generatedColumns);

            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setDate(3, utilisateur.getDateNaissance());
            stmt.setString(4, utilisateur.getPays());
            stmt.setString(5, utilisateur.getUsername_twitter());

            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("A problem appeared while inserting an user !");
            }

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                utilisateur.setIdentifiant(rs.getInt(1));
            }

        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while inserting an user", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
    }

    @Override
    public void update(User utilisateur) throws ConnectionProblemException {
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE utilisateur SET nom = ?, prenom = ?, datenaissance = ?, pays = ?, username_twitter = ? WHERE numero = ?";
            stmt = getConnection().prepareStatement(query);

            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setDate(3, utilisateur.getDateNaissance());
            stmt.setString(4, utilisateur.getPays());
            stmt.setString(5, utilisateur.getUsername_twitter());
            stmt.setInt(6, utilisateur.getIdentifiant());
            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("Error : update statement returned " + rowCount + " rows instead of 1.");
            }
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while updating an user", sqlE);
        } finally {
            closeStmtAndRS(stmt, null);
        }
    }

    @Override
    public void delete(User utilisateur) throws ConnectionProblemException {
        this.delete(utilisateur.getIdentifiant());
    }

    @Override
    public void delete(Integer utilisateurID) throws ConnectionProblemException {
        PreparedStatement stmt = null;

        try {
            String query = "DELETE FROM utiilisateur WHERE numero = ?";
            stmt = getConnection().prepareStatement(query);

            stmt.setInt(1, utilisateurID);
            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("Error : delete statement returned " + rowCount + " rows instead of 1.");
            }
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while deleting an user", sqlE);
        } finally {
            closeStmtAndRS(stmt, null);
        }
    }

    private User getUtilisateur(ResultSet rs) throws SQLException, ConnectionProblemException {
        User user = new User();
        user.setIdentifiant(rs.getInt("NUMERO"));
        user.setNom(rs.getString("NOM"));
        user.setPrenom(rs.getString("PRENOM"));
        user.setPays(rs.getString("PAYS"));
        user.setUsername_twitter(rs.getString("USERNAME_TWITTER"));

        return user;
    }
}

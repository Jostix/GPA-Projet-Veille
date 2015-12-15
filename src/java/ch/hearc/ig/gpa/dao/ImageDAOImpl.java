/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.dao.interf.ImageDAO;
import ch.hearc.ig.gpa.business.Image;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Blob;
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
public class ImageDAOImpl extends AbstractDAOOracle implements ImageDAO {

    @Override
    public Set<Image> researchAll() throws ConnectionProblemException {
        Statement stmt = null;
        ResultSet rs = null;

        Set<Image> images = new HashSet<>();

        try {
            stmt = getConnection().createStatement();
            String query = "SELECT NUMERO, NOM, TYPE, MSG_NUMERO FROM IMAGE";
            rs = stmt.executeQuery(query);

            //On ajoute les images à la liste
            while (rs.next()) {
                images.add(getImage(rs));
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading all image", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }

        return images;
    }

    @Override
    public Set<Image> researchByName(String nom) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Set<Image> images = new HashSet<>();
        Image currentImage = null;

        try {

            String query = "SELECT NUMERO, NOM, TYPE,IMAGE,MSG_NUMERO WHERE UPPER(nom) LIKE UPPER(?)";

            stmt = getConnection().prepareStatement(query);
            nom = "%" + nom + "%";
            stmt.setString(1, nom);

            rs = stmt.executeQuery();

            // On ajoute les images à la liste
            while (rs.next()) {
                currentImage = getImage(rs);
                images.add(currentImage);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while loading image by name", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
        return images;
    }

    @Override
    public Set<Image> research(Image image) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean and = false;

        Set<Image> images = new HashSet<>();
        Image currentImage = null;

        try {

            StringBuilder query = new StringBuilder("SELECT NUMERO, NOM, TYPE, IMAGE, MSG_NUMERO FROM IMAGE");

            if (!image.isNull()) {
                query.append(" where ");
                if (image.getIdentifiant() != -1) {
                    query.append("NUMERO = '");
                    query.append(image.getIdentifiant());
                    query.append("'");
                    and = true;
                }

                if (image.getNom() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("NOM = '");
                    query.append(image.getNom());
                    query.append("'");
                    and = true;
                }

                if (image.getType() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("TYPE = '");
                    query.append(image.getType());
                    query.append("'");
                    and = true;
                }
                if (image.getImage() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("IMAGE = '");
                    query.append(image.getImage());
                    query.append("'");
                    and = true;
                }
                if (image.getMessage() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("MSG_NUMERO = '");
                    query.append(image.getMessage().getIdentifiant());
                    query.append("'");
                    and = true;
                }
            }

            stmt = getConnection().prepareStatement(query.toString());

            rs = stmt.executeQuery();

            // On ajoute les images à la liste
            while (rs.next()) {
                currentImage = getImage(rs);
                images.add(currentImage);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while loading image by name", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
        return images;
    }

    @Override
    public Image insert(String nom, String type, Blob image, Message message) throws ConnectionProblemException {
        Image img = new Image(null, nom, type, image, message);

        this.insert(img);

        return img;
    }

    @Override
    public void insert(Image image) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String generatedColumns[] = {"numero"};

        try {
            String query = "INSERT INTO image (nom,type,image,msg_numero) VALUES (?,?,?,?)";
            stmt = getConnection().prepareStatement(query, generatedColumns);

            stmt.setString(1, image.getNom());
            stmt.setString(2, image.getType());
            stmt.setBlob(3, image.getImage());
            stmt.setInt(4, image.getMessage().getIdentifiant());

            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("A problem appeared while inserting an image !");
            }

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                image.setIdentifiant(rs.getInt(1));
            }

        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while inserting an image", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
    }

    @Override
    public void update(Image image) throws ConnectionProblemException {
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE image SET nom = ?, type = ?, image = ?, msg_numero = ? WHERE numero = ?";
            stmt = getConnection().prepareStatement(query);

            stmt.setString(1, image.getNom());
            stmt.setString(2, image.getType());
            stmt.setBlob(3, image.getImage());
            stmt.setInt(4, image.getMessage().getIdentifiant());
            stmt.setInt(5, image.getIdentifiant());
            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("Error : update statement returned " + rowCount + " rows instead of 1.");
            }
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while updating an image", sqlE);
        } finally {
            closeStmtAndRS(stmt, null);
        }
    }

    @Override
    public void delete(Image image) throws ConnectionProblemException {
        this.delete(image.getIdentifiant());
    }

    @Override
    public void delete(Integer imageID) throws ConnectionProblemException {
        PreparedStatement stmt = null;

        try {
            String query = "DELETE FROM image WHERE numero = ?";
            stmt = getConnection().prepareStatement(query);

            stmt.setInt(1, imageID);
            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("Error : delete statement returned " + rowCount + " rows instead of 1.");
            }
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while deleting an image", sqlE);
        } finally {
            closeStmtAndRS(stmt, null);
        }
    }

    private Image getImage(ResultSet rs) throws SQLException, ConnectionProblemException {
        Image image = new Image();
        image.setIdentifiant(rs.getInt("NUMERO"));
        image.setNom(rs.getString("NOM"));
        image.setType(rs.getString("TYPE"));
        image.setImage(rs.getBlob("IMAGE"));

        //image.setMessage(MessageDAO.getMessageById(rs.getInt("MSG_NUMERO")));
        return image;
    }
}

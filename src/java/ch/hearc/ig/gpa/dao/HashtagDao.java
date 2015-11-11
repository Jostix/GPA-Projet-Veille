/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class HashtagDao {

    //Insertion d'un hashtag dans la base de donnée
    public static long create(Hashtag hash) {
        Connection c = null;
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;

        long rId = -1;

        try {
            c = OracleConnections.getConnection();

            StringBuilder sql = new StringBuilder("insert into hashtag(libelle,msg_numero) values (?,?) returning id into ?");
            pstmt = (OraclePreparedStatement) c.prepareStatement(sql.toString());

            pstmt.setString(1, hash.getLibelle());
            pstmt.setInt(2, hash.getMsg_numero());
            pstmt.registerReturnParameter(3, OracleTypes.NUMBER);
            pstmt.executeUpdate();
            c.commit();

            rs = pstmt.getReturnResultSet();
            while (rs.next()) {
                rId = rs.getLong(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error INSERT: " + ex.getMessage());
        } finally {
            try {
                pstmt.close();
                c.close();
            } catch (SQLException ex) {
                System.out.println("Error INSERT CLOSE: " + ex.getMessage());
            }
        }
        System.out.println(rId);
        return rId;
    }

    //RechercheAll
    public static ArrayList<Hashtag> research(Hashtag hash) {
        ArrayList<Hashtag> listHash = new ArrayList<>();

        boolean and = false;

        Connection cnx = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            cnx = OracleConnections.getConnection();

            //String sql = "select id, nom, solde, taux from compte where id_client=" + String.valueOf(client_id);
            StringBuilder sql = new StringBuilder("select numero, libelle, msg_numero from hashtag");

            if (!hash.isNull()) {
                sql.append(" where ");
                if (hash.getIdentifiant()!= -1) {
                    sql.append("numero = '");
                    sql.append(hash.getIdentifiant());
                    sql.append("'");
                    and = true;
                }
                if (hash.getMsg_numero() != -1) {
                    if (and) {
                        sql.append(" and ");
                    }
                    sql.append("msg_numero = '");
                    sql.append(hash.getMsg_numero());
                    sql.append("'");
                    and = true;
                }
                if (hash.getLibelle() != null) {
                    if (and) {
                        sql.append(" and ");
                    }
                    sql.append("libelle = '");
                    sql.append(hash.getLibelle());
                    sql.append("'");
                    and = true;
                }
            }

            stmt = cnx.createStatement();

            rs = stmt.executeQuery(sql.toString());

            while (rs.next()) {
                Hashtag h = new Hashtag();
                h.setMsg_numero(rs.getInt("msg_numero"));
                h.setLibelle(rs.getString("libelle"));
                listHash.add(h);
            }
            return listHash;
        } catch (SQLException ex) {
            System.out.println("Error SELECT CONNECTION: " + ex.getMessage());
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error SELECT SQL: " + ex.getMessage());
            }

        }
    }

    public static void delete(Hashtag hash) {
        Connection cnx = null;
        PreparedStatement pstmt = null;

        try {
            cnx = OracleConnections.getConnection();

            StringBuilder sql = new StringBuilder("DELETE FROM hashtag WHERE numero = ?");
            pstmt = (OraclePreparedStatement) cnx.prepareStatement(sql.toString());

            pstmt.setLong(1, hash.getIdentifiant());
            pstmt.executeUpdate();
            cnx.commit();
        } catch (SQLException ex) {
            System.out.println("Error DELETE SQL: " + ex.getMessage());
        } finally {
            try {
                pstmt.close();
                cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error DELETE CLOSE: " + ex.getMessage());
            }
        }
    }
}

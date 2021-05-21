/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author monia
 */
public class ServiceDemande {
    Connection cnx;

    public ServiceDemande() {
        
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void AjouterDemande(Demande d) {
                try {
                        Statement stm = cnx.createStatement();
                        String query = "INSERT INTO demande( nom, prenom, numero, commentaire,related_offre_id, cv) VALUES ('" + d.getNom() + "','" + d.getPrenom() + "'," + d.getNum() + ",'" + d.getComment()+ "',"+d.getIDO()+",'" + d.getCV()+ "')";
                        stm.executeUpdate(query);
                        

                } catch (SQLException ex) {
                        System.out.println("le");
                }
        }
    
    public ObservableList<Demande> AfficherDemande(int id) throws SQLException {
                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM demande WHERE related_offre_id =" + id + "";
                ResultSet rst = stm.executeQuery(query);
                ObservableList<Demande> data = FXCollections.observableArrayList();
                while (rst.next()) {
                        Demande d = new Demande();
                        d.setNom(rst.getString("nom"));
                         d.setPrenom(rst.getString("prenom"));
                          d.setNum(rst.getInt("numero"));
                           d.setId(rst.getInt("id"));
                            d.setComment(rst.getString("commentaire"));
                             d.setCV(rst.getString("cv"));
                              
                        data.add(d);

                }
                return data;
        }

    

    
    
    
    
}

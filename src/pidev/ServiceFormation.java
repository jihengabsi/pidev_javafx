/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jihen
 */
public class ServiceFormation {
     Connection cnx;

        public ServiceFormation() {
                cnx = MyConnection.getInstance().getConnection();
        }
         public void AjouterOffre(Formation f) {
                try {
                        Statement stm = cnx.createStatement();
                        String query =  "insert into formation (titre,date,lieu,description,prix,date_fin) values ('" + f.getTitre() + "','" + f.getLieu()+ "','" + f.getDate()+ "','" + f.getDescription()+ "','" + f.getPrix()+ "','" + f.getDate_fin()+ "')";
                        stm.executeUpdate(query);

                } catch (SQLException ex) {
                        System.out.println("le");
                }
        }
        
         public ObservableList<Formation> AfficherFormation() throws SQLException {
                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM formation ";
                ResultSet rst = stm.executeQuery(query);
                ObservableList<Formation> data = FXCollections.observableArrayList();
                while (rst.next()) {
                        Formation f = new Formation();
                        f.setTitre(rst.getString("titre"));
                        f.setDate(rst.getDate("date"));
                        f.setLieu(rst.getString("lieu"));
                        f.setDate_fin(rst.getDate("date_fin"));
                        f.setPrix(rst.getString("prix"));
                        f.setId(rst.getInt("id"));
                        f.setDescription(rst.getString("description"));
                        data.add(f);

                }
                return data;
        }
          public void supprimerFormation(int id) {
                try {
                        Statement stm = cnx.createStatement();
                        String query;
                        query="DELETE FROM formation WHERE id=" + id + "";
                        stm.executeUpdate(query);
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
        }

        public void modifierFormation(int id, Formation f) {
                try {
                        Statement stm = cnx.createStatement();
                        String query;
                        query = "update formation set titre = '" + f.getTitre() + "', lieu = '" + f.getLieu() + "', date = '" + f.getDate()+ "', prix  = '" + f.getPrix() + "',description = '" + f.getDescription() + "'where id = " + id + "";
                        stm.executeUpdate(query);
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
        }

   
    
}

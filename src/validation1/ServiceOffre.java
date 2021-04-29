/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import validation1.Offre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import validation1.MyConnection;

/**
 *
 * @author monia
 */
public class ServiceOffre {
     Connection cnx;

        public ServiceOffre() {
                cnx = MyConnection.getInstance().getConnection();
        }
         public void AjouterOffre(Offre o) {
                try {
                        Statement stm = cnx.createStatement();
                        String query = "INSERT INTO offre( title, ville, domain, company_name, salaire, type, description) VALUES ('" + o.getTitle() + "','" + o.getVille() + "','" + o.getDomain() + "','" +o.getCompanyName()+ "'," + o.getSalary() + ",'" + o.getType() + "','" + o.getDescription() + "')";
                        stm.executeUpdate(query);
                        

                } catch (SQLException ex) {
                        System.out.println("le");
                }
        }
        
         public ObservableList<Offre> AfficherOffre() throws SQLException {
                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM offre ";
                ResultSet rst = stm.executeQuery(query);
                ObservableList<Offre> data = FXCollections.observableArrayList();
                while (rst.next()) {
                        Offre o = new Offre();
                        o.setTitle(rst.getString("title"));
                         o.setDomain(rst.getString("domain"));
                          o.setVille(rst.getString("ville"));
                           o.setSalary(rst.getInt("salaire"));
                           o.setId(rst.getInt("id"));
                            o.setCompanyName(rst.getString("company_name"));
                             o.setDescription(rst.getString("description"));
                              o.setType(rst.getString("type"));
                        data.add(o);

                }
                return data;
        }
          public void supprimerOffre(int id) {
                try {
                        Statement stm = cnx.createStatement();
                        String query;
                        query="DELETE FROM offre WHERE id=" + id + "";
                        stm.executeUpdate(query);
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
        }

        public void modifierOffre(int id, Offre o) {
                try {
                        Statement stm = cnx.createStatement();
                        String query;
                        query = "update offre set title = '" + o.getTitle() + "', ville = '" + o.getVille() + "', domain = '" + o.getDomain()+ "', company_name  = '" + o.getCompanyName() + "', salaire = '" + o.getSalary() + "',type = '" + o.getType() + "',description = '" + o.getDescription() + "'where id = " + id + "";
                        stm.executeUpdate(query);
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
        }
        public ObservableList<Offre> AfficherOffreSal() throws SQLException {
                Statement stm = cnx.createStatement();
                String query = "SELECT * FROM offre ORDER BY salaire DESC LIMIT 5 ";
                ResultSet rst = stm.executeQuery(query);
                ObservableList<Offre> data = FXCollections.observableArrayList();
                while (rst.next()) {
                        Offre o = new Offre();
                        o.setTitle(rst.getString("title"));
                         o.setDomain(rst.getString("domain"));
                          o.setVille(rst.getString("ville"));
                           o.setSalary(rst.getInt("salaire"));
                           o.setId(rst.getInt("id"));
                            o.setCompanyName(rst.getString("company_name"));
                             o.setDescription(rst.getString("description"));
                              o.setType(rst.getString("type"));
                        data.add(o);

                }
                return data;
        }
    
}

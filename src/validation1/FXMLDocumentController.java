/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author monia
 */
public class FXMLDocumentController implements Initializable {

    int selectedId = 0;
    @FXML
    private TableView<Offre> tabv;
    @FXML
    private TableColumn<Offre, String> title;
    @FXML
    private TableColumn<Offre, String> dom;
    @FXML
    private TableColumn<Offre, String> ville;
    @FXML
    private TableColumn<Offre, Integer> salary;
    @FXML
    private TableColumn<Offre, String> cmpName;
    @FXML
    private TableColumn<Offre, String> desc;
    @FXML
    private TableColumn<Offre, String> type;
    @FXML
    private JFXTextField domaintf;
    @FXML
    private JFXTextField titletf;
    @FXML
    private JFXTextField villetf;

    @FXML
    private JFXTextField cmpNametf;
    @FXML
    private JFXTextField desctf;
    @FXML
    private JFXTextField typetf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceOffre so = new ServiceOffre();

            title.setCellValueFactory(new PropertyValueFactory<Offre, String>("title"));
            dom.setCellValueFactory(new PropertyValueFactory<Offre, String>("domain"));
            ville.setCellValueFactory(new PropertyValueFactory<Offre, String>("ville"));
            salary.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("salary"));
            cmpName.setCellValueFactory(new PropertyValueFactory<Offre, String>("CompanyName"));
            desc.setCellValueFactory(new PropertyValueFactory<Offre, String>("Description"));
            type.setCellValueFactory(new PropertyValueFactory<Offre, String>("type"));

            tabv.setItems(so.AfficherOffre());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tabv.getSelectionModel().getSelectedItem() != null) {
                    Offre selectedOffre = tabv.getSelectionModel().getSelectedItem();
                    domaintf.setText(selectedOffre.getDomain());
                    titletf.setText(selectedOffre.getTitle());
                    villetf.setText(selectedOffre.getVille());
                    cmpNametf.setText(selectedOffre.getCompanyName());
                    desctf.setText(selectedOffre.getDescription());
                    typetf.setText(selectedOffre.getType());
                    selectedId = selectedOffre.getId();

                }
            }
        });

    }

    @FXML
    private void deleteqct(MouseEvent event) throws SQLException {
         ServiceOffre so = new ServiceOffre();
                System.out.println(selectedId);
                
                        so.supprimerOffre(selectedId);
                      
                                tabv.setItems(so.AfficherOffre());
    }

    @FXML
    private void update(MouseEvent event) throws SQLException {
        
                        Offre o = new Offre();
                       ServiceOffre so = new ServiceOffre();
                       o.setDomain(domaintf.getText());
                       o.setTitle( titletf.getText());
                       o.setVille(villetf.getText());
                       o.setCompanyName(cmpNametf.getText());
                       o.setDescription(desctf.getText());
                       o.setType(typetf.getText());
                       
                        so.modifierOffre(selectedId, o);
                       
                        
                                tabv.setItems(so.AfficherOffre());
                 
    }

}

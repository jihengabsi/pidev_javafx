/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static validation1.Constants.projectPath;

/**
 * FXML Controller class
 *
 * @author monia
 */
public class BackDemandeController implements Initializable {
    


    @FXML
    private TableColumn<Demande, String> nom;

    @FXML
    private TableColumn<Demande, String> prenom;

 
    @FXML
    private TableView<Demande> tv;
    @FXML
    private TableColumn<Demande, Integer> num;
    @FXML
    private TableColumn<Demande, String> cmt;
    
    
    Desktop desktop = Desktop.getDesktop();
    
    String nomf = null;
    
    File file = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
    }

    public void AfficheSelectedDemande(int id) throws SQLException {
        System.out.println(id);
         try {
            ServiceDemande Do = new ServiceDemande();
            System.out.println(id);
            
            nom.setCellValueFactory(new PropertyValueFactory<Demande,String>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<Demande, String>("prenom"));
            num.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("num"));
            cmt.setCellValueFactory(new PropertyValueFactory<Demande, String>("comment"));
            
             
            tv.setItems(Do.AfficherDemande(id));
        } catch (SQLException ex) {
            Logger.getLogger(BackDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
          tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (tv.getSelectionModel().getSelectedItem() != null) {
                    Demande selectedDemande = tv.getSelectionModel().getSelectedItem();
                    nom.setText(selectedDemande.getNom());
                    prenom.setText(selectedDemande.getPrenom());
                    cmt.setText(selectedDemande.getComment());
                    

                    num.setText(String.valueOf(selectedDemande.getNum()));
                    
                   nomf = selectedDemande.getCV();
                }
            }
        });
    }    
    
    
    private void openFile(File file) {
        try {
            desktop.open(file);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void OpenPDF(ActionEvent event) {
        
        
        String url = projectPath + "\\src\\validation1.CV\\" + nomf;
        System.out.println("------------------" + url);
        file = new File(url);
        openFile(file);

        
    }
    
    
}





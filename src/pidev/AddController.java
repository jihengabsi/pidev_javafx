/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jihen
 */
public class AddController implements Initializable {
 @FXML
    private JFXTextField titref;
    @FXML
    private JFXDatePicker datef;
    @FXML
    private JFXTextField lieuf;

    @FXML
    private JFXTextField descriptionf;
    @FXML
    private JFXDatePicker date_finf;
   @FXML
    private JFXTextField prixf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add(ActionEvent event) {
        ServiceFormation sf = new ServiceFormation();
        Formation f = new Formation();
         titref.setText(f.getTitre());
                    lieuf.setText(f.getLieu());
                    prixf.setText(f.getPrix());
                    datef.setValue(LocalDate.parse(f.getDate().toString()));
                    descriptionf.setText(f.getDescription());
                    date_finf.setValue(LocalDate.parse(f.getDate_fin().toString()));
                   
        sf.AjouterOffre(f);

    }

}

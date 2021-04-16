/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TextField;
/**
 *
 * @author jihen
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Label label;
    @FXML private TextField filterField;
    int selectedId = 0;
    @FXML
    private TableView<Formation> tabv;
    @FXML
    private TableColumn<Formation, String> titre;
    @FXML
    private TableColumn<Formation, Date> date;
    @FXML
    private TableColumn<Formation, String> lieu;
    @FXML
    private TableColumn<Formation, Integer> prix;
    @FXML
    private TableColumn<Formation, String> description;
    @FXML
    private TableColumn<Formation, Date> date_fin;
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
private final ObservableList<Formation> dataList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ServiceFormation sf = new ServiceFormation();
            
            titre.setCellValueFactory(new PropertyValueFactory<Formation, String>("titre"));
            
            date.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date"));
            lieu.setCellValueFactory(new PropertyValueFactory<Formation, String>("lieu"));
            prix.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("prix"));
            description.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
            date_fin.setCellValueFactory(new PropertyValueFactory<Formation, Date>("date_fin"));

            tabv.setItems(sf.AfficherFormation());
            FilteredList<Formation> filteredData = new FilteredList<>(dataList, b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(formation -> {
            	if (newValue == null || newValue.isEmpty()) {
					return true;
				}
                    String lowerCaseFilter = newValue.toLowerCase();
				
				if (formation.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (formation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(formation.getPrix()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Formation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabv.setItems(sortedData);
               
        
                    
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   

    }

    @FXML
    private void deleteqct(MouseEvent event) throws SQLException {
         ServiceFormation sF = new ServiceFormation();
                System.out.println(selectedId);
                
                        sF.supprimerFormation(selectedId);
                      
                                tabv.setItems(sF.AfficherFormation());
    }

    @FXML
    private void update(MouseEvent event) throws SQLException {
        
                       Formation f = new Formation();
                       ServiceFormation sf = new ServiceFormation();
                       f.setTitre(titref.getText());
                       f.setLieu(lieuf.getText());
                       java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datef.getValue());
                       f.setDate(gettedDatePickerDate);
                       f.setPrix(prixf.getText());
                       java.sql.Date gettedDatePickerDate1 = java.sql.Date.valueOf(date_finf.getValue());
                       f.setDate_fin(gettedDatePickerDate1);
                       f.setDescription(description.getText());
                       sf.modifierFormation(selectedId, f);
                       
                        
                                tabv.setItems(sf.AfficherFormation());
                 
    }

}

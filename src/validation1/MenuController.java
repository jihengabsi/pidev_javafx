/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import validation1.AddController;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author monia
 */
public class MenuController implements Initializable {
  double xOffset ,yOffset ;
            
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
    private JFXTextField saltf;

    @FXML
    private JFXTextField cmpNametf;
    @FXML
    private JFXTextField desctf;
    @FXML
    private JFXTextField typetf;
    @FXML
    private TextField filterField;
    @FXML
    private PieChart salalrystat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stat();
        try {
            ServiceOffre so = new ServiceOffre();

            title.setCellValueFactory(new PropertyValueFactory<Offre, String>("title"));
            dom.setCellValueFactory(new PropertyValueFactory<Offre, String>("domain"));
            ville.setCellValueFactory(new PropertyValueFactory<Offre, String>("ville"));
            salary.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("salary"));
            cmpName.setCellValueFactory(new PropertyValueFactory<Offre, String>("CompanyName"));
            desc.setCellValueFactory(new PropertyValueFactory<Offre, String>("Description"));
            type.setCellValueFactory(new PropertyValueFactory<Offre, String>("type"));

            FilteredList<Offre> filteredData = new FilteredList<>(so.AfficherOffre(), b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Offre -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Offre.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (Offre.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (String.valueOf(Offre.getSalary()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Offre> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tabv.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tabv.setItems(sortedData);

            //tabv.setItems(so.AfficherOffre());
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

                    saltf.setText(String.valueOf(selectedOffre.getSalary()));
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

        FilteredList<Offre> filteredData = new FilteredList<>(so.AfficherOffre(), b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Offre -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Offre.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Offre.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(Offre.getSalary()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Offre> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tabv.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabv.setItems(sortedData);

        //tabv.setItems(so.AfficherOffre());
    }

    @FXML
    private void update(MouseEvent event) throws SQLException {

        Offre o = new Offre();
        ServiceOffre so = new ServiceOffre();
        o.setSalary(Integer.valueOf(saltf.getText()));
        o.setDomain(domaintf.getText());
        o.setTitle(titletf.getText());
        o.setVille(villetf.getText());
        o.setCompanyName(cmpNametf.getText());
        o.setDescription(desctf.getText());
        o.setType(typetf.getText());

        so.modifierOffre(selectedId, o);

        FilteredList<Offre> filteredData = new FilteredList<>(so.AfficherOffre(), b -> true);

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Offre -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Offre.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (Offre.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(Offre.getSalary()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Offre> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tabv.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabv.setItems(sortedData);
        //tabv.setItems(so.AfficherOffre());

    }

    @FXML
    private void movetoaddoffre(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root = loader.load();
        AddController s2 = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void refresh(MouseEvent event) throws IOException {
        try {
            ServiceOffre so = new ServiceOffre();

            title.setCellValueFactory(new PropertyValueFactory<Offre, String>("title"));
            dom.setCellValueFactory(new PropertyValueFactory<Offre, String>("domain"));
            ville.setCellValueFactory(new PropertyValueFactory<Offre, String>("ville"));
            salary.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("salary"));
            cmpName.setCellValueFactory(new PropertyValueFactory<Offre, String>("CompanyName"));
            desc.setCellValueFactory(new PropertyValueFactory<Offre, String>("Description"));
            type.setCellValueFactory(new PropertyValueFactory<Offre, String>("type"));

            FilteredList<Offre> filteredData = new FilteredList<>(so.AfficherOffre(), b -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(Offre -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Offre.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else if (Offre.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    } else if (String.valueOf(Offre.getSalary()).indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Offre> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tabv.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tabv.setItems(sortedData);

            //tabv.setItems(so.AfficherOffre());
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

                    saltf.setText(String.valueOf(selectedOffre.getSalary()));
                    selectedId = selectedOffre.getId();

                }
            }
        });

    }

    public void stat() {
        ServiceOffre so = new ServiceOffre();
        String n1 = null;
        int v1 = 0;
        String n2 = null;
        int v2 = 0;
        String n3 = null;
        int v3 = 0;
        String n4 = null;
        int v4 = 0;
        String n5 = null;
        int v5 = 0;
        for (int i = 0; i < 4; i++) {
            try {
                n1 = (so.AfficherOffreSal().get(0).getTitle());
                v1 = (so.AfficherOffreSal().get(0).getSalary());
                n2 = (so.AfficherOffreSal().get(1).getTitle());
                v2 = (so.AfficherOffreSal().get(1).getSalary());
                n3 = (so.AfficherOffreSal().get(2).getTitle());
                v3 = (so.AfficherOffreSal().get(2).getSalary());
                n4 = (so.AfficherOffreSal().get(3).getTitle());
                v4 = (so.AfficherOffreSal().get(3).getSalary());
                n5 = (so.AfficherOffreSal().get(4).getTitle());
                v5 = (so.AfficherOffreSal().get(4).getSalary());

            } catch (SQLException ex) {
                System.out.println("rech controller errr");
            }
        }
        ObservableList<PieChart.Data> PieChartData;
        PieChartData = FXCollections.observableArrayList(
                new PieChart.Data(n1, v1),
                new PieChart.Data(n2, v2),
                new PieChart.Data(n3, v3),
                new PieChart.Data(n4, v4),
                new PieChart.Data(n5, v5));
        salalrystat.setData(PieChartData);
    }

    @FXML
    private void movetoDemande(MouseEvent event) throws SQLException, IOException {
       
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BackDemande.fxml"));
        Parent root = loader.load();
 BackDemandeController bdc = loader.getController();
        bdc.AfficheSelectedDemande(selectedId);
       
        BackDemandeController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();

    }

    /*@FXML
        private void AfficherSelectedDemande
        (ActionEvent event) throws IOException {
             try {
            ServiceDemande DO = new ServiceDemande();

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
        }*/
}

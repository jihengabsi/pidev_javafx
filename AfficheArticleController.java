/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import animatefx.animation.FadeIn;
import entities.Articles;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import services.ArticleServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author arijs
 */
public class AfficheArticleController implements Initializable {

    @FXML
    private Pane context;
    @FXML
    private TableView<Articles> TableViewArticles;
    @FXML
    private TableColumn<Articles, String> titre;
    @FXML
    private TableColumn<Articles, String> resume;
    @FXML
    private TableColumn<Articles, String> contenu;
    @FXML
    private TableColumn<Articles, Date> creele;
    @FXML
    private TableColumn<Articles, Date> majle;
    @FXML
    private TableColumn<Articles, String> image;
    @FXML
    private TextField searchId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReadData();
    }

    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/GUI/" + location + ".fxml")));
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        setUi("AjouterArticle");
        new FadeIn(context).play();
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        Articles a = TableViewArticles.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No Article Selected !");
            alert.showAndWait();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/ModifierArticle.fxml"));
        loader.load();
        ModifierArticleController MEC = loader.getController();
        System.out.println("TRUEEE");
        MEC.setTextField(a.getId(), a.getTitre(), a.getResume(), a.getContenu(), a.getImage(), a.getPupliele());
        System.out.println(a.toString());
        System.out.println("TRUEEENNNN");
        Parent root = loader.getRoot();
        context.getChildren().clear();
        context.getChildren().add(root);
    }

    @FXML
    private void delete(ActionEvent event) {
        Articles a = TableViewArticles.getSelectionModel().getSelectedItem();
        if (a == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("No Article Selected !");
            alert.showAndWait();
        }
        ArticleServices as = new ArticleServices();
        as.delById(a.getId());
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("SUCCES");
        tray.setMessage("Article bien supprimé");
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
        ObservableList<Articles> AList = FXCollections.observableArrayList();
        AList = as.readAll();
        TableViewArticles.setItems(AList);
    }

    @FXML
    private void search(ActionEvent event) {
    }

    public void ReadData() {
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        resume.setCellValueFactory(new PropertyValueFactory<>("resume"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        creele.setCellValueFactory(new PropertyValueFactory<>("pupliele"));
        majle.setCellValueFactory(new PropertyValueFactory<>("majle"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        ObservableList<Articles> ArticleList = FXCollections.observableArrayList();
        ArticleServices as = new ArticleServices();
        ArticleList = as.readAll();
        TableViewArticles.setItems(ArticleList);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import animatefx.animation.FadeIn;
import entities.Articles;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import services.ArticleServices;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author arijs
 */
public class AjouterArticleController implements Initializable {

    @FXML
    private Pane context;
    @FXML
    private TextField idtitre;
    @FXML
    private TextArea idresume;
    @FXML
    private Button btnaj;
    @FXML
    private Button btnretour;
    @FXML
    private TextArea idcontenu;
    @FXML
    private ImageView imageArticle;
    String ImageName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/GUI/" + location + ".fxml")));
    }

    @FXML
    private void ImageSelection(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        ImageName = file.getAbsolutePath();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageArticle.setImage(image);
        } catch (IOException ignored) {

        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        String titre = idtitre.getText();
        String res = idresume.getText();
        String cont = idcontenu.getText();
        if (titre.isEmpty() || res.isEmpty() || cont.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Champs est vide");
            alert.showAndWait();
        } else {
            ArticleServices as = new ArticleServices();
            long millis = System.currentTimeMillis();
            Date df = new Date(millis);
            Date d = new Date(millis);
            Articles a = new Articles(titre, res, cont, d, df, ImageName);
            as.create(a);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("SUCCES");
            tray.setMessage("article bien ajouté");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            setUi("AfficheArticle");
            new FadeIn(context).play();
        }
    }

    @FXML
    private void btnRetourAction(ActionEvent event) throws IOException {
        setUi("AfficheArticle");
        new FadeIn(context).play();

    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        setUi("AfficheArticle");
        new FadeIn(context).play();

    }

}

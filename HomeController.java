/*
 * Copyright (C) 2021 arijs
 * This project was developped by Arij Saleh. Enjoy!
 */
package Controllers;

import animatefx.animation.FadeIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author arijs
 */
public class HomeController implements Initializable {
    
    @FXML
    private Pane context;
    
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
    private void OffreFront(ActionEvent event) throws IOException {
         setUi("OffreFront");
        new FadeIn(context).play();
    }

    @FXML
    private void StageFront(ActionEvent event) throws IOException {
        setUi("CandidatureStage");
        new FadeIn(context).play();
    }

    @FXML
    private void FormationFront(ActionEvent event) {
    }

    @FXML
    private void CCFront(ActionEvent event) throws IOException {
        setUi("ConseilCarriereFront");
        new FadeIn(context).play();
    }

    @FXML
    private void ForumFront(ActionEvent event) throws IOException {
        setUi("AfficherForum");
        new FadeIn(context).play();
    }

    @FXML
    private void BloFront(ActionEvent event) throws IOException {
        setUi("AfficherBlog");
        new FadeIn(context).play();
    }

    @FXML
    private void AboutUs(ActionEvent event) {
    }
}

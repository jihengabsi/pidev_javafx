/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author monia
 */
public class OffreItemController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label domain;
    @FXML
    private Label salary;
    @FXML
    private Label ville;
    int id = 0;
    @FXML
    private ImageView qrCodeView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Offre o) {

        title.setText(o.getTitle());
        domain.setText(o.getDomain());
        ville.setText(o.getVille());
        salary.setText(String.valueOf(o.getSalary()));
        id = o.getId();
       
    }

    @FXML
    private void addDemane(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDemande.fxml"));
        Parent root = loader.load();
        AddDemandeController adc = loader.getController();
        adc.getID(id);

        AddDemandeController s2 = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void QRcode(ActionEvent event) {
        
        GenerateQRcode("hello");      
    }
    
    private void GenerateQRcode(String message)
    {
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //String myWeb = "http://java-buddy.blogspot.com/";
        int width = 300;
        int height = 300;
        String fileType = "png";
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(message, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(java.awt.Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(java.awt.Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            System.out.println("Success...");
    } catch (Exception ex) {
            Logger.getLogger(OffreItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        qrCodeView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
    }
}

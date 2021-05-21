/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author monia
 */
public class AddController implements Initializable {

    @FXML
    private JFXTextField domaintf;
    @FXML
    private JFXTextField typetf;
    @FXML
    private JFXTextField titletf;
    @FXML
    private JFXTextField villetf;
    @FXML
    private JFXTextField cmpNametf;
    @FXML
    private JFXTextField desctf;
    @FXML
    private JFXTextField saltf;
    
    public boolean ValidTitle = false;
    public boolean ValidVille = false;
    public boolean ValidDomain = false;
    public boolean ValidCompany = false;
    public boolean ValidType = false;
    public boolean ValidDesc = false;
    public boolean ValidSalary = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TitleValidator();
        VilleValidator();
        DomainValidator();
        CompanyValidator();
        TypeValidator();
        DescValidator();
        SalaryValidator();
    }

    @FXML
    private void add(ActionEvent event) {
        
        ServiceOffre so = new ServiceOffre();
        Offre o = new Offre();
        o.setSalary(Integer.valueOf(saltf.getText()));
        o.setDomain(domaintf.getText());
        o.setTitle(titletf.getText());
        o.setVille(villetf.getText());
        o.setCompanyName(cmpNametf.getText());
        o.setDescription(desctf.getText());
        o.setType(typetf.getText());
        

        
           so.AjouterOffre(o); 
        
        
        
        

    }
    
    
    public void TitleValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$");
        titletf.setValidators(valid);
        valid.setMessage("Please enter a valid title");
        titletf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    titletf.validate();
                    if (titletf.validate()) {
                        ValidTitle = true;
                    } else {
                        ValidTitle = false;
                    }
                }
            }
        

            
        
            });
}

    
    public void VilleValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$");
        villetf.setValidators(valid);
        valid.setMessage("Please enter a valid Ville");
        villetf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    villetf.validate();
                    if (villetf.validate()) {
                        ValidVille = true;
                    } else {
                        ValidVille = false;
                    }
                }
            }
        

            
        
            });
}
    
    
    public void DomainValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$");
        domaintf.setValidators(valid);
        valid.setMessage("Please enter a valid Domain");
        domaintf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    domaintf.validate();
                    if (domaintf.validate()) {
                        ValidDomain = true;
                    } else {
                        ValidDomain = false;
                    }
                }
            }
        

            
        
            });
}
    
    public void CompanyValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$");
        cmpNametf.setValidators(valid);
        valid.setMessage("Please enter a valid Ville");
        cmpNametf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    cmpNametf.validate();
                    if (cmpNametf.validate()) {
                        ValidDesc = true;
                    } else {
                        ValidDesc = false;
                    }
                }
            }
        

            
        
            });
}
    
    public void TypeValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$");
        typetf.setValidators(valid);
        valid.setMessage("Please enter a valid Type");
        typetf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    typetf.validate();
                    if (typetf.validate()) {
                        ValidType = true;
                    } else {
                        ValidType = false;
                    }
                }
            }
        

            
        
            });
}
    
    public void DescValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z])$");
        desctf.setValidators(valid);
        valid.setMessage("Please enter a valid Description");
        desctf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    desctf.validate();
                    if (desctf.validate()) {
                        ValidType = true;
                    } else {
                        ValidType = false;
                    }
                }
            }
        

            
        
            });
}
    
    public void SalaryValidator() {
        RegexValidator valid = new RegexValidator();
        valid.setRegexPattern("^[0-9]+$");
        saltf.setValidators(valid);
        valid.setMessage("Please enter a valid Salary");
        saltf.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                System.out.println(oldPropertyValue + " " + newPropertyValue);
                if (!newPropertyValue) {
                    saltf.validate();
                    if (saltf.validate()) {
                        ValidSalary = true;
                    } else {
                        ValidSalary = false;
                    }
                }
            }
        

            
        
            });
}
}

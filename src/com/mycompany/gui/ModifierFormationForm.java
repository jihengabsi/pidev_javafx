/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formation;
import com.mycompany.services.ServiceFormation;

/**
 *
 * @author DELL
 */
public class ModifierFormationForm extends BaseForm{
    
           public  Form current;

       public ModifierFormationForm(Resources res,Formation f){
        
            super("Newsfeed",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Modifier Formation");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        TextField titre = new TextField(f.getTitre(),"Titre",20,TextField.ANY);
        TextField description = new TextField(f.getDescritpion(),"Description",20,TextField.ANY);
        TextField lieu = new TextField(f.getLieu(),"Lieu",20,TextField.ANY);
        TextField prix = new TextField(String.valueOf(f.getPrix()),"Prix",20,TextField.ANY);

        ComboBox catCombo = new ComboBox();
        catCombo.addItem("Informatique");
        catCombo.addItem("Mécanique");
        
        if(f.getCategory()=="Informatique"){
            catCombo.setSelectedIndex(0);
        }
        else
            catCombo.setSelectedIndex(1);
            
        titre.setUIID("titre");
        description.setUIID("desc");
        lieu.setUIID("lieu");
        prix.setUIID("prix");
        //catCombo.setUIID("category");
        
        titre.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        lieu.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);
        //catCombo.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        
        btnModifier.addActionListener(l->{
            f.setTitre(titre.getText());
            f.setDescritpion(description.getText());
            f.setLieu(lieu.getText());
            f.setPrix(Integer. parseInt(prix.getText()));
             if(catCombo.getSelectedIndex()==0){
             f.setCategory("Informatique");
        }
        else
             f.setCategory("Mécanique");
            
        
        
        if(ServiceFormation.getInstance().modifierFormation(f)){
            new ListFormationForm(res).show();
        }
        });
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l->{
            new ListFormationForm(res).show();
        });
        
        Label l1 = new Label("");
        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");

        Container content = BoxLayout.encloseY(
        l1,l2,
        new FloatingHint(titre),
        createLineSeparator(),
         new FloatingHint(description),
        createLineSeparator(),
         new FloatingHint(lieu),
        createLineSeparator(),
          new FloatingHint(prix),
        createLineSeparator(),
        catCombo,
        l3,l4,
        btnModifier,
        btnAnnuler
        );
        
        add(content);
        show();
                
       }
}

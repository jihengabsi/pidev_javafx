/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Formation;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceFormation;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class AjoutFormationForm extends BaseForm{
    
    Form current;
    public AjoutFormationForm(Resources res){
        super("Newsfeed",BoxLayout.y());
        
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Formation");
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e->{
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1= new Label();
        Label s2 = new Label();
        
        addTab(swipe,s1,res.getImage("back-logo.jpg"),"","",res);
        
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Les formations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton ajout = RadioButton.createToggle("Ajouter formation", barGroup);
        ajout.setUIID("SelectBar");

        ajout.addActionListener((e) -> {
        InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          AjoutFormationForm a = new AjoutFormationForm(res);
            a.show();
            refreshTheme();
         });
        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
          ListFormationForm a = new ListFormationForm(res);
            a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, ajout)
               
        ));

        ajout.setSelected(true);
      
      
        // special case for rotation
       
       
        
        
        TextField titre = new TextField("","entrer titre!");
        titre.setUIID("TextFieldBlack");
        addStringValue("Titre",titre);
        
        TextField Descritpion = new TextField("","entrer description!");
        Descritpion.setUIID("TextFieldBlack");
        addStringValue("Description",Descritpion);
        
        TextField lieu = new TextField("","entrer lieu!");
        lieu.setUIID("TextFieldBlack");
        addStringValue("Lieu",lieu);
       
        ComboBox catCombo = new ComboBox();
        catCombo.addItem("Informatique");
        catCombo.addItem("Mécanique");
        addStringValue("combo",catCombo);
        TextField Prix = new TextField(String.valueOf(0),"entrer Prix!");
        Prix.setUIID("TextFieldBlack");
      
        addStringValue("Prix",Prix);
        
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setUIID("TextFieldBlack");
        addStringValue("Date",datePicker);
         
        Picker datePickerFin = new Picker();
        datePickerFin.setType(Display.PICKER_TYPE_DATE);
        datePickerFin.setUIID("TextFieldBlack");
        addStringValue("Date",datePickerFin);
         
        Button btnAjouter = new Button("Ajouter");
        addStringValue("",btnAjouter);
        
        btnAjouter.addActionListener((e)->{
            
            try{
                if(titre.getText().equals("") || Descritpion.getText().equals("")){
                    Dialog.show("Veuillez verifier les données","","Annuler","OK");
                }
                else{
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog=ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   
                   
                    Formation f = new Formation(
                            String.valueOf(titre.getText()).toString(),
                          String.valueOf(lieu.getText()).toString() ,
                             String.valueOf(Descritpion.getText()).toString(),
                             Integer.parseInt(Prix.getText()),
                             catCombo.getSelectedItem().toString(),
                             format.format(datePicker.getDate()).toString(),
                            format.format(datePickerFin.getDate()).toString());
                    System.out.println("data formation=="+f);
                    ServiceFormation.getInstance().ajoutFormation(f);
                    iDialog.dispose();
                    
                    new ListFormationForm(res).show();
                    
                    refreshTheme();
                }
          
            }
                  catch(Exception ex){
                        ex.printStackTrace();
                        }
        });
        
                
        
    }

    private void addStringValue(String s, Component v) {
       
        add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
        

    }

    private void addTab(Tabs swipe,Label spacer, Image image, String text, String string2, Resources res) {
      
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight()<size){
            image= image.scaledHeight(size/2);
            
        }
        
         else if(image.getHeight()>Display.getInstance().getDisplayHeight()/2){
            image= image.scaledHeight(Display.getInstance().getDisplayHeight()/2);
            
        }
          ScaleImageLabel imageScale = new ScaleImageLabel(image);
          imageScale.setUIID("Container");
          imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
          
          Label overLay = new Label("","ImageOverlay");
          
          Container page1;
        page1 = LayeredLayout.encloseIn(imageScale,overLay,BorderLayout.south(
                BoxLayout.encloseY(new SpanLabel(text,"LargeWhiteText"),
               spacer
                
                        )
        ));
        swipe.addTab("", res.getImage("back-logo.png"),page1);

    }
    
  

   
}

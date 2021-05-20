/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Formation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author DELL
 */
public class ServiceFormation {
    public static ServiceFormation instance =null;
    
    public static boolean resultOk = true;
    
    private ConnectionRequest req;
    
    public static ServiceFormation getInstance(){
        if(instance==null)
            instance = new ServiceFormation();
        return instance;
    }
    
    public ServiceFormation(){
    req = new ConnectionRequest();    
    }
  
    public void ajoutFormation(Formation formation){
        
       
        String url= Statics.BASE_URL+"/addFor?Description="+formation.getDescritpion()+"&Lieu="+formation.getLieu()+"&Titre="+formation.getTitre()+"&Prix="+formation.getPrix()+"&category="+formation.getCategory()
                +"&Date="+formation.getDate()+"&DateFin="+formation.getDateFin();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
           String str= new String(req.getResponseData());
            System.out.println("data =="+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
                
    }
    
    public ArrayList<Formation>affichageFormations()  {
        ArrayList<Formation> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/displayFormation";
        req.setUrl(url);
          req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp=new JSONParser();
                try{
                    Map<String,Object>mapFormations=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapFormations.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps){
                        Formation fe = new Formation();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        String description = obj.get("Description").toString();
                        String lieu = obj.get("Lieu").toString();
                        String titre = obj.get("Titre").toString();
                        float prix =Float.parseFloat(obj.get("Prix").toString());
                        String category = obj.get("category").toString();
                        
                        fe.setCategory(category);
                        fe.setDescritpion(description);
                        fe.setId((int) id);
                        fe.setLieu(lieu);
                        fe.setTitre(titre);
                        fe.setPrix((int)prix);
                        
                        String DateConverter = obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("timestamp")+10,obj.get("Date").toString().lastIndexOf("}"));
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        
                        fe.setDate(dateString);
                        fe.setDateFin(dateString);
                        
                        result.add(fe);
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
              
          });
                  NetworkManager.getInstance().addToQueueAndWait(req);
 
                  return result;
    }
    
    public Formation DetailFormation(int id,Formation fe){
        String url=Statics.BASE_URL+"/detailFormation?"+id;
        req.setUrl(url);  
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt)->{
            
            JSONParser jsonp = new JSONParser();
                try{
                    Map<String,Object>obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                        fe.setCategory(obj.get("category").toString());
                        fe.setDescritpion(obj.get("Description").toString());
                       
                        fe.setLieu(obj.get("Lieu").toString());
                        fe.setTitre(obj.get("Titre").toString());
                        fe.setPrix(Integer.parseInt(obj.get("Prix").toString()));
                }catch(IOException ex){
                        ex.printStackTrace();
                }
            System.out.println("data==="+str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return fe;
    }
    
    public boolean deleteFormation(int id){
        String url = Statics.BASE_URL+"/deleteFormation?id="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 req.removeResponseListener(this);

            }
      
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public boolean modifierFormation(Formation form){
        String url = Statics.BASE_URL+"/updateFormation?id="+form.getId()+"&Titre="+form.getTitre()+"&Description="+form.getDescritpion()+"&Prix="+form.getPrix()+"&Lieu="+
                form.getLieu()+"&category="+form.getCategory();
        req.setUrl(url);
           req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode()==200;
                req.removeResponseListener(this);


            }
      
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
}
 
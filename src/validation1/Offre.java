/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

/**
 *
 * @author monia
 */
public class Offre {
    
    private int id;
    private String title;
    private String ville;
    private String domain;
    private String CompanyName;
    private int salary;
    private String type;
    private String Description;

    public Offre() {
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", title=" + title + ", ville=" + ville + ", domain=" + domain + ", CompanyName=" + CompanyName + ", salary=" + salary + ", type=" + type + ", Description=" + Description + '}';
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

 
    
    
    
}

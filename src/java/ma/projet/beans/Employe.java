/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.chart.PieChart.Data;

import javax.persistence.*;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
 

@Entity
 
 
public class Employe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	public String getNom() {
		return nom;
	}

    public Employe(String nom, String prenom, Date dateNaissance, String photo, Service service, Employe chef) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.photo = photo;
        this.service = service;
        this.chef = chef;
    }

    public Employe() {
    }
        
        

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Employe getChef() {
		return chef;
	}

	public void setChef(Employe chef) {
		this.chef = chef;
	}

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	private String photo;
	@ManyToOne
	private Service service;
	@ManyToOne
	private Employe chef;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
        
        
         public List<Object[]> nbemploye(){
        List<Object[]> employes = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employes  = session.createQuery("select count(e.nom), e.nom from Employe e group by e.nom").list();
        session.getTransaction().commit();
        return employes;
    }
    
    public List<Employe> getbydates(Date d1 , Date d2){
        List <Employe> employes = new ArrayList<>();
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
         employes  = session.createQuery("from Employe e where e.dateNaissance between :d1 and :d2").setParameter("d1", d1).setParameter("d2", d2).list();
        session.getTransaction().commit();
        return employes;
        
    }

}

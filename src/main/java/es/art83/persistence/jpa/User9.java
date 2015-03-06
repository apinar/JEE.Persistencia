package es.art83.persistence.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User9 {
	@Id
    private Integer id;

    private String description;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Category3 category;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Phone3 phone;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Boat3 boat;
    

    public User9() {
        super();
    }
    
    public User9(Integer id, String description) {
        super();
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static void main(String[] args) {
        JpaFactory.dropAndCreateTables();
        EntityManager em = JpaFactory.getEntityManagerFactory().createEntityManager();
        
        //Phone de 1 a 0
        //Category de 1 a 1
        //Boat de 1 a 0
        
        
        /*
        User9 u = new User9(1, "Soy u");
        List<Vehicle2> vehicles = new ArrayList<Vehicle2>();
        
        User9 u2 = new User9(2, "Soy u");
        // Create
        em.getTransaction().begin();
        em.persist(u);
        em.persist(u2);
        em.getTransaction().commit();
        // find
        System.out.println(em.find(User9.class, 1));
        System.out.println(em.find(User9.class, 2));
        System.out.println(em.find(User9.class, 3));
        
        */
    }
    
}

package es.art83.persistence.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import es.art83.persistence.models.utils.PhoneType;

@NamedQuery(name = Phone3.FIND_PHONES_BY_TYPE, query = "SELECT p FROM Phone2 p WHERE p.phoneType = :"
        + Phone3.TYPE)
@Entity
public class Phone3 {
    public static final String FIND_PHONES_BY_TYPE = "findPhonesByType3";

    public static final String TYPE = "type";

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private PhoneType phoneType;

    private int number;

    public Phone3(PhoneType phoneType, int number) {
        this.phoneType = phoneType;
        this.number = number;
    }

    public Phone3() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone [id=" + id + ", phoneType=" + phoneType + ", number=" + number + "]";
    }

    public static void main(String[] args) {
        JpaFactory.dropAndCreateTables();
        EntityManager entityManager = JpaFactory.getEntityManagerFactory().createEntityManager();
        List<Phone3> phones = new ArrayList<Phone3>();
        phones.add(new Phone3(PhoneType.HOME, 666));
        phones.add(new Phone3(PhoneType.MOBILE, 999));
        phones.add(new Phone3(PhoneType.WORK, 000));
        phones.add(new Phone3(PhoneType.HOME, 111));
        phones.add(new Phone3(PhoneType.MOBILE, 444));
        phones.add(new Phone3(PhoneType.WORK, 222));
        // Create
        entityManager.getTransaction().begin();
        for (Phone3 phone : phones) {
            entityManager.persist(phone);
        }
        entityManager.getTransaction().commit();
        // find
        Query query1 = entityManager
                .createQuery("SELECT p.phoneType FROM Phone2 p WHERE p.number = 666");
        System.out.println("Query: " + query1.getSingleResult());

        Query query2 = entityManager.createNamedQuery(Phone3.FIND_PHONES_BY_TYPE);
        query2.setParameter(Phone3.TYPE, PhoneType.MOBILE);
        System.out.println("Named query: " + query2.getResultList());
    }
}

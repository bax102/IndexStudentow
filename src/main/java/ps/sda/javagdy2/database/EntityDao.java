package ps.sda.javagdy2.database;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ps.sda.javagdy2.database.model.IBaseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa której celem jest zapewnienie metod CRUD na obiektach bazodanowych.
 *
 *
 */
public class EntityDao {
    // CRUD
    public void saveOrUpdate(IBaseEntity entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity); // insert / update
            transaction.commit();
        } // try with resources - zamknie sesjÄ (session.close()) automatycznie po
        // zamkniÄciu klamry try
    }

    // delete from ... where id = {}
    public void delete(IBaseEntity entity) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity); // delete
            transaction.commit();
        } // try with resources - zamknie sesjÄ (session.close()) automatycznie po
        // zamkniÄciu klamry try
    }


    // select * from ... where id = {}
    public <T extends IBaseEntity> T getById(Class<T> classT, Long id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            T result = session.get(classT, id); // select
            return result;
        }
    }


    public <T extends IBaseEntity> List<T> list(Class<T> classT) {
        List<T> orderList = new ArrayList<>();

        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            // budowniczy zapytania
            CriteriaBuilder builder = session.getCriteriaBuilder();

            // tworzymy obiekt zawierajÄcy kryteria zapytania O OBIEKT Order
            CriteriaQuery<T> criteriaQuery = builder.createQuery(classT);

            // tabela w ktĂłrej bÄdziemy wyszukiwaÄ
            Root<T> table = criteriaQuery.from(classT);

            // wykonaj zapytanie na tabeli table, uĹźyj kryteriĂłw "criteria query"
            criteriaQuery.select(table);

            // wykonaj zapytanie na bazie i wyniki dopisz do listy
            orderList.addAll(session.createQuery(criteriaQuery).list());
        }

        return orderList;
    }

}
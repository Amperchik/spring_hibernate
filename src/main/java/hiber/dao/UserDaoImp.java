package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUserByCar(String model, int version) {
        List<User> request=new ArrayList<>();
        Session session= null;
        try {
            session=sessionFactory.getCurrentSession();
            TypedQuery<User> query = session.createQuery("FROM User user " +
                    "  WHERE car.model = :mod and car.series=:ver");
            query.setParameter("mod", model).setParameter("ver", version);
            request=query.getResultList();
        } catch (Exception e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
        return request;

    }

}

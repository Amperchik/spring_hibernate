package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
    @SuppressWarnings("unchecked")
    public Car getCar(String model, int version) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("FROM Car car " +
                "Left outer join fetch car.owner  WHERE car.model = :mod and car.series=:ver");
        query.setParameter("mod", model).setParameter("ver", version);
        return query.getSingleResult();

    }

}

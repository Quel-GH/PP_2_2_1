package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

    @Override
    public List<User> getUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.car.model = ?1 and u.car.series = ?2");
        return query.setParameter(1,model).setParameter(2,series).getResultList();
    }

}

package services;

import entities.User;
import interfaces.UserServiceRemote;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateful
public class UserService implements UserServiceRemote {
    @PersistenceContext(unitName="pidev-ejb")
    EntityManager em;
    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean login(String emailAddress, String pwd) {
        Query query=em.createQuery("SELECT u FROM User u WHERE u.emailAddress = :emailAddress AND u.password = :pwd").setParameter("emailAddress", emailAddress).setParameter("pwd", pwd);
        if(query.getResultList().size() == 0)
            return false;
        return true;

    }

    @Override
    public User findUserByEmail(String email) {
        Query query=em.createQuery("select a from User a where a.emailAddress = :email").setParameter("email", email);
        List<User> user=query.getResultList();
        if(user.size()>0)
            return user.get(0);
        return null;

    }
}

package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user, int id) {
        User userFromDB = showUser(id);

        userFromDB.setName(user.getName());
        userFromDB.setSurname(user.getSurname());
        userFromDB.setAge(user.getAge());

        entityManager.merge(userFromDB);

    }

    @Override
    public User showUser(int id) {
        return entityManager.createQuery("from User where id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteUser(int id) {
        User userFromDB = showUser(id);

        entityManager.remove(userFromDB);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("select user From User user ").getResultList();
    }
}

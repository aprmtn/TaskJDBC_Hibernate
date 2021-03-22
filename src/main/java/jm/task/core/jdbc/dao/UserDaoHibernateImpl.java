package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users "
                + "(id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                + " name VARCHAR(50) not NULL,"
                + " lastName VARCHAR(50) not NULL,"
                + " age TINYINT not NULL)").executeUpdate();
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        session.flush();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("from User").list();
        transaction.commit();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        transaction.commit();
        session.flush();
        session.close();
    }
}

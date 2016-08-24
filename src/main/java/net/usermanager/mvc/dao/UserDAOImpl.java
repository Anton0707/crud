package net.usermanager.mvc.dao;

import net.usermanager.mvc.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 07.08.2016.
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User was add. User info: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User was update. User info: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null)
        {
            session.delete(user);
            logger.info("User was remove. User info: " + user);
        }
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User was load. User info: " + user);
        return user;
    }

    @Override
    public Long count() {
        return (long) sessionFactory.getCurrentSession().createCriteria(User.class).list().size();
    }

    @Override
    public Long count(Integer age)
    {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Expression.like("age", age));
        List<User>  users = (List<User>)criteria.list();
        return (long) users.size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers(Integer age, Integer offset, Integer maxResults) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.setFirstResult(offset!=null?offset:0);
        criteria.setMaxResults(maxResults!=null?maxResults:10);
        if (age != null) {
            criteria.add(Expression.like("age", age));
        }
        return (List<User>)criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer getPositionById(int id, Integer age) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        if (age != null) {
            criteria.add(Expression.like("age", age));
        }
        List<User>  users = (List<User>)criteria.list();
        Integer position = 0;
        for (User user:users)
        {
            position++;
            if (user.getId() == id)
                break;
        }
        return position;
    }
}

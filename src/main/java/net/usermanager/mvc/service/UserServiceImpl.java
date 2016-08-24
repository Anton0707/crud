package net.usermanager.mvc.service;

import net.usermanager.mvc.dao.UserDAO;
import net.usermanager.mvc.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Oleg on 07.08.2016.
 */

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDAO.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDAO.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public Long count() {
        return this.userDAO.count();
    }

    @Override
    @Transactional
    public Long count(Integer age) {
        return this.userDAO.count(age);
    }

    @Override
    @Transactional
    public List<User> listUsers(Integer age, Integer offset, Integer maxResults) {
        return this.userDAO.listUsers(age, offset, maxResults);
    }

    @Override
    @Transactional
    public Integer getPositionById(int id, Integer age) {
        return this.userDAO.getPositionById(id, age);
    }
}

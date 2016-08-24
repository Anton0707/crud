package net.usermanager.mvc.dao;

import net.usermanager.mvc.model.User;

import java.util.List;

/**
 * Created by Oleg on 07.08.2016.
 */
public interface UserDAO {

    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public Integer getPositionById(int id, Integer age);

    public Long count();

    public Long count(Integer age);

    public List<User> listUsers(Integer age, Integer offset, Integer maxResults);

}

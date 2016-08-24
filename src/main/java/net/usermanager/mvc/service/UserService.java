package net.usermanager.mvc.service;

import net.usermanager.mvc.model.User;

import java.util.List;

/**
 * Created by Oleg on 07.08.2016.
 */
public interface UserService {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserById(int id);

    public Long count();

    public Long count(Integer age);

    public List<User> listUsers(Integer age, Integer offset, Integer maxResults);

    public Integer getPositionById(int id, Integer age);
}

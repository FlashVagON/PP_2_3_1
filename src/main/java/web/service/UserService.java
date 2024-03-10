package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user, int id);
    User showUser(int id);
    void deleteUser(int id);
    List<User> listUsers();
}
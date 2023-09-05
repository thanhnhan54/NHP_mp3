package service;

import dao.UserDAO;
import dto.Pageable;
import model.User;

import java.util.List;

public class UserService {
    UserDAO userDAO = new UserDAO();
    public List<User> findAll(Pageable pageable){
        return userDAO.findAll(pageable);
    }
    public User findById(int id){
        return userDAO.findById(id);
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }
    public User findByUsername(String username){
        return userDAO.findByUsername(username);
    }
    public void createUser(User user){
        userDAO.createUser(user);
    }
    public void deleteUser(int id){
        userDAO.deleteUser(id);
    }
    public void updateUser(User user){
        userDAO.updateUser(user);
    }
    public boolean checkExist(String username){
        return userDAO.checkExist(username);
    }
}

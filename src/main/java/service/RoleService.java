package service;

import dao.RoleDAO;
import model.Role;

import java.util.List;

public class RoleService {
    RoleDAO roleDAO = new RoleDAO();
    public List<Role> findAll(){
        return roleDAO.findAll();
    }
    public Role findById(int id){
        return roleDAO.findById(id);
    }
}

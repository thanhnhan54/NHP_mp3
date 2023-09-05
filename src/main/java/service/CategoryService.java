package service;

import dao.CategoryDAO;
import model.Category;

import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> findAll(){
        return categoryDAO.findAll();
    }
    public Category findByID(int id){
        return categoryDAO.findById(id);
    }
    public void createCategory(Category category){
        categoryDAO.createCategory(category);
    }
    public void editCategory(Category category){
        categoryDAO.editAuthor(category);
    }
    public void deleteCategory(int id){
        categoryDAO.deleteAuthor(id);
    }
}

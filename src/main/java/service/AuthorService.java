package service;

import dao.AuthorDAO;
import model.Author;

import java.util.List;

public class AuthorService {
    AuthorDAO authorDAO = new AuthorDAO();

    public List<Author> findAll(){
        return authorDAO.findAll();
    }
    public Author findByID(int id){
        return authorDAO.findById(id);
    }
    public void createAuthor(Author author){
        authorDAO.createAuthor(author);
    }
    public void editAuthor(Author author){
        authorDAO.editAuthor(author);
    }
    public void deleteAuthor(int id){
        authorDAO.deleteAuthor(id);
    }
}

package controller;


import dao.SongDAO;
import dao.UploadDAO;
import dto.Pageable;
import model.Song;
import service.SongService;

import javax.servlet.RequestDispatcher;

import model.Author;
import model.Category;
import model.Singer;
import model.Song;
import service.AuthorService;
import service.CategoryService;
import service.SingerService;
import service.SongService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
@WebServlet(name = "SongServlet", urlPatterns = "/admin/songs")


public class SongServlet extends HttpServlet {
    private int TOTAL_ITEMS = 10;
    SongService songService = new SongService();
    AuthorService authorService = new AuthorService();
    CategoryService categoryService = new CategoryService();
    SingerService singerService = new SingerService();
    SongDAO songDAO = new SongDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "create":
                showSreateSong(req,resp);
                break;
            case "edit":
                showEditSong(req,resp);
                break;
            case "delete" :
                deleteSong(req,resp);
                break;
            default:
                showSong(req,resp);
        }
    }

    private void showEditSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Song song = songService.findByID(id);
        req.setAttribute("song", song);
        List<Author> authors = authorService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Singer> singers = singerService.findAll();
        req.setAttribute("authors",authors);
        req.setAttribute("categorys",categories);
        req.setAttribute("singers",singers);
        req.getRequestDispatcher("/JSPhomeAdmin/editSong.jsp").forward(req,resp);
    }
    public void editSong(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int IDAuthor = Integer.parseInt(request.getParameter("author"));
        Author author = authorService.findByID(IDAuthor);
        int IDCategory = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findByID(IDCategory);
        int IDSinger = Integer.parseInt(request.getParameter("singer"));
        Singer singer = singerService.findByID(IDSinger);
        String song1 = request.getParameter("song");
        String image = request.getParameter("image");
        Song song = new Song(id,name,author,category,singer,song1,image);
        songService.editSong(song);
        request.setAttribute("message","Update thành công");
        request.setAttribute("song",song);
        request.setAttribute("author",author);
        request.setAttribute("category",category);
        request.setAttribute("singer",singer);
        request.getRequestDispatcher("/JSPhomeAdmin/editSong.jsp").forward(request,response);

    }

    private void showSreateSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Singer> singers = singerService.findAll();

        req.setAttribute("authors", authors);
        req.setAttribute("categorys", categories);
        req.setAttribute("singers", singers);
        req.getRequestDispatcher("/JSPhomeAdmin/createSong.jsp").forward(req, resp);
//        resp.sendRedirect(req.getContextPath() + "/songs");

    }
    public void createSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        String name = request.getParameter("name");
        int IDAuthor = Integer.parseInt(request.getParameter("author"));
        Author author = authorService.findByID(IDAuthor);
        int IDCategory = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findByID(IDCategory);
        int IDSinger = Integer.parseInt(request.getParameter("singer"));
        Singer singer = singerService.findByID(IDSinger);


        String UPLOAD_DIR = "assets/music";
        String UPLOAD_IMG = "assets/image";

        Part filePart1 = request.getPart("filePart1");
        Part filePart2 = request.getPart("filePart2");
        String fileName1 = getFileName(filePart1);
        String fileName2 = getFileName(filePart2);

        if ((filePart1 != null && fileName1 != null && !fileName1.isEmpty())
                && (filePart2 != null && fileName2 != null && !fileName2.isEmpty())) {
            fileName1 = fileName1.replace("\"", "");
            fileName2 = fileName2.replace("\"", "");
            String filePath1 = UPLOAD_DIR + "/" + fileName1;
            String filePath2 = UPLOAD_IMG + "/" + fileName2;

            saveFileToServer(filePart1, filePath1, fileName1);
            saveFileImgToServer(filePart2,filePath2,fileName2);
            Song song = new Song(name, author, category, singer, filePath1, filePath2);
            songDAO.saveSongToDatabase(song);
            request.setAttribute("message", "create thành công");
            request.setAttribute("song", song);
            request.setAttribute("author", author);
            request.setAttribute("category", category);
            request.setAttribute("singer", singer);
            request.getRequestDispatcher("/JSPhomeAdmin/createSong.jsp").forward(request, response);
        } else {
            response.getWriter().println("Upload failed!");
        }
    }

//copy file name từ máy đưa vô assets
    private void saveFileToServer(Part filePart, String filePath, String fileName) throws IOException, URISyntaxException {
        if (filePart != null && filePath != null) {
            String uploadDir = getServletContext().getRealPath("/assets/music");
            Path p = Paths.get(uploadDir + File.separator + fileName);
            Path destination = Paths.get(filePath);
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream,  p, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    private void saveFileImgToServer(Part filePart, String filePath, String fileName) throws IOException, URISyntaxException {
        if (filePart != null && filePath != null) {
            String uploadDir = getServletContext().getRealPath("/assets/image");
            Path p = Paths.get(uploadDir + File.separator + fileName);
            Path destination = Paths.get(filePath);
            InputStream inputStream = filePart.getInputStream();
            Files.copy(inputStream,  p, StandardCopyOption.REPLACE_EXISTING);
        }
    }

// Lấy file name
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                String fileName = element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
                fileName = fileName.replace("\\", "/");
                return fileName;
            }
        }
        return null;
    }

    private void showSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String search = req.getParameter("search");
        int page = 1;
        if(req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        String sortBy = req.getParameter("sortBy");
        if(sortBy == null){
            sortBy = "asc";
        }
        String nameField = req.getParameter("nameField");
        if(nameField == null){
            nameField = "song.id";
        }
        int filterAuthor = 0;
        if (req.getParameter("author") != null && !Objects.equals(req.getParameter("author"), "")) {
            filterAuthor = Integer.parseInt(req.getParameter("author"));
        }

        int filterSinger = 0;
        if (req.getParameter("singer") != null && !Objects.equals(req.getParameter("singer"), "")) {
            filterSinger = Integer.parseInt(req.getParameter("singer"));
        }

        int filterCategory = 0;
        if (req.getParameter("category") != null && !Objects.equals(req.getParameter("category"), "")) {
            filterCategory = Integer.parseInt(req.getParameter("category"));
        }
        Pageable pageable = new Pageable(search,page,TOTAL_ITEMS,nameField,sortBy,filterAuthor,filterSinger,filterCategory);

        List<Song> songs = new ArrayList<>();
        if (search == null){
            songs=  songDAO.showFilter(pageable, filterAuthor, filterSinger, filterCategory);
        }else {
            songs = songService.findAll(pageable);
        }
        req.setAttribute("authors",authorService.findAll());
        req.setAttribute("singers",singerService.findAll());
        req.setAttribute("categories",categoryService.findAll());
        req.setAttribute("pageable", pageable);
        req.setAttribute("songs", songs);
        req.getRequestDispatcher("/JSPhomeAdmin/admin.jsp").forward(req,resp);


    }

//    private void showFilterSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        String filterAuthor = req.getParameter("author");
//        String filterSinger = req.getParameter("singer");
//        String filterCategory = req.getParameter("category");
//        String search = req.getParameter("search");
//        int page = 1;
//        if(req.getParameter("page") != null){
//            page = Integer.parseInt(req.getParameter("page"));
//        }
//        String sortBy = req.getParameter("sortBy");
//        if(sortBy == null){
//            sortBy = "asc";
//        }
//        String nameField = req.getParameter("nameField");
//        if(nameField == null){
//            nameField = "song.id";
//        }
//        Pageable pageable = new Pageable(search,page,TOTAL_ITEMS,nameField,sortBy);
//        pageable.setFilterAuthor(filterAuthor);
//        pageable.setFilterSinger(filterSinger);
//        pageable.setFilterCategory(filterCategory);
//
//        req.setAttribute("filterAuthor", filterAuthor);
//        req.setAttribute("filterSinger", filterSinger);
//        req.setAttribute("filterCategory", filterCategory);
//        req.setAttribute("pageable", pageable);
//        req.setAttribute("songs", songDAO.showFilter(pageable,filterAuthor,filterSinger,filterCategory));
//        req.getRequestDispatcher("/JSPhomeAdmin/admin.jsp").forward(req,resp);
//    }
    public void deleteSong(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        songService.deleteSong(id);
        showSong(request,response);
    }

    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getParameter("action");
            if (action == null){
                action = "";
            }
            switch (action){
                case "create":
                    try {
                        createSong(req,resp);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "edit":
                    editSong(req,resp);
                    break;
                default:
                    showSong(req,resp);
            }
        }
}

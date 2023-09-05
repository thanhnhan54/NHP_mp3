package controller;

import dao.UploadDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet({"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "assets/music/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String musicName = request.getParameter("name");
        Part filePart = request.getPart("filePart");
        String fileName = getFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            String filePath = UPLOAD_DIR + fileName;
            saveFileToServer(filePart, filePath);
            saveMusicToDatabase(musicName, filePath);
            response.getWriter().println("Upload successful!");
        } else {
            response.getWriter().println("Upload failed!");
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "/");
            }
        }
        return null;
    }

    private void saveFileToServer(Part filePart, String filePath) throws IOException {
        Path destination = Paths.get(filePath);
        Files.copy(filePart.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    }

    private void saveMusicToDatabase(String musicName, String filePath) {
        UploadDAO uploadDAO = new UploadDAO();
        uploadDAO.saveMusic(musicName, filePath);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case "/upload":
                request.getRequestDispatcher("admin.jsp").forward(request,response);
        }
    }
}
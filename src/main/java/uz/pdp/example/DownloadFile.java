package uz.pdp.example;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DownloadFile",value = "/download")
@MultipartConfig
public class DownloadFile extends HttpServlet {
    private static final Path rootPath=Path.of(System.getProperty("user.home"),"Desktop/MyFiles");

    @Override
    public void init(ServletConfig config) {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectories(rootPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/files/download.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newFilename = req.getParameter("myFile");
        byte[] bytes = Files.readAllBytes(rootPath.resolve(newFilename));
        BufferedReader reader = new BufferedReader(new FileReader((rootPath+"my.json")));
        Gson gson = new Gson();
        FileNameGson[]fileNameGsons=gson.fromJson(reader, FileNameGson[].class);
        ArrayList<FileNameGson> fileNameGsons1=new ArrayList<>(List.of(fileNameGsons));
        for (FileNameGson fileNameGson : fileNameGsons1) {
            if (fileNameGson.getNewName().equals(newFilename)) {
                resp.addHeader("Content-Type",fileNameGson.getMimeType());
                resp.addHeader("Content-Disposition","attachment; filename="+fileNameGson.getOriginalName());
            }
        }
        resp.getOutputStream().write(bytes);
    }
}

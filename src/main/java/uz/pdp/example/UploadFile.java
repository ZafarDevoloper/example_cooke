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
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(name = "Upload",value = "/upload")
@MultipartConfig
public class UploadFile extends HttpServlet {

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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/files/upload.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException  {
        Part file = req.getPart("file");
        String submittedFileName = file.getSubmittedFileName();
        String newNAme= UUID.randomUUID()+submittedFileName.substring(submittedFileName.lastIndexOf("."));
        InputStream inputStream = file.getInputStream();
        String originalName = file.getSubmittedFileName();
        String mimeType = file.getContentType();
        System.out.println(originalName);//haqiqiy nomi misol uchunAll.the.Money.in.the.World.(2017).BDRip.x264.AFM.mp4
        System.out.println(mimeType);//kengaytmasi misol uchun video/mp4
        Files.copy(inputStream, rootPath.resolve(newNAme), StandardCopyOption.REPLACE_EXISTING);

        Gson gson = new Gson();
        File file1 = new File(rootPath+"my.json");
        try {
            file1.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
        ArrayList<FileNameGson> fileNameGson=new ArrayList<>();
        fileNameGson.add(new FileNameGson(newNAme,originalName,mimeType));
        String json = gson.toJson(fileNameGson);
        writer.write(json);
        writer.close();
    }
}

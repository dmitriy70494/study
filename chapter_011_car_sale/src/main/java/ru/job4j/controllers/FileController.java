package ru.job4j.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.*;
import ru.job4j.persist.CarStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

public class FileController extends HttpServlet {

    private CarStore store = CarStore.getInstance();

    private ServletFileUpload upload;

    private String uploadPath;

    private static final String UPLOAD_DIRECTORY = "upload";

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 30;
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 400;
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 500;

    private void initFileLoader() {
        if (upload == null) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            this.upload = new ServletFileUpload(factory);
            this.upload.setFileSizeMax(MAX_FILE_SIZE);
            this.upload.setSizeMax(MAX_REQUEST_SIZE);
            this.uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(this.uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
        }
    }


    /**
     * после перезапуска сервер подчищает все фотографии из себя, поэтому они недоступны
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.initFileLoader();
            Car car = null;
            List<FileItem> items = this.upload.parseRequest(req);
            Iterator<FileItem> iter = items.iterator();
            if (iter.hasNext()) {
                car = new Car(
                        0,
                        iter.next().getString(),
                        new Motor(Integer.valueOf(iter.next().getString())),
                        new Transmission(Integer.valueOf(iter.next().getString())),
                        new Bodywork(Integer.valueOf(iter.next().getString())),
                        true,
                        new Timestamp(System.currentTimeMillis()),
                        new User(Integer.valueOf(((User) req.getSession().getAttribute("theUser")).getId())),
                        ""
                );
                FileItem item = iter.next();
                String fileName = new File(item.getName()).getName();
                String filePath = this.uploadPath + File.separator + fileName;
                String path = UPLOAD_DIRECTORY + "/" + fileName;
                File storeFile = new File(filePath);
                item.write(storeFile);
                car.setFoto(path);
            }
        if (car != null) {
            this.store.add(car);
        }
        this.store.drawTableHTML();
        req.getRequestDispatcher("index.html").forward(req, resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

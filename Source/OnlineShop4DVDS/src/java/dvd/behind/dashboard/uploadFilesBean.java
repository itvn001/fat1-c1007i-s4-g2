package dvd.behind.dashboard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "fileUploadController")
@RequestScoped
public class uploadFilesBean {

    //Primitives
    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload;

    /**
     * Creates a new instance of UploadBean
     */
    public uploadFilesBean() {
    }
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    public void handleFileUpload(FileUploadEvent event) {

        ExternalContext extContext =
                FacesContext.getCurrentInstance().getExternalContext();
        File result = new File(extContext.getRealPath("//DVDStore//album//"
                + event.getFile().getFileName()));
        System.out.println(extContext.getRealPath("//DVDStore//album//"
                + event.getFile().getFileName()));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();
            this.file = event.getFile().getFileName();
            // Set file name to session
            session.setAttribute("se_nameonlyBean", this.file);
            FacesMessage msg =
                    new FacesMessage("File Description", "file name: "
                    + event.getFile().getFileName() + "file size: " + event.getFile().getSize() / 1024 + " Kbcontent type: "
                    + event.getFile().getContentType() + "The file was uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
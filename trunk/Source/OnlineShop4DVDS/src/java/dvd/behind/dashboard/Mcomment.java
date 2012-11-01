/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.behind.dashboard;

import dvd.business.dashboard.Feedback;
import dvd.entity.FeedBack;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Administrator
 */
@ManagedBean
@RequestScoped
public class Mcomment {

    /**
     * Creates a new instance of Mcomment
     */
    public Mcomment() {
    }
    private dvd.business.dashboard.Feedback feHand = new Feedback();
    private List<dvd.entity.FeedBack> listComment;

    public List<FeedBack> getListComment() {
        this.listComment = this.feHand.showListAllComment(this.idAlbum);
        return listComment;
    }
    public String idAlbum = "0";

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public void cbcateChoice(ValueChangeEvent e) {
        this.idAlbum = e.getNewValue().toString();
    }

    public void setListComment(List<FeedBack> listComment) {
        this.listComment = listComment;
    }

    public String usetStatusComment(String status) {
        if (status.equals("false")) {
            return "0";
        } else {
            return "2";
        }
    }

    public void updateComment(String id, String status) {
        if (feHand.updateComment(id, status) == true) {
            this.message = dvd.libraries.UImessage.generalMessage("blue", "Update Comment Success", "", "");
        } else {
            this.message = dvd.libraries.UImessage.generalMessage("red", "Sytem error!", "", "");
        }
    }

    public void deleteComment(String id) {
        if (feHand.deleteComment(id) == true) {
            this.message = dvd.libraries.UImessage.generalMessage("blue", "Delete Comment Success", "", "");
        } else {
            this.message = dvd.libraries.UImessage.generalMessage("red", "Sytem error!", "", "");
        }
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String usetTextCommentStt(String status) {
        if (status.equals("false")) {
            return "Peding";
        } else {
            return "Accept";
        }
    }
}

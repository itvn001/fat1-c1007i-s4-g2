/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dvd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Administrator
 */
@Entity
public class FeedBack {

    @Column(name = "FeedBackID")
    @Id
    private int feedBackID;
    @Column(name = "AlbumID")
    private int albumID;
    @Column(name = "UserID")
    private int userID;
    @Column(name = "FeedBackComment")
    private String feedBackComment;
    @Column(name = "FeedBackDateCreate")
    private String feedBackDateCreate;
    @Column(name = "FeedBackStatus")
    private Boolean feedBackStatus;

    public int getFeedBackID() {
        return feedBackID;
    }

    public void setFeedBackID(int feedBackID) {
        this.feedBackID = feedBackID;
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFeedBackComment() {
        return feedBackComment;
    }

    public void setFeedBackComment(String feedBackComment) {
        this.feedBackComment = feedBackComment;
    }

    public String getFeedBackDateCreate() {
        return feedBackDateCreate;
    }

    public void setFeedBackDateCreate(String feedBackDateCreate) {
        this.feedBackDateCreate = feedBackDateCreate;
    }

    public Boolean getFeedBackStatus() {
        return feedBackStatus;
    }

    public void setFeedBackStatus(Boolean feedBackStatus) {
        this.feedBackStatus = feedBackStatus;
    }
}

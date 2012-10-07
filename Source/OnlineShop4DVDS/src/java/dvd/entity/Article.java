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
public class Article {

    @Column(name = "ArticleID")
    @Id
    private int articleID;
    @Column(name = "ArticleTitle")
    private String articleTitle;
    @Column(name = "ArticleImage")
    private String articleImage;
    @Column(name = "ArticleNText")
    private String articleNText;
    @Column(name = "ArticleStatus")
    private Boolean articleStatus;

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleNText() {
        return articleNText;
    }

    public void setArticleNText(String articleNText) {
        this.articleNText = articleNText;
    }

    public Boolean getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Boolean articleStatus) {
        this.articleStatus = articleStatus;
    }
}

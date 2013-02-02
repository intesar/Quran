package com.bia.quran.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author mdshannan
 */
@Embeddable
public class Video implements Serializable {

    private String title;
    private String description;
    private String link;

    public Video(){}
    
    public Video(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

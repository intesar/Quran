package com.bia.quran.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Entity
@Table(name = "Quran")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Quran.findAll", query = "SELECT q FROM Quran q"),
    @NamedQuery(name = "Quran.findById", query = "SELECT q FROM Quran q WHERE q.id = :id"),
    @NamedQuery(name = "Quran.findByDatabaseId", query = "SELECT q FROM Quran q WHERE q.databaseId = :databaseId"),
    @NamedQuery(name = "Quran.findBySuraId", query = "SELECT q FROM Quran q WHERE q.suraId = :suraId ORDER BY verseId"),
    @NamedQuery(name = "Quran.findByVerseId", query = "SELECT q FROM Quran q WHERE q.verseId = :verseId")
})
public class Quran implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "ayahText")
    private String ayahText;
    @Column(name = "databaseId")
    private Short databaseId;
    @Column(name = "suraId")
    private Integer suraId;
    @Column(name = "verseId")
    private Integer verseId;

    public Quran() {
    }

    public Quran(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }

    public Short getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Short databaseId) {
        this.databaseId = databaseId;
    }

    public Integer getSuraId() {
        return suraId;
    }

    public void setSuraId(Integer suraId) {
        this.suraId = suraId;
    }

    public Integer getVerseId() {
        return verseId;
    }

    public void setVerseId(Integer verseId) {
        this.verseId = verseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quran)) {
            return false;
        }
        Quran other = (Quran) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "qurandbinsert1.Quran[ id=" + id + " ]";
    }
    
}

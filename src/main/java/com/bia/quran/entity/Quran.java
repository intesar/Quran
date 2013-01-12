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
    @NamedQuery(name = "Quran.findByDatabaseID", query = "SELECT q FROM Quran q WHERE q.databaseID = :databaseID"),
    @NamedQuery(name = "Quran.findBySuraID", query = "SELECT q FROM Quran q WHERE q.suraID = :suraID ORDER BY verseID"),
    @NamedQuery(name = "Quran.findByVerseID", query = "SELECT q FROM Quran q WHERE q.verseID = :verseID")
})
public class Quran implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Column(name = "AyahText")
    private String ayahText;
    @Column(name = "DatabaseID")
    private Short databaseID;
    @Column(name = "SuraID")
    private Integer suraID;
    @Column(name = "VerseID")
    private Integer verseID;

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

    public Short getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(Short databaseID) {
        this.databaseID = databaseID;
    }

    public Integer getSuraID() {
        return suraID;
    }

    public void setSuraID(Integer suraID) {
        this.suraID = suraID;
    }

    public Integer getVerseID() {
        return verseID;
    }

    public void setVerseID(Integer verseID) {
        this.verseID = verseID;
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

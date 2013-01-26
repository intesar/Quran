package com.bia.quran.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.solr.analysis.*;
import org.hibernate.annotations.Cache;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.*;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 * @author Atef Ahmed
 */
@Entity
@Table(name = "Ayah")
@Cache(usage = NONSTRICT_READ_WRITE)
@Indexed
@AnalyzerDefs({
    @AnalyzerDef(name = "ayah-analyzer",
    tokenizer =
    @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = StandardFilterFactory.class),
        @TokenFilterDef(factory = StopFilterFactory.class),
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SynonymFilterFactory.class, params = {
            @Parameter(name = "ignoreCase", value = "true"),
            @Parameter(name = "expand", value = "true"),
            @Parameter(name = "synonyms", value = "data/ayah-synonyms.properties")}),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
            @Parameter(name = "language", value = "English")
        })
    })
})
@XmlRootElement
public class Ayah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // ayah number
    @Field
    @NumericField
    @Column(name = "ayahId")
    private Integer ayahId;
    // ayah
    @Field
    @Analyzer(definition = "ayah-analyzer")
    @Lob
    @Column(name = "ayahText")
    private String ayahText;
    // sura numbber
    @IndexedEmbedded
    @ManyToOne
    private Surah surah;
    // verse number
    @Field
    @NumericField
    @Column(name = "verseId")
    private Integer verseId;

    public Ayah() {
    }

    public Ayah(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAyahId() {
        return ayahId;
    }

    public void setAyahId(Integer ayahId) {
        this.ayahId = ayahId;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }

    public Surah getSurah() {
        return surah;
    }

    public void setSurah(Surah surah) {
        this.surah = surah;
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
        if (!(object instanceof Ayah)) {
            return false;
        }
        Ayah other = (Ayah) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ayah{" + "id=" + id + ", ayahId=" + ayahId + ", ayahText=" + ayahText + ", surah=" + surah + ", verseId=" + verseId + '}';
    }
}

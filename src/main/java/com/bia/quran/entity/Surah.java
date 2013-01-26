package com.bia.quran.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.solr.analysis.*;
import org.hibernate.annotations.Cache;
import static org.hibernate.annotations.CacheConcurrencyStrategy.NONSTRICT_READ_WRITE;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Entity
@Table(name = "Surah")
@Cache(usage = NONSTRICT_READ_WRITE)
@Indexed
@AnalyzerDefs({
    @AnalyzerDef(name = "surah-analyzer",
            tokenizer =
            @TokenizerDef(factory = KeywordTokenizerFactory.class),
            filters = {
        @TokenFilterDef(factory = StandardFilterFactory.class),
        @TokenFilterDef(factory = StopFilterFactory.class),
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SynonymFilterFactory.class, params = {
            @Parameter(name = "ignoreCase", value = "true"),
            @Parameter(name = "expand", value = "true"),
            @Parameter(name = "synonyms", value = "data/surah-synonyms.properties")}),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
            @Parameter(name = "language", value = "English")
        })
    })
})
@XmlRootElement
public class Surah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Field
    @NumericField
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // name
    @Field
    @Analyzer(definition = "surah-analyzer")
    @Lob
    @Column(name = "name")
    private String name;

    public Surah() {
    }

    public Surah(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Surah)) {
            return false;
        }
        Surah other = (Surah) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Surah{" + "id=" + id + ", name=" + name + '}';
    }
}

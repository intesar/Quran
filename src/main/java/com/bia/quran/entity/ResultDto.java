package com.bia.quran.entity;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mdshannan
 */
@XmlRootElement
public class ResultDto implements Serializable {

    private int ayahHits;
    private List<Ayah> ayahs;

    public ResultDto() {
    }

    public ResultDto(int ayahHits, List<Ayah> ayahs) {
        this.ayahHits = ayahHits;
        this.ayahs = ayahs;
    }

    public int getAyahHits() {
        return ayahHits;
    }

    public void setAyahHits(int ayahHits) {
        this.ayahHits = ayahHits;
    }

    public List<Ayah> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Ayah> ayahs) {
        this.ayahs = ayahs;
    }
}

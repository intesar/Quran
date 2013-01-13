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
    private List<Quran> ayahs;

    public ResultDto() {
    }

    public ResultDto(int ayahHits, List<Quran> ayahs) {
        this.ayahHits = ayahHits;
        this.ayahs = ayahs;
    }

    public int getAyahHits() {
        return ayahHits;
    }

    public void setAyahHits(int ayahHits) {
        this.ayahHits = ayahHits;
    }

    public List<Quran> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Quran> ayahs) {
        this.ayahs = ayahs;
    }
}

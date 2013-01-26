package com.bia.quran.entity;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@XmlRootElement
public class ResultDto implements Serializable {

    private int ayahHits;
    private int surahHits;
    private List<Ayah> ayahs;
    private List<Surah> surahs;

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

    public void setSurahs(List<Surah> surahs) {
        this.surahs = surahs;
    }

    public List<Surah> getSurahs() {
        return surahs;
    }

    public int getSurahHits() {
        return surahHits;
    }

    public void setSurahHits(int surahHits) {
        this.surahHits = surahHits;
    }
}

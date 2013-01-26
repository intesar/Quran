package com.bia.quran.service;

import com.bia.quran.dao.AyahRepository;
import com.bia.quran.dao.SurahRepository;
import com.bia.quran.entity.Ayah;
import com.bia.quran.entity.ResultDto;
import com.bia.quran.entity.Surah;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p> Quran Search|Facade
 *
 * @author Intesar Mohammed <mdshannan@gmail.com>
 */
@Component
public class QuranServiceImpl {

    protected static final Logger logger = LoggerFactory.getLogger(QuranServiceImpl.class);
    protected static final String SURA_NO_REGEX = "([1-9]|[1-9][0-9]|10[0-9]|11[0-4])";
    protected static final String SURA_PREFIX_NO_REGEX = "suraId:" + SURA_NO_REGEX;
    protected static final String SURA_NO_REGEX_BETWEEN = SURA_NO_REGEX + "-" + SURA_NO_REGEX;
    protected static final String SURA_PREFIX_NO_REGEX_BETWEEN = SURA_PREFIX_NO_REGEX + "-" + SURA_PREFIX_NO_REGEX;
    @Autowired
    protected AyahRepository ayahRepository;
    @Autowired
    protected SurahRepository surahRepository;
    @Autowired
    protected OOBDataReader oOBDataReader;

    /*
     * Searches entire db for the term Search by sura number (e.g 1, 23, 114,
     * suraID:1, suraID:34. valid suraID values 1-114) Search by sura number
     * range (e.g 1-4, 100-114) Search by Sura name Search by term
     */
    public ResultDto search(String term) {
        logger.info("Search term: " + term);
        List<Ayah> list;
        ResultDto dto = new ResultDto();
        if (term.matches(SURA_NO_REGEX) || term.matches(SURA_PREFIX_NO_REGEX)) {
            String suraNo = term;
            String[] tokens = term.split(":");
            if (tokens.length > 1) {
                suraNo = tokens[1];
            }
            Integer suraId = Integer.parseInt(suraNo);
            logger.info("suraId : " + suraId);
            list = ayahRepository.findBySurahId(suraId);
        } else if (term.matches(SURA_NO_REGEX_BETWEEN)) {
            String[] fromTo = term.split("-");
            list = ayahRepository.findBySurahIdBetween(Integer.parseInt(fromTo[0]), Integer.parseInt(fromTo[1]));
        } else {
            list = ayahRepository.search(term);
        }
        dto.setAyahs(list);
        dto.setAyahHits(list == null ? 0 : list.size());
        return dto;
    }

    /**
     * reinitializes db
     */
    public void reinitDB() {
        logger.info(("deleting all db data"));
        ayahRepository.deleteAll();
        surahRepository.deleteAll();

        logger.info("loading Surah's oob");
        List<Surah> surahs = oOBDataReader.readSuraData();
        surahRepository.save(surahs);

        logger.info("loading Ayah's oob");
        List<Ayah> list = oOBDataReader.readAyahData();
        ayahRepository.save(list);
    }
}

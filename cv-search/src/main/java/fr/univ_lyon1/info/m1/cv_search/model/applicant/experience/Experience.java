package fr.univ_lyon1.info.m1.cv_search.model.applicant.experience;

import java.util.List;

public class Experience {
    private String company;
    private String start;
    private String end;
    private List<String> keywords;

    /**
     * constructor of an Applicant Experiences.
     * @param company the company name
     * @param start date of start
     * @param end date of end if exist
     * @param keywords get the differents technos
     */
    public Experience(String company, String start, String end, List<String> keywords) {
        this.company = company;
        this.start = start;
        this.end = end;
        this.keywords = keywords;
    }

    public String getCompany() {
        return company;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}

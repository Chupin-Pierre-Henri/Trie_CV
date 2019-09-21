package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.experience.Experience;

import java.util.*;

public class ApplicantList implements Iterable<Applicant> {
    private List<Applicant> list = new ArrayList<Applicant>();

    public void add(Applicant a) {
        list.add(a);
    }

    public Object size() {
        return list.size();
    }

    @Override
    public Iterator<Applicant> iterator() {
        return list.iterator();
    }

    /**
     * Clear the list of applicants.
     */
    public void clear() {
        list.clear();
    }

    /**
     * Sets the content of the applicant list.
     */
    public void setList(ApplicantList list) {
        this.list = list.list;
    }

    /**
     * return the name of Applicant in list.
     *
     * @return list of String
     */
    public List<String> getNamesOfApplicants() {
        List<String> result = new ArrayList<String>();
        for (Applicant a : list) {
            result.add(a.getName());
        }
        return result;
    }

    /**
     * return the experience of Applicant in list.
     *
     * @return list of list
     */
    public Map<String, List> getExperiencesOfApplicants() {
        Map<String, List> result = new HashMap<String, List>();
        for (Applicant a : list) {
            List<Experience> experiences = a.getExperiences();
            List<Map> translateExperiences = new ArrayList<Map>();
            for (Experience experience : experiences) {
                //get attributes
                String company = experience.getCompany();
                String start = experience.getStart();
                String end = experience.getEnd();
                List<String> keywords = experience.getKeywords();

                //add to a list
                Map<String, Object> tranlateExperience = new HashMap<String, Object>();
                tranlateExperience.put("company", company);
                tranlateExperience.put("start", start);
                tranlateExperience.put("end", end);
                tranlateExperience.put("keywords", keywords);

                //add to the list of experience
                translateExperiences.add(tranlateExperience);
            }
            result.put(a.getName(), translateExperiences);
        }
        return result;
    }
}

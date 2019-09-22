package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.experience.Experience;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplicantBuilder {

    File file;

    public ApplicantBuilder(File f) {
        this.file = f;
    }

    public ApplicantBuilder(String filename) {
        this.file = new File(filename);
    }

    /**
     * Build the applicant from the Yaml file provided to the constructor.
     */
    public Applicant build() {
        Applicant a = new Applicant();
        Yaml yaml = new Yaml();
        Map<String, Object> map;
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Error(e);
        }

        a.setName((String) map.get("name"));

        // Cast may fail if the Yaml is incorrect. Ideally we should provide
        // clean error messages.
        @SuppressWarnings("unchecked")
        Map<String, Integer> skills = (Map<String, Integer>) map.get("skills");

        for (String skill : skills.keySet()) {
            Integer value = skills.get(skill);
            a.setSkill(skill, value);
        }

        List<Experience> experiences = new ArrayList<Experience>();
        Map<String, Map> rawExperiences = (Map<String, Map>) map.get("experience");
        for (String experienceKey : rawExperiences.keySet()) {
            Map<String, Object> values = rawExperiences.get(experienceKey);

            //attributes
            String name = experienceKey;
            String start = "";
            String end = "";
            List<String> keywords = new ArrayList<String>();
            for (String key : values.keySet()) {
                Object value = values.get(key);
                switch (key) {
                    case "start":
                        if (value instanceof Integer) {
                            start = Integer.toString((Integer) value);
                        }
                        break;
                    case "end":
                        if (value instanceof Integer) {
                            end = Integer.toString((Integer) value);
                        }
                        break;
                    case "keywords":
                        if (value instanceof List) {
                            keywords = (List<String>) value;
                        }
                        break;
                    default:
                        break;
                }
            }
            if (!name.equals("") && !start.equals("")) {
                Experience experience = new Experience(name, start, end, keywords);
                experiences.add(experience);
            }
        }
        a.setExperiences(experiences);

        return a;
    }
}

package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.experience.Experience;
import org.junit.Test;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantListBuilder;

public class ApplicantTest {

    @Test
    public void testReadApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");

        // When
        Applicant a = builder.build();

        // Then
        assertEquals(70, a.getSkill("c++"));
        assertEquals("John Smith", a.getName());
    }

    @Test
    public void testReadManyApplicant() {
        // Given
        ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));

        // When
        ApplicantList list = builder.build();

        // Then
        boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
                assertEquals(90, a.getSkill("c"));
                assertEquals(70, a.getSkill("c++"));
                johnFound = true;
            }
        }
        assertTrue(johnFound);
    }

    @Test
    public void testExperience(){
        // Given
        List<String> keyWords = new ArrayList<String>();
        keyWords.add("c");
        keyWords.add("c++");
        keyWords.add("java");
        Experience exp = new Experience("Google", "2005", "2010", keyWords);
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");

        // When
        Applicant a = builder.build();
        assertEquals(a.getExperiences().get(0).getCompany(), exp.getCompany());
        assertEquals(a.getExperiences().get(0).getStart(), exp.getStart());
        assertEquals(a.getExperiences().get(0).getEnd(), exp.getEnd());
        assertEquals(a.getExperiences().get(0).getKeywords().get(0), exp.getKeywords().get(0));
    }
}

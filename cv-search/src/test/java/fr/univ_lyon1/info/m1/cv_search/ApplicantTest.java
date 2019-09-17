package fr.univ_lyon1.info.m1.cv_search;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import fr.univ_lyon1.info.m1.cv_search.model.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantBuilder;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantList;
import fr.univ_lyon1.info.m1.cv_search.model.ApplicantListBuilder;

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
}

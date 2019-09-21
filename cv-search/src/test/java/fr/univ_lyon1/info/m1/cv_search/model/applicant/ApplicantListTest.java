package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ApplicantListTest {



    @Test
    public void getNamesOfApplicants() {
        // Given
        ApplicantList listApplicants = new ApplicantListBuilder(new File(".")).build();
        List<String> name = new ArrayList<String>();

        // When
        name = listApplicants.getNamesOfApplicants();
        String name1 = name.get(0);
        String name2 = name.get(1);

        // Then
        assertEquals("John Smith", name1);
        assertEquals("Foo Bar", name2);
    }
}
package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayesNonInitialise() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile();
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesNouvelEmploye() {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Nouveau",
                LocalDate.now(), LocalDate.now(), 0, 0, 0,
                0, 0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesNettementMoinsDe10() {
        // Given :
        //LocalDate dummyDate = LocalDate.now();
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 5,
                1, 0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesNettementPlusDe10() {
        // Given :
        //LocalDate dummyDate = LocalDate.now();
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 15,
                1, 0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(true, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesAuxLimites10() {
        // Given :
        //LocalDate dummyDate = LocalDate.now();
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 10,
                1, 0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(true, res);
    }

    @Test
    void aLegalementDroitADesCongesPayesAuxLimites9() {
        // Given :
        //LocalDate dummyDate = LocalDate.now();
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                1, 0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res);
    }

    @Test
    void aLegalementDroitADesCongesTousLesCasAuxLimites() {
        // Given :
        //LocalDate dummyDate = LocalDate.now();
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                1, 0);
        // When :
        boolean res = aide.aLegalementDroitADesCongesPayes();
        // Then :
        Assertions.assertEquals(false, res);

        aide.setJoursTravaillesAnneeNMoins1(10);
        // When, Then :
        Assertions.assertEquals(true, aide.aLegalementDroitADesCongesPayes());
    }



    @Test
    void testCalculeJoursDeCongeDecomptesPourPlage() {
        // When :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                1, 0);

        // When :
        Set<LocalDate> res = aide.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 2));

        // Then :
        LinkedHashSet<LocalDate> expected = new  LinkedHashSet<>();
        expected.add(LocalDate.of(2022, 7, 1));
        expected.add(LocalDate.parse("2022-07-02"));
        Assertions.assertEquals(expected, res);
    }


    @ParameterizedTest(name = "Entre {0} et {1}, nombre de JoursDeCongeDecomptes devrait être {2}")
    @CsvSource({
            "'2022-07-01', '2022-07-02', 2",
            "'2022-07-01', '2022-07-03', 2",
            "'2022-07-02', '2022-07-04', 1",
            "'2022-07-02', '2022-07-02', 0"
    })
    void testCalculeJoursDeCongeDecomptesPourPlageParametrized(String debut, String fin, double expectedNbJoursDeCongeDecomptes) {
        // Given :
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 10,
                1, 0);

        // When :
         Set<LocalDate> res = aide.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.parse(debut), LocalDate.parse(fin));

        // Then :
        Assertions.assertEquals(expectedNbJoursDeCongeDecomptes, res.size());
    }


}







/*package com.ipi.jva350.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.xml.stream.Location;

import java.time.LocalDate;
import java.util.LinkedHashSet;


class SalarieAideADomicileTest {

    @Test
    void aLegalementDroitADesCongesPayes() {

        //Given
        SalarieAideADomicile salarieAideADomicile = new SalarieAideADomicile("Jean", LocalDate.now(), LocalDate.now(), 0, 0, 5, 0, 0);
        //When
        boolean res = salarieAideADomicile.aLegalementDroitADesCongesPayes();
        //Then
        // Assertions.assertThat(res).isFalse();
        Assertions.assertEquals(false, res);
    }
    @Test void employQuiNapasDroitsADesConges() {

        SalarieAideADomicile salarieAideADomicile1 = new SalarieAideADomicile("Jean", LocalDate.now(), LocalDate.now(), 0, 0, 15, 0, 0);
        //When
        boolean res1 = salarieAideADomicile1.aLegalementDroitADesCongesPayes();
        //Then
        // Assertions.assertThat(res).isFalse();
        Assertions.assertEquals(true, res1);


    }
    //@Disabled
       @Test void CongesPayesAuxLimitesNeuf(){
           SalarieAideADomicile salarieAideADomicile1 = new SalarieAideADomicile();
           salarieAideADomicile1.setJoursTravaillesAnneeNMoins1(9.0);
           //When
           boolean res1 = salarieAideADomicile1.aLegalementDroitADesCongesPayes();
           //Then
           Assertions.assertEquals(res1,false);

        }
        //@Disabled
        @Test void testCalculJoursDeCongeDecomptesPourPlage() {
        SalarieAideADomicile salarieAideADomicile1 = new SalarieAideADomicile("Jean", LocalDate.of(2021, 7, 1), LocalDate.now(), 0, 0, 9, 0, 0);

        LinkedHashSet<LocalDate> res = salarieAideADomicile1.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 2));
        LinkedHashSet<LocalDate>  expected = new LinkedHashSet<>();
        expected.add(LocalDate.of(2022,7,1));
        expected.add(LocalDate.parse("2022-07-22"));
        Assertions.assertEquals(false,res);

    }

/*
        @ParameterizedTest(name = "Entre {0} est nombre de jour et de c")
        @CsvSource({
               "'2022-07-02','2022-07-03',2"
        })
        public  void testCalculJoursDeCongeDecomptesPourPlageParam(String debut, String fin, double expectedNbjoursDesCongesDecomptes) {
            SalarieAideADomicile salarieAideADomicile1 = new SalarieAideADomicile("Jean", LocalDate.of(2021, 7, 1), LocalDate.now(), 0, 0, 9, 0, 0);

           //WHEN
            LinkedHashSet<LocalDate>  res = salarieAideADomicile1.calculeJoursDeCongeDecomptesPourPlage(LocalDate.parse(debut), LocalDate.parse(fin));

            //THEN
            Assertions.assertEquals(expectedNbjoursDesCongesDecomptes,res.size());

        }


    }*/

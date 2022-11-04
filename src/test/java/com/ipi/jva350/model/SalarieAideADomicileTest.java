package com.ipi.jva350.model;


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
    @Disabled
       @Test void CongesPayesAuxLimitesNeuf(){
           SalarieAideADomicile salarieAideADomicile1 = new SalarieAideADomicile();
           salarieAideADomicile1.setJoursTravaillesAnneeNMoins1(10.0);
           //When
           boolean res1 = salarieAideADomicile1.aLegalementDroitADesCongesPayes();
           //Then
           Assertions.assertEquals(res1,false);

        }
        @Disabled
        @Test void testCalculJoursDeCongeDecomptesPourPlage() {
        SalarieAideADomicile salarieAideADomicile1 = new SalarieAideADomicile("Jean", LocalDate.of(2021, 7, 1), LocalDate.now(), 0, 0, 9, 0, 0);

        LinkedHashSet<LocalDate> res = salarieAideADomicile1.calculeJoursDeCongeDecomptesPourPlage(
                LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 2));
        LinkedHashSet<LocalDate>  expected = new LinkedHashSet<>();
        expected.add(LocalDate.of(2022,7,1));
        expected.add(LocalDate.parse("2022-07-22"));
        Assertions.assertEquals(false,res);

    }

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


    }

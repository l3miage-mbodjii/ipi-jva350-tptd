package com.ipi.jva350.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.xml.stream.Location;

import java.time.LocalDate;


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

    }

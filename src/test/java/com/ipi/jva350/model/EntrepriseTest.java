package com.ipi.jva350.model;

import io.cucumber.java.en.When;
import org.h2.tools.Console;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

class EntrepriseTest {

    @Test
    void estDansPlage() {

        // Given
        LocalDate date = LocalDate.of(2021,7,1);
        LocalDate dateDebut = LocalDate.of(2021,6,1);
        LocalDate dateFin = LocalDate.of(2021,8,1);

        //when
        boolean res = Entreprise.estDansPlage(date,dateDebut,dateFin);

        //Then
        Assertions.assertEquals(true,res);


    }

    @Test
    void estDansPlageAvantDateDebut() {

        // Given
        LocalDate date = LocalDate.of(2020,7,2);
        LocalDate dateDebut = LocalDate.of(2021,7,1);
        LocalDate dateFin = LocalDate.of(2021,7,3);

        //When
        boolean res = Entreprise.estDansPlage(date,dateDebut,dateFin);

        //Then
        Assertions.assertEquals(false,res);

    }

    @Test
    void estDansPlageApresDateFin() {

        // Given
        LocalDate date = LocalDate.of(2022,7,2);
        LocalDate dateDebut = LocalDate.of(2021,7,1);
        LocalDate dateFin = LocalDate.of(2021,7,3);

        //When
        boolean res = Entreprise.estDansPlage(date,dateDebut,dateFin);

        //Then
        Assertions.assertEquals(false,res);

    }

    @Test
    void estDansPlageLimite() {

        // Given
        LocalDate date = LocalDate.of(2021,7,1);
        LocalDate dateDebut = LocalDate.of(2021,7,1);
        LocalDate dateFin = LocalDate.of(2021,7,1);

        //When
        boolean res = Entreprise.estDansPlage(date,dateDebut,dateFin);

        //Then
        Assertions.assertEquals(true,res);

    }


    @ParameterizedTest(name = "{0} est un jour feries")
    @ValueSource(strings = {
            "2022-01-01",
            "2022-05-01",
            "2022-07-14",
            "2022-08-15",
            "2022-12-25"
    })
    void estJourFerie(String date) {

        //Given When
        boolean res = Entreprise.estJourFerie(LocalDate.parse(date));
        //Then
        Assertions.assertEquals(true,res);
    }

    @ParameterizedTest(name = "{0} est un jour feries")
    @ValueSource(strings = {
            "2022-01-10",
            "2022-08-01",
            "2021-12-14",
            "2022-08-13",
            "2022-09-17"
    })
    void estNonJourFerie(String date) {

        //Given When
        boolean res = Entreprise.estJourFerie(LocalDate.parse(date));
        //Then
        Assertions.assertEquals(false,res);
    }
}
package com.ipi.jva350.model;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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


}
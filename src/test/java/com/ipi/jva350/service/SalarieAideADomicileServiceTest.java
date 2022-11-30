package com.ipi.jva350.service;
import com.ipi.jva350.exception.SalarieException;
import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SalarieAideADomicileServiceTest {

    @MockBean
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Autowired
    private SalarieAideADomicileRepository salarieAideADomicileRepositorybd;

    @Test
    void testClotureMois() throws SalarieException {
        // Given (aurait aussi pu être mis en méthode @BeforeEach pour toutes les autres méthodes de test)
        SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
                LocalDate.of(2021, 7, 1), LocalDate.now(),
                0, 0, 9,
                1, 0);
        // When
        salarieAideADomicileService.clotureMois(aide, 20);
        // Then
        Assertions.assertEquals(20, aide.getJoursTravaillesAnneeN());
    }


    @ParameterizedTest(name = "Vous avez {5} jours de conges permis ")
    @CsvSource({
            "'2022-01-01',20.0,'2020-01-01','2022-10-01','2022-10-27',36.0",
            "'2022-02-01',15.0,'2021-01-04','2022-09-01','2022-09-16',25.0",
            "'2022-08-01',8.0,'2019-05-08','2022-07-01','2022-07-03',13.0",
            "'2022-09-01',0.0,'2022-08-01','2022-09-03','2022-09-20',0.0"
    })
    void testCalculeLimiteEntrepriseCongesPermis(String moisEnCours, double congesPayesAcquisAnneeNMoins1,
                                                 String moisDebutContrat,
                                                 String premierJourDeConge, String dernierJourDeConge, double expectedValue) {

        //Given
        Mockito.when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(-5.0);
       long limiteConges = 0;
        //When
       limiteConges =  salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
                LocalDate.parse(moisEnCours),
                congesPayesAcquisAnneeNMoins1,LocalDate.parse(moisDebutContrat),
                LocalDate.parse(premierJourDeConge),
                LocalDate.parse(dernierJourDeConge)

        );
        //Then
        Assertions.assertEquals(expectedValue,limiteConges);
    }


    @Test
    void testIntegrationCalculeLimiteEntrepriseCongesPermis() throws SalarieException {

        //Given
    SalarieAideADomicile aide = new SalarieAideADomicile("Jeanne",
            LocalDate.of(2021, 7, 1), LocalDate.now(),
            0, 0, 9,
            1, 0);
      // When
             salarieAideADomicileService.creerSalarieAideADomicile(aide);
             double limiteConges = salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(
                     aide.getMoisEnCours(),
                     aide.getCongesPayesAcquisAnneeNMoins1(),
                     aide.getMoisDebutContrat(),
                     LocalDate.of(2022,8,01),
                     LocalDate.of(2022,8,25)
             );

             //then
        Assertions.assertEquals(1,limiteConges);

    }
    }








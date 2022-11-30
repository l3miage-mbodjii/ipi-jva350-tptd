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
public class SalarieAideADomicileServiceTest {

    @MockBean
    private SalarieAideADomicileRepository salarieAideADomicileRepository;
    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @Test
    @BeforeEach
    void repositSalarie(){
       Mockito.when(salarieAideADomicileRepository.partCongesPrisTotauxAnneeNMoins1()).thenReturn(-5.0);
   }

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


    @ParameterizedTest(name = "Vous avez {10} jours de conges permis ")
    @CsvSource({
            "'Fall', '2020-01-01', '2022-01-01', 50.0, 20.0, 70.0, 40.0, 30.0, '2022-06-06', '2022-07-01', 54.0",

    })
    void testIntegrationCalculeLimiteEntrepriseCongesPermis(String nom, String moisDebutContrat, String  moisEnCours,
                                                            double joursTravaillesAnneeN, double congesPayesAcquisAnneeN,
                                                            double joursTravaillesAnneeNMoins1,
                                                            double congesPayesAcquisAnneeNMoins1,
                                                            double congesPayesPrisAnneeNMoins1,String premierJourDeConge, String dernierJourDeConge, double expectedValue) throws SalarieException {

        SalarieAideADomicile aide = new SalarieAideADomicile(nom,LocalDate.parse(moisDebutContrat),
                LocalDate.parse(moisEnCours),joursTravaillesAnneeN,congesPayesAcquisAnneeN,
                joursTravaillesAnneeNMoins1,congesPayesAcquisAnneeNMoins1,congesPayesPrisAnneeNMoins1);

        salarieAideADomicileService.creerSalarieAideADomicile(aide);

        long limiteConges = 0;
        //When
        limiteConges =  salarieAideADomicileService.calculeLimiteEntrepriseCongesPermis(aide.getMoisEnCours(),aide.getCongesPayesAcquisAnneeNMoins1(),
                aide.getMoisDebutContrat(),LocalDate.parse(premierJourDeConge),LocalDate.parse(dernierJourDeConge));

        System.out.println(limiteConges);

        Assertions.assertEquals(expectedValue,limiteConges);


    }
}








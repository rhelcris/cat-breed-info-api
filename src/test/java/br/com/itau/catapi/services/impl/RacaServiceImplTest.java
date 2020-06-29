package br.com.itau.catapi.services.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import br.com.itau.catapi.services.RacaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RacaServiceImplTest {

    private RacaService racaService;

    @MockBean
    private RacaRepository repository;

    @BeforeEach
    public void setUp() {
        this.racaService = new RacaServiceImpl(repository);
    }


    @Test
    @DisplayName("Deve buscar uma Raça pelo ID")
    public void deveBuscarUmaRacaPeloIdTest() {
        // Cenário
        String id = "abys";
        Raca raca = Raca.builder().id(id).nome("Abyssinian").origem("Egypt").temperamento("Active, Energetic, Independent, Intelligent, Gentle").build();
        BDDMockito.when( repository.findById(Mockito.anyString()) ).thenReturn( Optional.of(raca) );

        // Ação
        Optional<Raca> returnedRaca = racaService.buscarPeloCondigo(id);

        // Verificação
        assertThat( returnedRaca.isPresent() ).isTrue();
        assertThat( returnedRaca.get().getId() ).isEqualTo( raca.getId() );
        assertThat( returnedRaca.get().getNome() ).isEqualTo( raca.getNome() );
        assertThat( returnedRaca.get().getOrigem() ).isEqualTo( raca.getOrigem() );
        assertThat( returnedRaca.get().getTemperamento() ).isEqualTo( raca.getTemperamento() );

        Mockito.verify(repository, times(1)).findById( Mockito.anyString() );
    }

    @Test
    @DisplayName("Não deve encontrar uma raça inexistente")
    public void naoDeveEncontrarUmaRacaInexistenteTest() {
        // Cenário
        String id = "racaItau";
        BDDMockito.when( repository.findById(id) ).thenReturn( Optional.empty() );

        // Ação
        Optional<Raca> returnedRaca = racaService.buscarPeloCondigo(id);

        // Verificação
        assertThat( returnedRaca.isPresent() ).isFalse();
        Mockito.verify(repository, times(1)).findById(id);
    }

}

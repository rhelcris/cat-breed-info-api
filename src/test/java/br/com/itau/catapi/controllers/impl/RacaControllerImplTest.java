package br.com.itau.catapi.controllers.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.services.RacaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = {RacaControllerImpl.class})
public class RacaControllerImplTest {

    static String API = "/racas";

    @MockBean
    private RacaService racaService;

    @Autowired
    private MockMvc mvc;

    // This object will be magically initialized by initFields methos below
    private JacksonTester<Raca> jsonResult;
    private JacksonTester<Raca> jsonResponse;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    @DisplayName("Deve retornar uma raça")
    public void deveRetornarUmaRacaTest() throws Exception {
        // Cenário
        String id = "abys";
        Raca raca = Raca.builder().id(id).nome("Abyssinian").origem("Egypt").temperamento("Active, Energetic, Independent, Intelligent, Gentle").build();

        BDDMockito.given( racaService.buscarPeloCondigo(Mockito.anyString()) ).willReturn( Optional.of(raca) );

        // Ação
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get(API + "/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        // Verificação
        assertThat( response.getStatus() ).isEqualTo(HttpStatus.OK.value());
        assertThat( response.getContentAsString() )
                .isEqualTo( jsonResponse.write( raca ).getJson() );

    }

    @Test
    @DisplayName("Não deve retornar uma raça")
    public void naoDeveRetornarUmaRacaTest() throws Exception {
        // Cenário
        String id = "abysInexistente";
        BDDMockito.given( racaService.buscarPeloCondigo(Mockito.anyString()) ).willReturn( Optional.empty() );

        // Ação
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders
                        .get(API + "/" + id)
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Verificação
        assertThat( response.getStatus() ).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}

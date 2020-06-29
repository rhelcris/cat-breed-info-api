package br.com.itau.catapi.controllers.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.controllers.RacaController;
import br.com.itau.catapi.services.RacaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RacaControllerImpl implements RacaController {

    private static final Logger logger = LoggerFactory.getLogger(RacaControllerImpl.class);

    private RacaService racaService;

    @Autowired
    public RacaControllerImpl(RacaService racaService) {
        this.racaService = racaService;
    }

    @Override
    public ResponseEntity<Raca> buscarPeloId(String id) {
        logger.info(">>> Buscando Racas pelo ID: " + id);
        Optional<Raca> raca = racaService.buscarPeloCondigo(id);
        return raca.isPresent() ? ResponseEntity.ok(raca.get()) : ResponseEntity.notFound().build();
    }

}

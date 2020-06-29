package br.com.itau.catapi.services.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import br.com.itau.catapi.services.RacaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RacaServiceImpl implements RacaService {

    private static final Logger logger = LoggerFactory.getLogger(RacaServiceImpl.class);

    private RacaRepository racaRepository;

    @Autowired
    public RacaServiceImpl(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    @Override
    public Optional<Raca> buscarPeloCondigo(String id) {
        logger.info("Consulta raça pelo ID: " + id);
        return this.racaRepository.findById(id);
    }

}

package br.com.itau.catapi.services.impl;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.repositories.RacaRepository;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RacaServiceImpl implements RacaService {

    private RacaRepository racaRepository;

    @Autowired
    public RacaServiceImpl(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    @Override
    public Optional<Raca> buscarPeloCondigo(String id) {
        return this.racaRepository.findById(id);
    }

}

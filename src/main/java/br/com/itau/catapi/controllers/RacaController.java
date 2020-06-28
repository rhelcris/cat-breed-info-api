package br.com.itau.catapi.controllers;

import br.com.itau.catapi.beans.Raca;
import br.com.itau.catapi.services.RacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("racas")
public class RacaController {

    private RacaService racaService;

    @Autowired
    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Raca> buscarPeloId(@PathVariable("id") String id) {
        Optional<Raca> raca = racaService.buscarPeloCondigo(id);
        return raca.isPresent() ? ResponseEntity.ok(raca.get()) : ResponseEntity.notFound().build();
    }


}

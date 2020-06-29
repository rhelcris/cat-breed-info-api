package br.com.itau.catapi.controllers;

import br.com.itau.catapi.beans.Raca;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("racas")
public interface RacaController {

    @GetMapping("{id}")
    ResponseEntity<Raca> buscarPeloId(@PathVariable("id") String id);

}

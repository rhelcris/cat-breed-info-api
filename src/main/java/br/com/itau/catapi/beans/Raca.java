package br.com.itau.catapi.beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "racas")
public class Raca {

    @JsonAlias("id")
    @Id
    private String id;
    @JsonAlias("name")
    private String nome;
    @JsonAlias("temperament")
    private String temperamento;
    @JsonAlias("origin")
    private String origem;

}

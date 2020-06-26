package br.com.rodrism.projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.rodrism.projeto.model.Departamento;

public interface DepartamentoDao extends CrudRepository<Departamento, Integer> {

}

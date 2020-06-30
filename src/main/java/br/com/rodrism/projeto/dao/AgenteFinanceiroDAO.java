package br.com.rodrism.projeto.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.rodrism.projeto.model.AgenteFinanceiro;

public interface AgenteFinanceiroDAO extends CrudRepository<AgenteFinanceiro, Integer>{

	public List<AgenteFinanceiro> findAllOrderByVolumeDesc();
	
}

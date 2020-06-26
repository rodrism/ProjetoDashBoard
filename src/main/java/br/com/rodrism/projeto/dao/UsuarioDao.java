package br.com.rodrism.projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.rodrism.projeto.model.Usuario;
//DAO interface que faz a comunicacao com o banco
public interface UsuarioDao extends CrudRepository<Usuario, Integer>{
	
	public Usuario findByEmailAndSenha(String email, String senha);
		
	public Usuario findByRacf(String racf);
	
	public Usuario findByRacfOrEmail(String racf, String email);
}

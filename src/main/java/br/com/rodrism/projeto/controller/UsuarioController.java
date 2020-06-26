package br.com.rodrism.projeto.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrism.projeto.dao.UsuarioDao;
import br.com.rodrism.projeto.model.Usuario;

@RestController
public class UsuarioController {

	@Autowired //injecao da dependencia --- informa pro springboot que precisa de uma classe que faz select no usuario, delego encontrar e instanciar uma classe que siga esse padrao, entao ele nao encontra e cria
	private UsuarioDao dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> listarTudo(){
		ArrayList<Usuario> lista = (ArrayList<Usuario>)dao.findAll();//select * from table
		for (Usuario u : lista) {
			u.setSenha("********");
		}
		return lista;
	}
	
	@PostMapping("/login")// exemplo de metrodo post para fazer select na base trazendo o usuario por email e senha
	public Usuario login(@RequestBody Usuario userEmailSenha) {
		Usuario user = dao.findByEmailAndSenha(userEmailSenha.getEmail(), userEmailSenha.getSenha());
		return user;
	}
	
	@PostMapping("/login2")
	public ResponseEntity<Usuario> login2(@RequestBody Usuario userRacfEmail) {
		
		Usuario user = dao.findByRacfOrEmail(userRacfEmail.getRacf(), userRacfEmail.getEmail());
		
		if (user != null) {
			if(userRacfEmail.getSenha().equals(user.getSenha())) {
				user.setSenha("********");
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(403).build();
			}
		}
		return ResponseEntity.ok(user);
	}
	
	
	@PostMapping("/buscaracf")
	public Usuario buscaRacf(@RequestBody Usuario racf) {
		Usuario rf = dao.findByRacf(racf.getRacf());
		return rf;
	}
}

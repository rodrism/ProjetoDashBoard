package br.com.rodrism.projeto.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrism.projeto.dao.UsuarioDao;
import br.com.rodrism.projeto.model.Usuario;

@RestController
@CrossOrigin("*")//colocar de qual origem esse controller é acessivel, pode ser um ip ou tudo como esta *
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
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody Usuario incompleto) {
		
		Usuario resultado = dao.findByRacfOrEmail(incompleto.getRacf(), incompleto.getEmail());
		if (resultado != null) {  // achei um usuario no banco!
			if (incompleto.getSenha().equals(resultado.getSenha())) { // as senhas coincidem??
				resultado.setSenha("*******");
				return ResponseEntity.ok(resultado);
			}
			else {
				
				return ResponseEntity.status(403).build(); // retorno "Forbidden"
			}
		}
		else {
			return ResponseEntity.status(404).build();   // retorno um status de "Não encontrado"
		}

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

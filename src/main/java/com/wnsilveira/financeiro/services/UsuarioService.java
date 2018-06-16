package com.wnsilveira.financeiro.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wnsilveira.financeiro.domain.Usuario;
import com.wnsilveira.financeiro.repositories.UsuarioRepository;
import com.wnsilveira.financeiro.security.UserSS;
import com.wnsilveira.financeiro.services.exceptions.AuthorizationException;
import com.wnsilveira.financeiro.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		UserSS user = UserService.authenticated();
		if (user == null || !id.equals(user.getId())) throw new AuthorizationException("Acesso negado");
		
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}
}

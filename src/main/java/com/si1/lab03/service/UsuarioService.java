package com.si1.lab03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.si1.lab03.model.Usuario;
import com.si1.lab03.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public void register(Usuario user) {
		if (usuarioRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException();
		} else {
			usuarioRepository.save(user);
			return;
		}
	}

	public Usuario login(Usuario user) {
		if (validation(user.getEmail(), user.getPassword()) == null) {
			throw new RuntimeException();
		} else {
			return validation(user.getEmail(), user.getPassword());
		}

	}

	public Usuario validation(String email, String password) {
		Usuario user = usuarioRepository.findByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				return user;
			}return null;
		}
		return user;
	}
	
}

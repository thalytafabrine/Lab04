package com.si1.lab03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.si1.lab03.model.Usuario;
import com.si1.lab03.service.UsuarioService;

@RestController
@RequestMapping(value = "/")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/getin")
	public Usuario login(@RequestBody Usuario usuario) {
		return usuarioService.login(usuario);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public void cadastrar(@RequestBody Usuario usuario) {
		usuarioService.register(usuario);
	}
	
}

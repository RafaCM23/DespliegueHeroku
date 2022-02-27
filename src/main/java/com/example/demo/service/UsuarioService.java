package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;

@Service
public class UsuarioService {

	
	
private List<Usuario> repositorio = new ArrayList<>();
	
	public List<Usuario> findAll(){
		return repositorio;
	}
	
	public Usuario getByNick(String nk) {
		
		Usuario buscado= new Usuario( nk);
		
		if(repositorio.indexOf(buscado)!=-1) {
			return repositorio.get(repositorio.indexOf(buscado));
		}else {
			return  null;
		}
		
	}
	
	public int autenticar(String nk, String psw) {
		int respuesta=-1;
		Usuario buscado=getByNick(nk);
		if(buscado!=null && buscado.getPassword().equals(psw)) {			
				respuesta=1;
			
		}
		return respuesta;
	}
	
	@PostConstruct
	public void init() {
		
		Usuario nuevo= new Usuario("rafa","contra","c/micasa","655753576","correo@gmail.com");
		repositorio.add(nuevo);
		
		Usuario nuevo2= new Usuario("admin","admin","c/Vendetta","655666777","anon@gmail.com");
		repositorio.add(nuevo2);
		
		Usuario nuevo3= new Usuario("pepe","pepe33","c/Oliva","685816613","pepe333@hotmail.com");
		repositorio.add(nuevo3);
	
	}
	
	public int addPedido(String nk, Pedido p) {
		int respuesta =-1;
		
		Usuario buscado=getByNick(nk);
		if(buscado!=null && p!=null) {
			buscado.addPedido(p);
			respuesta=1;
		}
		
		
		return respuesta;
		
	}
	
	public List<Pedido> getAllPedidos(String nk){
		
		List<Pedido> respuesta = null;
		Usuario buscado=getByNick(nk);
		if (buscado!=null) {
			respuesta=buscado.getPedidos();
		}
		
		return respuesta;
	}
	
	
	public void addUsuario(Usuario user) {
		this.repositorio.add(user);
	}
}

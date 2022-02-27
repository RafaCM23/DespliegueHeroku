package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Usuario {
	

	private int id;
	private String nickname;
	private String password;
	private List<Pedido> pedidos;
	private String direccion;
	private String tlfn;
	private String correo;
	
	
	public Usuario() {
		this.pedidos= new ArrayList<Pedido>();
	}
	
	public Usuario(String nick) {
		this.nickname=nick;
		this.pedidos= new ArrayList<Pedido>();
	}
	
	
	

	
	
	public Usuario(String nickname, String password) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.pedidos= new ArrayList<Pedido>();
	}
	
	


	



	public Usuario(String nickname, String password, String direccion, String tlfn, String correo) {
		super();
		this.nickname = nickname;
		this.password = password;
		this.direccion = direccion;
		this.tlfn = tlfn;
		this.correo = correo;
		this.pedidos= new ArrayList<Pedido>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void addPedido(Pedido p) {
		pedidos.add(p);
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nickname, other.nickname);
	}
	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTlfn() {
		return tlfn;
	}

	public void setTlfn(String tlfn) {
		this.tlfn = tlfn;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void borrarPedido(Pedido p) {
		if (this.pedidos.contains(p)) {
			this.pedidos.remove(this.pedidos.indexOf(p));
		}
	}

	@Override
	public String toString() {
		StringBuilder respuesta = new StringBuilder();
		respuesta.append("Nick:"+this.nickname+"\n");
		if (!pedidos.isEmpty()) {
			for (Pedido p: pedidos) {
				respuesta.append(p.toString());
			}
		}
		
		return respuesta.toString();
	}
	
	public Pedido getPedido(int id) {
		Pedido buscado = new Pedido();
		buscado.setId(id);
		Pedido.setNserie(Pedido.getNserie()-1);
		
		int encontrado = this.pedidos.indexOf(buscado);
		if (encontrado!=-1) {
			return this.pedidos.get(encontrado);
		}
		else {
			return null;
		}
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
}

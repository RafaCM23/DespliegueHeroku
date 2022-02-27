package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Pedido {
	
	private static int nserie=1;
	private int id;
	private List<LineaPedido> lineas;
	
	
	


	public Pedido() {
		this.lineas=new ArrayList<LineaPedido>();
		this.id = nserie++;
	}


	public Pedido(int id, List<LineaPedido> lineas) {
		super();
		this.id = id;
		this.lineas = lineas;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return id == other.id;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public List<LineaPedido> getlineas() {
		return lineas;
	}
	public String imprimeLinea(int posicion) {
		return lineas.get(posicion).toString();
	}


	public void setlineas(List<LineaPedido> lineas) {
		this.lineas = lineas;
	}

	public void addLinea(LineaPedido p) {
		this.lineas.add(p);
	}


	@Override
	public String toString() {
		StringBuilder respuesta = new StringBuilder();
		respuesta.append("Pedido= ID:"+this.id+" \n");
		for (int i = 0; i < lineas.size(); i++) {
			respuesta.append(lineas.get(i).toString()+ "\n");
			
		}
		return respuesta.toString();
	}


	public static int getNserie() {
		return nserie;
	}


	public static void setNserie(int nserie) {
		Pedido.nserie = nserie;
	}
	
	
}

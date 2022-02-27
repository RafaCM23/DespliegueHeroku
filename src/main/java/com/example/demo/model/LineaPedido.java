package com.example.demo.model;



public class LineaPedido {


	public int id_pedido;
	public int id_producto;
	public String nombreProducto;
	public String imgProducto;
	public int cantidad;
	
	
	
	public LineaPedido() {
		super();
	}

	public LineaPedido(int id_pedido) {
		this.id_pedido=id_pedido;
	}

	public LineaPedido(int id_pedido, int id_producto, int cantidad) {
		super();
		this.id_pedido = id_pedido;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}


	
	public int getId_pedido() {
		return id_pedido;
	}



	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}


	
	public int getId_producto() {
		return id_producto;
	}



	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}


	
	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	
	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	
	public String getImgProducto() {
		return imgProducto;
	}

	public void setImgProducto(String imgProducto) {
		this.imgProducto = imgProducto;
	}

	@Override
	public String toString() {
		return "LineaPedido [id_pedido=" + id_pedido + ", id_producto=" + id_producto + ", cantidad=" + cantidad + "]";
	}
	
	
	
	
}

package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Cantidades;
import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class PedidoService {
	
	
	
	public static final Producto p1 = new Producto(1,"Steam Gift Card 25€");
	public static final Producto p2 = new Producto(2,"Amazon Gift Card 25€");
	public static final Producto p3 = new Producto(3,"Google Play Gift Card 25€");
	public static final Producto p4 = new Producto(4,"Xbox  Gift Card 25€");
	public static final Producto p5 = new Producto(5,"eShop Gift Card 25€");
	public static final Producto p6 = new Producto(6,"PS Store Gift Card 25€");
	
	
	public Pedido creaPedido(Cantidades cant) {
		
		 Pedido nuevo = new Pedido();
		 LineaPedido aux = new LineaPedido();
		 aux.setId_pedido(nuevo.getId());
		 
		 if(cant.getCant1()!=0) {
			 aux.setId_producto(p1.getId()); aux.setCantidad(cant.getCant1());
			 aux.setNombreProducto("Steam Gift Card 25€"); aux.setImgProducto("/imagenes/steamgift.jpg");
			 nuevo.addLinea(aux);
		 }			 		 
		 if(cant.getCant2()!=0) {
			 aux = new LineaPedido(nuevo.getId());	 
			 aux.setId_producto(p2.getId()); aux.setCantidad(cant.getCant2());
			 aux.setNombreProducto("Amazon Gift Card 25€"); aux.setImgProducto("/imagenes/amazongift.png");
			 nuevo.addLinea(aux);
			
		 }
		 if(cant.getCant3()!=0) {
			 aux = new LineaPedido(nuevo.getId());	
			 aux.setId_producto(p3.getId()); aux.setCantidad(cant.getCant3());
			 aux.setNombreProducto("Google Play Gift Card 25€"); aux.setImgProducto("/imagenes/googleplaygift.jpg");
			 nuevo.addLinea(aux);
			
		 }
		 if(cant.getCant4()!=0) {
			 aux = new LineaPedido(nuevo.getId());	
			 aux.setId_producto(p4.getId()); aux.setCantidad(cant.getCant4());
			 aux.setNombreProducto("Xbox Store Gift Card 25€"); aux.setImgProducto("/imagenes/xboxgift.png");
			 nuevo.addLinea(aux);
		 }
		 if(cant.getCant5()!=0) {
			 aux = new LineaPedido(nuevo.getId());	
			 aux.setId_producto(p5.getId()); aux.setCantidad(cant.getCant5());
			 aux.setNombreProducto("Nintendo eShop Gift Card 25€"); aux.setImgProducto("/imagenes/nintendogift.jpg");
			 nuevo.addLinea(aux);
		 }
		 if(cant.getCant6()!=0) {
			 aux = new LineaPedido(nuevo.getId());	
			 aux.setId_producto(p6.getId()); aux.setCantidad(cant.getCant6());
			 aux.setNombreProducto("PS Store Gift Card 25€"); aux.setImgProducto("/imagenes/playgift.jpeg");
			 nuevo.addLinea(aux);
		 }
		
		 return nuevo;
		
	}
	
	public int borraPedido(int id,Usuario user){
		Pedido buscado=user.getPedido(id);
		if(buscado!=null) {
			user.borrarPedido(buscado);
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	
}

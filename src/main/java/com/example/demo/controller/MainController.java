package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.*;

import com.example.demo.service.*;

/**
 * Esta aplicación realiza la función de una pagina web con carrito de la compra
 * al que puedes añadir, editar, eliminar (o pagar) productos. Para acceder a la aplicación
 * necesitaras uno de los usuarios que están creados desde UsuarioService.
 * @author rafa
 *
 */
@Controller
public class MainController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PedidoService pedidoService;
	
	
	/**
	 * Devuelve la pantalla de login
	 * @param user Usuario
	 * @param model Modelo
	 * @return redirect a Seleccion o Login
	 */
	@GetMapping({"/login","/"})
	public String Login(@Valid @ModelAttribute("usuario") Usuario user, Model model) {
		
		
		if(1==usuarioService.autenticar(user.getNickname(),user.getPassword())){
		
			Usuario usuario=usuarioService.getByNick(user.getNickname());
			session.setAttribute("usuario", usuario);
			model.addAttribute("usuario", usuario);
			
			return "redirect:/seleccion";
		}
		else {
			return "login";
		}
	}
	
	/**
	 * Recibe un usuario y comprueba que exista, si es asi redirige a la pagina de seleccion, si no vuelve a cargar el login
	 * @param user Usuario
	 * @param bindingResult
	 * @param model Modelo
	 * @return Selecccion 
	 */
	@PostMapping("/login")
	public String paginaLogin(@Valid @ModelAttribute("usuario") Usuario user,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "login";
		}
		else if(1==usuarioService.autenticar(user.getNickname(),user.getPassword())){
		
			Usuario usuario=usuarioService.getByNick(user.getNickname());
			session.setAttribute("usuario", usuario);
			model.addAttribute("usuario", usuario);
			return "redirect:/seleccion";
		}
		else {
			return "login";
		}
	}
	
	/**
	 * Invalida la sesion
	 * @return Login
	 */
	@GetMapping("/invalidate")
	public String invalidate() {
		
		this.session.invalidate();
		return "redirect:/login";
			
	}
	/**
	 * Comprueba si el usuario está en sesión, si asi asi devuelve sla pagina de seleccion, si no 
	 * devuelve la pagina de error
	 * @return Seleccion 
	 */
	@GetMapping("/seleccion") public String seleccion() {
		 Usuario user =(Usuario)session.getAttribute("usuario");
			if(user != null) {
				
				return "seleccion";
			}
			else {
				 return "redirect:/error";	
			}
	}
	
	/**
	 * Comprueba que el usuario está en sesion, si es asi devuelve el catalogo y añade al modelo una entidad Cantidades
	 * ,si no existe el usuario devuelve la pagina de error.
	 * @param model Modelo
	 * @return Catalogo
	 */
	@GetMapping("/catalogo")
	public String seleccionFormulario(Model model) {
		
		 Usuario user =(Usuario)session.getAttribute("usuario");
		if(user != null) {
			model.addAttribute("cantidades", new Cantidades());
			return "catalogo";
		}
		else {
			 return "redirect:/error";	
		}
		
	}
	
	
	/**
	 * Recibe una entidad Cantidades que se utilizan para crear el pedido. En caso de que el usuario exista en sesion
	 * se crea el pedido y se añade al usuario, en caso contrario devuelve la pagina de error
	 * @param cant Cantidades
	 * @param bindingResult
	 * @return Mis pedidos
	 */
	  @PostMapping("/catalogo/nuevoPedido") public String
	  nuevoPedido(@ModelAttribute("cantidades") Cantidades cant,
			  BindingResult bindingResult) { 
		  
		  
		  if(session.getAttribute("usuario") == null) {
				
			  return "redirect:/error";				  
			}
			else {
				Pedido nuevo = pedidoService.creaPedido(cant);
				Usuario user =(Usuario)session.getAttribute("usuario");
				 
				Collections.reverse(user.getPedidos());
				 user.addPedido(nuevo);
				 Collections.reverse(user.getPedidos());
				 session.setAttribute("usuario", user);
				 
				  return "redirect:/mispedidos";
			}
		 
	  }
	  
	  /**
	   * Si el usuario no esta en sesion devuelve error, si el usuario esta en sesion pero no tiene pedidos devuelve
	   * la pagina "sin pedidos". Si el usuario esta registrado y tiene pedidos muestra los pedidos del usuario
	   * @param model Modelo
	   * @return Mis pedidos
	   */
	  @GetMapping("/mispedidos") public String mispedidos(Model model) { 
		  
		  Usuario user = (Usuario) session.getAttribute("usuario");
		  
		  if(session.getAttribute("usuario") == null) {
				
			  return "redirect:/error";			  
			}
			else {
				List<Pedido> pedidos = user.getPedidos();
				if (pedidos.isEmpty()) {
					return"/sinPedidos";
				}
				
				model.addAttribute("pedidos", pedidos);
				
				return "/mispedidos";
			}
		  
	  }
	  /**
	   * Recibe el id del pedido que se va a editar, y devuelve la pagina editar pedido con los datos del pedido.
	   * Si el usuario no esta registrado devuelve error y si no tiene pedidos devuelve sin pedidos
	   * @param model Modelo
	   * @param id int
	   * @return editarPedido
	   */
	  @GetMapping("/editarpedido") public String editarPedido(Model model,
			  @RequestParam int id) {
		  
		
		  
		  Usuario user = (Usuario) session.getAttribute("usuario");
		  if(user == null) {
				
			  return "redirect:/error";				  
			}
			else {
				List<Pedido> pedidos = user.getPedidos();
				if (pedidos.isEmpty()) {
					return"/sinPedidos";
				}
				else {
					
				
						ArrayList<Integer> cantidadesAux = new ArrayList<Integer>();
						for (LineaPedido linea : user.getPedido(id).getlineas()) {
							cantidadesAux.add(linea.getCantidad());
						}
						model.addAttribute("cantidades", new Cantidades());
						model.addAttribute("idpedido",id);
						session.setAttribute("idpedido",id);
						model.addAttribute("lineas", user.getPedido(id).getlineas());
						return "editarpedido";
					
				
				}
			}
		
	  }
	  
	  /**
	   * Recibe las cantidades editadas y modifica las lineas correspondiendes del pedido con esas cantidades. Si la cantidad
	   * es 0 borra la linea. Si el usuario no esta registrado devuelve error.
	   * @param model Modelo
	   * @param cantidades Cantidades
	   * @return Mis pedidos
	   */
	  @PostMapping("/modificarPedido")
	  public String	  modificaPedido(Model model, @RequestParam(name="cantidad") int[]cantidades) { 
		  
		  
		  Usuario user = (Usuario) session.getAttribute("usuario");
		  if(user == null) {
				
			  return "redirect:/error";				  
			}
			else {
				int id=(int) session.getAttribute("idpedido");
				session.removeAttribute("idpedido");
			
				List<LineaPedido> lineas= user.getPedido(id).getlineas();
				List<LineaPedido> lineasNuevas = new ArrayList<LineaPedido>();
				for (int i = 0; i < lineas.size(); i++) {
					if (cantidades[i]!=0) {
						lineas.get(i).cantidad=cantidades[i];
						lineasNuevas.add(lineas.get(i));
						
					}
					
					
				}
				user.borrarPedido(user.getPedido(id));
				if (!lineasNuevas.isEmpty()) {
					Pedido modificado = new Pedido(id,lineasNuevas);
					Collections.reverse(user.getPedidos());
					user.addPedido(modificado);
					 Collections.reverse(user.getPedidos());
					
				}
				
				
				session.setAttribute("usuario", user);
				  return "redirect:/mispedidos";
			}
		 
	  }
	  
	  /**
	   * Recibe el pedido que se desea pagar y pinta los datos del mismo en la pagina pagarpedido. Si el usuario no esta registrado
	   * devuelve error.
	   * @param model Modelo
	   * @param id int
	   * @return Pagar Pedido
	   */
	  @GetMapping("/pagarpedido")
	  public String pagarPedido(Model model,@RequestParam int id) {
		  
		  Usuario user = (Usuario) session.getAttribute("usuario");
		  if(user == null) {
				
			  return "redirect:/error";				  
			}
		  else {
			  int total=0;
			  ArrayList<Integer> cantidadesAux = new ArrayList<Integer>();
				for (LineaPedido linea : user.getPedido(id).getlineas()) {
					cantidadesAux.add(linea.getCantidad());
					total+=linea.getCantidad();
				}
				
				total=total*25;
				
				model.addAttribute("cantidades", new Cantidades());
				model.addAttribute("idpedido",id);
				session.setAttribute("idpedido",id);
				model.addAttribute("lineas", user.getPedido(id).getlineas());
				model.addAttribute("usuario",user);
				model.addAttribute("direccion",user.getDireccion());
				model.addAttribute("total", total);
				return "pagarpedido";
		  }
		  
	  }
	  /**
	   * Borra el pedido que se ha indicado en sesion. Si el usuario no existe devuelve error.
	   * @param model Modelo
	   * @return Mis pedidos
	   */
	  @PostMapping("/pedidopagado")
	  public String pedidoPagado(Model model) {
		  
		  Usuario user = (Usuario) session.getAttribute("usuario");
		  if(user == null) {
				
			  return "redirect:/error";				  
			}
		  else {
			  int id=(int) session.getAttribute("idpedido");
			  user.borrarPedido(user.getPedido(id));
			  session.setAttribute("usuario", user);
			  return "redirect:/mispedidos";
			  }
		  
	  }
	  /**
	   * Recibe el id del pedido,si el usuario existe y el pedido existe para ese usuario, es borrado, en caso 
	   * contrario devuelve error
	   * @param id int
	   * @return Mis pedidos
	   */
	  @GetMapping("/borrar")
	  public String borrarPedido(@RequestParam int id) {
		  
		  Usuario user = (Usuario) session.getAttribute("usuario");
		  if(user == null) {
				
			  return "redirect:/error";				  
			}
		  else {
		  			int resp=pedidoService.borraPedido(id,user);
		  			if(resp==1) {
		  				return "redirect:/mispedidos";
		  			}
		  			else {
		  				return "redirect:/error";
		  			}
		  }
	  }
		  



}


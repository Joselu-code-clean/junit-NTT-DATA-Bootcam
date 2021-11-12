package bootcamp.junit.service;

import java.util.List;

import bootcamp.junit.model.Articulo;

public interface CarritoCompraServiceI {
	void limpiarCesta();
	
	void addArticulo(Articulo articulo);
	
	Integer insertarArticuloById(int id, Articulo articulo);
	
	int getNumArticulo();
	
	List<Articulo> getArticulos();
	
	Double totalPrice();
	
	Double calculadoraDescuento(Double precio, Double porcentajeDescuento);
	
	Double aplicarDescuento(Integer idArticulo, Double porcentaje);
	
	void addArticuloInDB(Articulo articulo);
}

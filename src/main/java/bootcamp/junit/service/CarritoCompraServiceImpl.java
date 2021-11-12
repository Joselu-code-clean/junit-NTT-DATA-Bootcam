package bootcamp.junit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI{

	@Autowired
	BaseDatosServiceI baseDatos;
	
	private List<Articulo> listaArticulos = new ArrayList<>();
	
	@Override
	public void limpiarCesta() {
		listaArticulos.clear();
	}

	@Override
	public void addArticulo(Articulo articulo) {
		listaArticulos.add(articulo);
	}

	@Override
	public int getNumArticulo() {
		return listaArticulos.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return listaArticulos;
	}

	@Override
	public Double totalPrice() {
		Double total = 0D;
		for (Articulo articulo : listaArticulos) {
			total += articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadoraDescuento(Double precio, Double porcentajeDescuento) {
		return precio - precio * porcentajeDescuento/100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Double resultado = null;
		Articulo articulo = baseDatos.findArticuloByNombre(idArticulo);
		
		if (articulo != null) {
			resultado = calculadoraDescuento(articulo.getPrecio(), porcentaje);
		} else {
			System.out.println("No se ha encontrado ningun articulo con el ID: " + idArticulo);
		}
		
		
		return resultado;
	}

	@Override
	public void addArticuloInDB(Articulo articulo) {
		baseDatos.insertarArticulo(articulo);
	}

	@Override
	public Integer insertarArticuloById(int id, Articulo articulo) {
		listaArticulos.add(articulo);
		return baseDatos.insertarArticuloById(id, articulo);
	}
	

}

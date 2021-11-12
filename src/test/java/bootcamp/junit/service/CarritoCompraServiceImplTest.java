package bootcamp.junit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.model.Articulo;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class CarritoCompraServiceImplTest {

	@Mock
	private BaseDatosServiceI bbddService;
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();
	
	@BeforeEach private void limpiarLista() {
		carritoService.limpiarCesta();
		// Creamos los articulos
		Articulo articulo1 = new Articulo("Camiseta", 10.00);
		Articulo articulo2 = new Articulo("Pantalon", 20.00);
		Articulo articulo3 = new Articulo("Jersey", 15.95);
		
		// Añadimos los articulos a la cesta
		carritoService.addArticulo(articulo1);
		carritoService.addArticulo(articulo2);
		carritoService.addArticulo(articulo3);
	}
	
	@Test
	@Order(1)
	void testLimpiarCesta() {	
		System.out.println("TEST - testLimpiarCesta");
		// Limpiamos la cesta
		carritoService.limpiarCesta();
		
		// Comprobamos la cesta
		assertEquals(0, carritoService.getArticulos().size());
	}
	

	@Test
	@Order(2)
	void testAddArticulo() {
		System.out.println("TEST - testAddArticulo");
		// Creamos los articulos
		Articulo articulo = new Articulo("Zapatos", 80.00); 
		carritoService.addArticulo(articulo);
		// Comprobamos el articulo de la cesta
		assertTrue(carritoService.getArticulos().contains(articulo));
	}

	@Test
	@Order(3)
	void testGetNumArticulo() {
		System.out.println("TEST - testGetNumArticulo");
		// Comprobamos la cesta
		assertEquals(3, carritoService.getNumArticulo());
	}

	@Test
	@Order(4)
	void testGetArticulos() {
		System.out.println("TEST - testGetArticulos");
		List<Articulo> listaComparar = new ArrayList<>();
		listaComparar.add(new Articulo("Camiseta", 10.00));
		listaComparar.add(new Articulo("Pantalon", 20.00));
		assertTrue(carritoService.getArticulos().size() > 0);
	}

	@Test
	@Order(5)
	void testTotalPrice() {
		System.out.println("TEST - testTotalPrice");
		// Comprobamos el precio
		assertEquals(45.95, carritoService.totalPrice());
	}

	@Test
	@Order(6)
	void testCalculadoraDescuento() {
		System.out.println("TEST - testCalculadoraDescuento");
		// Comprobamos el precio
		assertEquals(28.50, carritoService.calculadoraDescuento(30.00D, 5.00D));
	}

	@Test
	@Order(7)
	void testAplicarDescuentoConArticulo() {
		System.out.println("TEST - testAplicarDescuentoConArticulo");
		Articulo articulo = new Articulo("Blusa", 66.35D);
		when(bbddService.findArticuloByNombre(any(Integer.class))).thenReturn(articulo);
		Double res = (double) Math.round(carritoService.aplicarDescuento(1, 10D)*100)/100;
		assertEquals(59.72D, res);
	}
	
	@Test
	@Order(8)
	void testAplicarDescuentoSinArticulo() {
		System.out.println("TEST - testAplicarDescuentoSinArticulo");
		when(bbddService.findArticuloByNombre(any(Integer.class))).thenReturn(null);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(null, res);
	}
	@Test
	@Order(9)
	void testAplicarDescuentoConArticuloVerify() {
		System.out.println("TEST - testAplicarDescuentoConArticuloVerify");
		Articulo articulo = new Articulo("Blusa", 10D);
		when(bbddService.findArticuloByNombre(any(Integer.class))).thenReturn(articulo);
		Double res = (double) Math.round(carritoService.aplicarDescuento(1, 10D)*12)/10;
		assertEquals(10.8D, res);
		Mockito.verify(bbddService).findArticuloByNombre(1);
	}
	
	@Test
	@Order(10)
	void testInsertarArticuloById() {
		System.out.println("TEST - testInsertarArticuloById");
		Articulo articulo = new Articulo("Pijama", 9.9D);
		int id = 0;
		
		when(bbddService.insertarArticuloById(any(Integer.class), any(Articulo.class))).thenReturn(8);
		id = carritoService.insertarArticuloById(8, articulo);
		
		assertEquals(8, id);
		assertTrue(carritoService.getArticulos().contains(articulo));
		verify(bbddService, atLeast(1)).insertarArticuloById(any(Integer.class), any(Articulo.class));
	}
	
}

package bootcamp.junit.bbdd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import bootcamp.junit.model.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI{

	private Map<Integer, Articulo> storage;
	
	@Override
	public void initBD() {
		storage = new HashMap<>();
		storage.put(1, new Articulo("Camiseta", 18.95));
		storage.put(2, new Articulo("Jersey", 21.95));
		storage.put(3, new Articulo("Cinturon", 30.95));
		storage.put(4, new Articulo("Sudadera", 15.95));
		storage.put(5, new Articulo("Pantalon", 25.95));
		storage.put(6, new Articulo("Zapato", 44.95));
		storage.put(7, new Articulo("Chaqueta", 65.95));
		storage.put(8, new Articulo("Chandal", 70.95));
		
	}

	@Override
	public Articulo findArticuloByNombre(Integer identificador) {
		System.out.println("Buscando el articulo con ID: " + identificador);
		return storage.get(identificador);
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {
		System.out.println("Insertando el articulo con nombre: " + articulo.getNombre());
		storage.put(storage.size()+1, articulo);
		return storage.size()+1;
	}
	
	@Override
	public Integer lastIndex() {
		return storage.size();
	}

	@Override
	public Integer insertarArticuloById(int id, Articulo articulo) {
		System.out.println("Insertando el articulo con nombre: " + articulo.getNombre());
		storage.put(id, articulo);
		return storage.size()+1;
	}

}

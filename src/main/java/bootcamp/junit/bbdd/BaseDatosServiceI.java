package bootcamp.junit.bbdd;

import bootcamp.junit.model.Articulo;

public interface BaseDatosServiceI {
	
	void initBD();
	
	Integer insertarArticulo(Articulo articulo);

	Articulo findArticuloByNombre(Integer identificadore);

	Integer lastIndex();
	
	Integer insertarArticuloById(int id, Articulo articulo);
	
}

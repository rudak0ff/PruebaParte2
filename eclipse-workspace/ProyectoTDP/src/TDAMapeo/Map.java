package TDAMapeo;

/**
 * Interface Map - Estructura de Datos (DCIC-UNS).
 * Define los datos y operaciones aplicables sobre una pila.
 * @author Estructuras de Datos, DCIC-UNS.
 * @version 1.0 - Mar�a Lujan Ganuza (2013-2018)
 * @version 2.0 - Federico Joaqu�n (2019-2020) 
 * @param <K> Tipo de dato de las claves a almacenar en el mapeo.
 * @param <V> Tipo de dato de los valores a almacenar en el mapeo.
 */
public interface Map<K,V>{
	
	/**
	 * Consulta el n�mero de entradas del mapeo.
	 * @return N�mero de entradas del mapeo.
	 */
	public int size();
	
	/**
	 * Consulta si el mapeo est� vac�o.
	 * @return Verdadero si el mapeo est� vac�o, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Busca una entrada con clave igual a una clave dada y devuelve el valor asociado, si no existe retorna nulo.
	 * @param key Clave a buscar.
	 * @return Valor de la entrada encontrada.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public V get(K key)throws InvalidKeyException;
	
	/**
	 * Si el mapeo no tiene una entrada con clave key, inserta una entrada con clave key y valor value en el mapeo y devuelve null. 
	 * Si el mapeo ya tiene una entrada con clave key, entonces remplaza su valor y retorna el viejo valor.
	 * @param key Clave de la entrada a crear.
	 * @param value Valor de la entrada a crear. 
	 * @return Valor de la vieja entrada.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public V put(K key, V value) throws InvalidKeyException;
	
	/**
	 * Remueve la entrada con la clave dada en el mapeo y devuelve su valor, o nulo si no fue encontrada.
	 * @param e Entrada a remover.
	 * @return Valor de la entrada removida.
	 * @throws InvalidKeyException si la clave pasada por par�metro es inv�lida.
	 */
	public V remove(K key) throws InvalidKeyException;
	
	/**
	 * Retorna una colecci�n iterable con todas las claves del mapeo.
	 * @return Colecci�n iterable con todas las claves del mapeo.
	 */
	public Iterable<K> keys();
	
	/**
	 * Retorna una colecci�n iterable con todas los valores del mapeo.
	 * @return Colecci�n iterable con todas los valores del mapeo.
	 */
	public Iterable<V> values();
	
	/**
	 * Retorna una colecci�n iterable con todas las entradas del mapeo.
	 * @return Colecci�n iterable con todas las entradas del mapeo.
	 */
	public Iterable<Entry<K,V>> entries();
}

package TDAMapeo;
/**
 * 
 * @author Ilz Matias
 *
 * @param <K> Primer  Tipo de dato de los elementos a almacenar en la entrada.
 * @param <V> Segundo Tipo de dato de los elementos a almacenar en la entrada.
 */
public class Entrada<K, V> implements Entry<K,V> {

	private K key; private V value;
	
	public Entrada(K k,V v) {
		key=k; value=v;
	}
	
	public K getKey() {
		return key;
	}
	/**
	 * Obtiene el valor de la entrada
	 */
	public V getValue() {
		return value;
	}
	/**
	 * Asigna el valor v recibido por parametro a el valor de la entrada
	 */
	public void setValue(V v) {
		value=v;
	}
	/**
	 * Asigna la clave k recibida por parametro a la clave de la entrada
	 */
	public void setKey(K k) {
		key=k;
	}

	
	
}

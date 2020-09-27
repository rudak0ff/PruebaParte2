package TDAMapeo;
/**
 * 
 * @author Ilz Matias
 *
 * @param <K>
 * @param <V>
 */
public interface Entry<K, V> {
	/**
	 * Obtiene la clave de la entrada
	 */
	public K getKey();
	/**
	 * Obtiene el valor de la entrada
	 */
	public V getValue();
	/**
	 * Asigna el valor v recibido por parametro a el valor de la entrada
	 */
	public void setValue(V v);
	/**
	 * Asigna la clave k recibida por parametro a la clave de la entrada
	 */
	public void setKey(K k);
}

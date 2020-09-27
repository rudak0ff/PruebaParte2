package TDAMapeo;

import TDALista.*;
/**
 * Clase MapeoHashAbierto
 * @author Ilz Matias
 *
 * @param <K> Primer Tipo de dato de los elementos a almacenar en el mapeo.
 * @param <V> Segundo Tipo de dato de los elementos a almacenar en el mapeo.
 */
public class MapeoHashAbierto<K, V> implements Map<K, V> {

	protected PositionList<Entrada<K, V>>[] arr;
	protected int N;
	protected int n;
	private static double fc = 0.9;
	/**
	 * Devuelve la clave hasheada
	 * @param k
	 * @return
	 */
	private int hash(K k) {
		int i = Math.abs(k.hashCode());
		return (i % N);
	}
	/**
	 * Metodo constructor parametrizado
	 * @param tam buckets del mapeo
	 */
	public MapeoHashAbierto(int tam) {
		N=tam;
		n = 0;
		arr = (PositionList<Entrada<K, V>>[]) new PositionList[N];
		for (int i = 0; i < N; i++)
			arr[i] = new DoubleLinkedList<Entrada<K,V>>();
	}
	/**
     * Metodo constructor por defecto
     */
	public MapeoHashAbierto() {
		this(13);
	}
	@Override
	public int size() {
		return n;
	}
	@Override
	public boolean isEmpty() {
		return n == 0;
	}
	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		V ret = null;
		int clave = hash(key);
		boolean esta=false;
		PositionList<Entrada<K,V>> l=arr[clave];
		try {
			if(!l.isEmpty()) {
				Position<Entrada<K,V>> pos=l.first();

				while(!esta && pos!=null) {
					if(pos.element().getKey().equals(key)) {
						ret=pos.element().getValue();
						esta=true;
					}
					else {
						if(pos==l.last())
							pos=null;
						else
							pos=l.next(pos);
					}
				}

			}


		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}



		return ret;
	}
	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		V ret = null;
		int clave = hash(key);
		boolean esta = false;
		PositionList<Entrada<K,V>> l=arr[clave];
		try {
			if(!l.isEmpty()) {
				Position<Entrada<K,V>> pos=l.first();
				while(!esta && pos!=null) {
					if(pos.element().getKey().equals(key)) {
						ret=pos.element().getValue();
						pos.element().setValue(value);
						esta=true;
					}
					else {
						if(pos==l.last())
							pos=null;
						else
							pos=l.next(pos);
					}
				}
			}
			if (!esta) {
				Entrada<K,V> e = new Entrada<K,V>(key, value);
				l.addLast(e);
				n++;
			}

			if (n / N > fc)
				agrandarTabla();

		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e1) {
			e1.printStackTrace();
		}
		return ret;

	}
	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		int clave=hash(key);
		boolean esta=false;
		V valor = null;
		PositionList<Entrada<K,V>> l=arr[clave];

		try {
			if(!l.isEmpty()) {
				Position<Entrada<K,V>> pos=l.first();

				while(!esta && pos!=null) {
					if(pos.element().getKey().equals(key)) {
						valor=pos.element().getValue();
						l.remove(pos);
						n--;
						esta=true;
					}
					else
						if(pos==l.last()) {
							pos=null;
						}
						else {
							pos=l.next(pos);
						}
				}

			}

		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return valor;
	}
	@Override
	public Iterable<K> keys() {
		PositionList<K> lista = new DoubleLinkedList<K>();
		for (int i = 0; i < N; i++) {
			for (Position<Entrada<K, V>> en : arr[i].positions()) {
				lista.addLast(en.element().getKey());
			}
		}
		return lista;
	}
	@Override
	public Iterable<V> values() {
		PositionList<V> lista = new DoubleLinkedList<V>();
		for (int i = 0; i < N; i++) {
			for (Position<Entrada<K, V>> en : arr[i].positions()) {
				lista.addLast(en.element().getValue());
			}
		}
		return lista;

	}
	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> lista = new DoubleLinkedList<Entry<K, V>>();
		for (int i = 0; i < N; i++) {
			for (Position<Entrada<K, V>> en : arr[i].positions()) {
				lista.addLast(en.element());
			}
		}
		return lista;
	}
	/**
	 * Aumenta el size del Mapeo
	 */
	public void agrandarTabla() {
		N = proximo_primo(N * 2);
		PositionList<Entrada<K, V>>[] T;
		T = (PositionList<Entrada<K, V>>[]) new DoubleLinkedList[N];
		for (int i = 0; i < N; i++)
			T[i] = new DoubleLinkedList<Entrada<K, V>>();

		for (int i = 0; i < arr.length; i++)
			for (Entrada<K, V> e : arr[i]) {
				int p = hash(e.getKey());
				T[p].addLast(e);
			}
		arr=T;
	}
	 /**
	  * Verifica que la clave key sea valida
	  * @param key
	  * @throws InvalidKeyException Se lanza si la clave key no es valida
	  */
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null) {
			throw new InvalidKeyException("Clave invalida");
		}
	}
	/**
	 * Busca el proximo primo a partir de n
	 * @param n
	 * @return el proximo primo de n
	 */
	private int proximo_primo(int n) {
		boolean es = false;
		n++;
		while (!es) {
			if (esPrimo(n))
				es = true;
			else
				n++;

		}
		return n;
	}
	/**
	 * Devuelve verdadero si el numero n pasado por parametro es primo, falso caso contrario
	 * @param n
	 * @return verdadero si n primo, falso caso contrario
	 */
	private boolean esPrimo(int n) {
		boolean es = false;
		int divisor = 2;
		while (divisor < n && !es) {
			if (n % divisor == 0)
				es = true;
			else
				divisor++;

		}

		return es;
	}

}

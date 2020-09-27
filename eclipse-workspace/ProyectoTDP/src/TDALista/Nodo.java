package TDALista;

public class Nodo<E> implements Position<E>{

	private Nodo<E> siguiente; private E element;
	
	public Nodo(Nodo<E> sig,E elem) {
		siguiente=sig; element=elem;
	}
	
	public Nodo<E> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}

	public void setElement(E element) {
		this.element = element;
	}



	@Override
	public E element() {
		return element;
	}

}

package TDAGrafoMapTDP;
import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import TDAMapeo.*;
/**
 * 
 * @author Ilz Matias
 *
 */
public class Graph {
	private static final Map<Integer,List<Integer>> grafomap = new MapeoHashAbierto<Integer,List<Integer>>(); 
	private static Logger log;

	public Graph(){
		if(log == null) {
			log = Logger.getLogger(Graph.class.getName());
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			log.addHandler(hnd);
			log.setLevel(Level.WARNING);
			Logger rootLogger = log.getParent();
			for (Handler h : rootLogger.getHandlers()) {
				h.setLevel(Level.OFF);
			}
		}
	}
	/**
	 * Se agrega el nodo "node" a la estructura si aun no pertenecia a la estructura.
	 * @param node es el nodo a agregar.
	 */
	public void addNode(int node) {
		try {
			Iterator<Integer> it = grafomap.keys().iterator();
			boolean encontre=false;
			while(it.hasNext() && !encontre) {
				Integer ent = it.next();
				if(ent.equals(node))
					encontre=true;
			}
			//Si no hay un nodo con el mismo valor pasado por parametro
			if(!encontre) {
				grafomap.put(node,new ArrayList<Integer>());
				log.info("El nodo "+node+" ha sido agregado.");
			}
			else {
				log.info("La estructura ya tiene un nodo con valor "+node);
			}
		} catch (InvalidKeyException e) {
			log.warning("Nodo "+node+" que fue ingresado no es correcto.");
		}
	}
	/**
	 * agrega un arco entre el nodo node1 y el nodo
	 * node2, si aún no existía el arco y ambos parámetros son nodos pertenecientes a la estructura.
	 * @param node1 es el nodo predecesor.
	 * @param node2 es el nodo sucesor.
	 */
	public void addEdge(int node1,int node2) {
		Iterator<Integer> itk = grafomap.keys().iterator();
		Iterator<Integer> itk2 = grafomap.keys().iterator();
		boolean estanode1=false;
		boolean estanode2=false;

		while(itk.hasNext() && !estanode1) {
			Integer entry=itk.next();
			if(entry.equals(node1)) {
				estanode1=true;
			}
		}
		while(itk2.hasNext() && !estanode2) {
			Integer entry=itk2.next();
			if(entry.equals(node2)) {
				estanode2=true;
			}
		}

		if(!estanode1) {
			log.warning("No es posible agregar el arco, el nodo "+node1+" no existe en la estructura");
		}
		if(!estanode2) {
			log.warning("No es posible agregar el arco, el nodo "+node2+" no existe en la estructura");
		}
		//Si ambos nodos estan en la estructura, el node1 apuntara a node2
		if(estanode1 && estanode2) {
			try {
				if(!grafomap.get(node1).isEmpty()) {
					boolean conectados=false;
					for(Integer e : grafomap.get(node1)) {
						if(e.equals(node2))
							conectados=true;
					}
					if(conectados) {
						log.info("El arco que va desde el nodo "+node1+" hacia el nodo "+node2+" ya pertenece a la estructura");
					}
					//Si no existe ya un arco entre node1 y node2
					if(!conectados) {
						grafomap.get(node1).add(node2);
						log.info("El arco que va desde el nodo "+node1+" hacia el nodo "+node2+" se agrego con exito a la estructura");
					}
				}
				else {//Si el node1 no tiene arcos previos
					grafomap.get(node1).add(node2);
					log.info("El arco que va desde el nodo "+node1+" hacia el nodo "+node2+" se agrego con exito a la estructura");
				}

			} catch (InvalidKeyException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * remueve el nodo “node” del grafo, si el parámetro es un
	 * nodo de la estructura, y se remueven todos los arcos que tienen relacion con el mismo.
	 * @param node es el nodo a remover.
	 */
	public void removeNode(int node) {
		Iterator<Integer> it = grafomap.keys().iterator();
		Integer entradaRemov=0;
		List<Integer> l;
		boolean encontre=false;
		Integer aux=null;
		try {

			while(it.hasNext() && !encontre) {
				Integer entr=it.next();
				if(entr.equals(node)) {
					entradaRemov=entr;
					encontre=true;
				}
			}

			if(encontre) {
				l=grafomap.remove(entradaRemov);
				//Remuevo todos los arcos salientes del nodo
				for(int i=0; i<l.size(); i++){
					l.remove(i);
				}
				//Remuevo todos los arcos entrantes del nodo
				for(List<Integer> p : grafomap.values()) {
					for(Integer e : p) {
						if(e.equals(node)) {
							aux=e;
						}
					}
					if(aux!=null)
						p.remove(aux);
				}

				log.info("El nodo "+node+" se elimino de la estructura con exito");
			}
			else {
				log.warning("No se puede remover el nodo "+node+" ya que no pertenece a la estructura");
			}

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

	}
	/**
	 * remueve el arco entre el nodo “node1” y el
	 * nodo “node2”, si el arco formado por los parámetros pertenecen a la estructura.
	 * @param node1 es el nodo predecesor.
	 * @param node2 es el nodo sucesor.
	 */
	public void removeEdge(int node1,int node2) {
		boolean estanode1=false;
		boolean estanode2=false;
		Iterator<Integer> itk = grafomap.keys().iterator();
		Iterator<Integer> itk2 = grafomap.keys().iterator();

		while(itk.hasNext() && !estanode1) {
			Integer entry=itk.next();
			if(entry.equals(node1))
				estanode1=true;
		}
		while(itk2.hasNext() && !estanode2) {
			Integer entry=itk2.next();
			if(entry.equals(node2))
				estanode2=true;
		}

		if(!estanode1) {
			log.warning("No se puede remover el nodo "+node1+" ya que no pertenece a la estructura");
		}
		if(!estanode2) {
			log.warning("No se puede remover el nodo "+node2+" ya que no pertenece a la estructura");
		}
		try {
			//Si los nodos pertenecen a la estructura, y existe un arco del node1 al node2 lo elimino
			if(estanode1 && estanode2) {
				boolean conectados = false;
				for(Integer e : grafomap.get(node1)) {
					if(e.equals(node2))
						conectados=true;
				}
				if(conectados) {
					grafomap.get(node1).remove(grafomap.get(node1).indexOf(node2));
					log.info("El arco que va desde el nodo "+node1+" hacia el nodo "+node2+" se elimino con exito de la estructura");
				}
				else {
					log.warning("No se puede remover el arco que va desde el nodo "+node1+" al nodo "+node2+" ya que no pertenece a la estructura");
				}
			}

		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

	}


}

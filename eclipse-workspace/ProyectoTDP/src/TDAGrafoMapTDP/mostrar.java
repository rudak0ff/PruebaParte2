package TDAGrafoMapTDP;

public class mostrar {

	public static void main(String[] args) {
       
		Graph grafito = new Graph();
        
        //Se hace prueba del metodo addNode
        
        grafito.addNode(1);
        grafito.addNode(2);
        grafito.addNode(3);
        grafito.addNode(4);
        grafito.addNode(5);
        grafito.addNode(5);
        grafito.addNode(5);
        grafito.addNode(5);
        grafito.addNode(4);
        
        //Se hace prueba del metodo addEdge
        
        grafito.addEdge(1,2);
        grafito.addEdge(1,4);
        grafito.addEdge(2,4);
        grafito.addEdge(3,1);
        grafito.addEdge(12,2);
        grafito.addEdge(1,12);
        grafito.addEdge(13,14);
        grafito.addEdge(12,12);
        
        //Se hace prueba del metodo removeEdge
        
        grafito.removeEdge(2, 4);
        grafito.removeEdge(8, 10);
        grafito.removeEdge(5, 14);
        grafito.removeEdge(12, 8);
        grafito.removeEdge(4, 5);
     
        //Se hace prueba del metodo removeNode
        
        grafito.removeNode(4);
        grafito.removeNode(5);
        grafito.removeNode(3);
        grafito.removeNode(2);
        grafito.removeNode(24);
        grafito.removeNode(12);
        grafito.removeNode(10);
        grafito.removeNode(3);
	}
	
}

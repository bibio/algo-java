import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;


public class Graph {
  public Set<Vertex> vs;
  public Map<Vertex, List<Edge>> es;

  public Graph() {
	this.vs = new HashSet<Vertex>();
	this.es = new HashMap<Vertex, List<Edge>>();
  }

  public List<Edge> getEdges(Vertex v) {
	return es.get(v);
  }
  public Vertex getVertex(String key) {
	for ( Vertex vs : this.vs ) {
	  if (vs.key.equals(key) )
		return vs;
	}
	return null;
  }
}

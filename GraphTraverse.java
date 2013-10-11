import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class GraphTraverse {

  /* GFS */
  /* traverse with recursive */
  boolean find(Graph g, Vertex start, Vertex goal) {
	System.out.println("find:Start=" + start + ",Goal=" + goal);
	return dfs(g, start, goal, new HashSet<Vertex>());
  }

  boolean dfs(Graph g, Vertex v, Vertex goal, Set<Vertex>visited) {
	if (v.equals(goal)) { /* found!! */ return true; }

	/* check infinity loop */
	if (visited.contains(v)) { return false; }
	visited.add(v);

	/* traverse all edges */
	List<Edge> edges = g.getEdges(v);
	for ( int i = 0; i < edges.size(); i++ ) {
	  Edge edge = edges.get(i);
	  if (dfs(g, edge.e, goal, visited)) { return true; }
	}
	return false;
  }

  /* traverse without recursive */
  boolean iter(Graph g, Vertex start, Vertex goal) {
	Set<Vertex> visited = new HashSet<Vertex>();
	Stack<Vertex> q = new Stack<Vertex>(); /* LIFO */

	System.out.println("iter:Start=" + start + ",Goal=" + goal);

	/* add stack */
	q.add(start);

	while (!q.isEmpty()) {
	  Vertex v = q.pop();

	  if (visited.contains(v)) { continue; }

	  System.out.print(v+"->");
	  if (v.equals(goal)) { 
		System.out.println("[end]");
		return true; 
	  }
	  visited.add(v);

	  List<Edge> edges = g.getEdges(v);
	  for ( Edge e : edges ) {
		if (visited.contains(e.e)) { continue; }
		q.add(e.e);
	  }
	}

	/* Not found!! */
	System.out.println("[end]");
	return false;
  }

  /* BFS */
  public boolean bfs(Graph g, Vertex start, Vertex goal) {
	Set<Vertex> visited = new HashSet<Vertex>();
	Queue<Vertex> q = new LinkedList<Vertex>(); /* FIFO */
	q.add(start);
	System.out.println("bfs:Start="+start+",Goal="+goal);

	while( !q.isEmpty() ) {
	  Vertex v = q.poll(); /* pickup first element and remove from queue */
	  if (visited.contains(v)) { continue; }
	  System.out.print(v+"->");
	  if (v.equals(goal)) {
		System.out.println("[end]");
		return true;
	  }
	  visited.add(v);
	  List<Edge> edges = g.getEdges(v);
	  for ( Edge e : edges ) {
		if (visited.contains(e.e)) { continue; }
		q.add(e.e);
	  }
	}
	System.out.println("[end]");
	return false;
  }

  /* test */

  /* generate test data */
  Graph getGraph() {
	Graph g = new Graph();

	/* Vertex */
	Vertex va,vb,vc,vd,ve,vf,vg;
	g.vs.add(va = new Vertex("A"));
	g.vs.add(vb = new Vertex("B"));
	g.vs.add(vc = new Vertex("C"));
	g.vs.add(vd = new Vertex("D"));
	g.vs.add(ve = new Vertex("E"));
	g.vs.add(vf = new Vertex("F"));
	g.vs.add(vg = new Vertex("G"));

	/* Edges */
	ArrayList<Edge> list;

	list = new ArrayList<Edge>();
	list.add(new Edge(va,vb));
	list.add(new Edge(va,ve));
	list.add(new Edge(va,vg));
	g.es.put(va, list);
	/* */
	list = new ArrayList<Edge>();
	list.add(new Edge(vb, vc));
	list.add(new Edge(vb, vd));
	g.es.put(vb, list);
	/* */
	list = new ArrayList<Edge>();
	g.es.put(vc, list);
	/* */
	list = new ArrayList<Edge>();
	g.es.put(vd, list);
	/* */
	list = new ArrayList<Edge>();
	list.add(new Edge(ve, vd));
	list.add(new Edge(ve, vf));
	g.es.put(ve, list);
	/* */
	list = new ArrayList<Edge>();
	list.add(new Edge(vf, vd));
	g.es.put(vf, list);
	/* */
	list = new ArrayList<Edge>();
	g.es.put(vg, list);
	return g;
  }

  GraphTraverse(String start, String goal) {
	Graph g = getGraph();
	boolean ret;
	ret = this.find(g, g.getVertex(start), g.getVertex(goal));
	System.out.println("ret="+ret);

	ret = this.iter(g, g.getVertex(start), g.getVertex(goal));
	System.out.println("ret="+ret);

	ret = this.bfs(g, g.getVertex(start), g.getVertex(goal));
	System.out.println("ret="+ret);
  }

  public static void main(String args[]) {
	GraphTraverse gt = new GraphTraverse(args[0], args[1]);
  }
}

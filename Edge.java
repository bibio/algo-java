/* o--o */
/* s  e */
public class Edge {
  public Vertex s;  // start vertex
  public Vertex e;  // end vertex
  public Edge(Vertex s, Vertex e) {
	this.s = s;
	this.e = e;
  }
  @Override
  public String toString() {
	StringBuffer str = new StringBuffer();
	str.append("s:"+s+"->"+"e:"+e);
	return str.toString();
  }
}

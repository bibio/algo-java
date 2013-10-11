import java.util.Comparator;

public class Index {
  public int x, y;
  public Index(int x, int y) {
	this.x = x;
	this.y = y;
  }
}

class IndexComparator implements Comparator<Index> {
  private Index goal;
  public IndexComparator(Index goal) {
	this.goal = goal;
  }
  @Override
  public int compare(Index lhs, Index rhs) {
	int r1 = Math.abs(lhs.y - goal.y) + Math.abs(lhs.x - goal.x);
	int r2 = Math.abs(rhs.y - goal.y) + Math.abs(rhs.x - goal.x);
	return r1 - r2;
  }
}

import java.util.PriorityQueue;
import java.util.Arrays;

public class Aastah {
  private final char[] DIR = new char[] { 'E', 'W', 'S', 'N' };
  private final int[] dx = new int[] { 1, -1, 0, 0 };
  private final int[] dy = new int[] { 0, 0, 1, -1 };
  private final char WALL = '#';

  public String solve(char[][] m, Index start, Index goal) {
	final int H = m.length, W = m[0].length;

	char[][] visited = new char[H][W];
	for ( int i = 0; i < H; i++ ) {
	  Arrays.fill(visited[i], ' ');
	}
	PriorityQueue<Index> q = new PriorityQueue<Index>(100, new IndexComparator(goal));
	q.add(start);

	while ( !q.isEmpty() ) {
	  Index idx = q.poll();
	  int x = idx.x, y = idx.y;

	  if ( x == goal.x && y == goal.y ) {
		return getPath(visited, start, goal);
	  }

	  /* Direction E,W,S,N */
	  for ( int i = 0; i < 4; i++ ) {
		int xx = x + dx[i], yy = y + dy[i];
		if ( 0 <= xx && xx < W && 0 <= yy && yy < H && visited[yy][xx] == ' ' && m[yy][xx] != WALL) {
		  visited[yy][xx] = DIR[i];
		  //System.out.println("x="+xx+",y="+yy+",v="+m[yy][xx]+",dir="+DIR[i]);
		  q.add(new Index(xx,yy));
		}
	  }
	}
	return null;
  }

  String getPath(char[][] m, Index start, Index goal) {
	StringBuilder builder = new StringBuilder();
	int x = goal.x, y = goal.y;
	while ( x != start.x || y != start.y ) {
	  builder.append(m[y][x]);
	  switch (m[y][x]) {
		case 'N': ++y; break; 
		case 'S': --y; break;
		case 'E': --x; break;
		case 'W': ++x; break;
		default: throw new RuntimeException();
	  }
	}
	return builder.reverse().toString();
  }

  void setMap(char[][] map) {
	System.arraycopy("#####S####".toCharArray(), 0, map[0], 0, 9);
	System.arraycopy("#........#".toCharArray(), 0, map[1], 0, 9);
	System.arraycopy("#.###.#..#".toCharArray(), 0, map[2], 0, 9);
	System.arraycopy("#...#.#..#".toCharArray(), 0, map[3], 0, 9);
	System.arraycopy("###.#.#..#".toCharArray(), 0, map[4], 0, 9);
	System.arraycopy("#...#.#..#".toCharArray(), 0, map[5], 0, 9);
	System.arraycopy("#.###.#..#".toCharArray(), 0, map[6], 0, 9);
	System.arraycopy("#...#.#..#".toCharArray(), 0, map[7], 0, 9);
	System.arraycopy("###.###..#".toCharArray(), 0, map[8], 0, 9);
	System.arraycopy("#.....#..#".toCharArray(), 0, map[9], 0, 9);
	System.arraycopy("#####G####".toCharArray(), 0, map[10], 0, 9);
  }

  Aastah() {
	char[][] map = new char[11][9];
	Index start = new Index(5,0);  /* S */
	Index goal = new Index(5,10);  /* G */
	setMap(map);
	System.out.println(solve(map, start,goal));
  }

  public static void main(String args[]) {
	new Aastah();
  }
}

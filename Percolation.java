/**
 * 
 * @author dare
 *
 */
public class Percolation {

	/** size of a row. */
	private int rowSize;
	/** the virtual top site. */
	private int top;
	private int bottom;
	/** number of sites. */
	private int size;
	/** to store blocked/open states. */
	private boolean[] grid;
	/** for quick union. */
	private WeightedQuickUnionUF gridUF;
	/** last row */
	private boolean percolated;
	
	/**
	 * 
	 * @param i row
	 * @param j column
	 * @throws Exception when i or j are in the wrong range.
	 */
	private void chkIndices(final int i, final int j) throws Exception {
		int m = i - 1;
		int n = j - 1;
		if (m < 0 || n < 0 || (m * rowSize + n) >= (rowSize * rowSize) || n > rowSize - 1) {
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 *  The constructor creates a percolation of N sites.
	 *  Creates a virtual top site & bottom site.
	 *  Initializes private variables.
	 * @param n the number of sites
	 */
	public Percolation(final int n) {
		size = n * n - 1;
		top = n * n;
		bottom= top + 1;
		rowSize = n;
		grid = new boolean [ n * n + 2 ];
		gridUF = new WeightedQuickUnionUF(n * n + 2);
		//create a virtual top
		for (int i = 0; i < rowSize; i++) { gridUF.union(top, i); }
		//create a virtual bottom
		//for (int i = size - rowSize + 1; i <= size; i++) { gridUF.union(bottom, i); }
	}
	/**
	 * 
	 * @param i row
	 * @param j column
	 * @return whether.
	 * @throws Exception calls chkIndices()
	 */
	public  boolean isFull(final int i, final int j) throws Exception {
		
		chkIndices(i, j);
		if (!isOpen(i, j)) { return false; }
		return gridUF.connected(top, (i - 1) * rowSize + (j - 1));
	}
	/**
	 * 
	 * @param i row 
	 * @param j column
	 * @return whether the site is open.
	 * @throws Exception calls chkIndices()
	 */
	public  boolean isOpen(final int i, final int j) throws Exception {
		chkIndices(i, j);
		return grid[(i - 1) * rowSize + (j - 1)];
	}
	/**
	 * see if the node is connected to the bottom
	 * @param i index
	 */
	private boolean bottomConnected(int i) { 
		for(int k=size - rowSize + 1; k <= size; k ++) {
			if (grid[k] && gridUF.connected(i, k))  { return true; }
		}
		return false;
	}
	/**
	 * Attaches the site to nearby open sites.
	 * @param i index of the site
	 */
	private void attach(final int i) throws Exception {
		//conditions
		boolean above, down, left, right;
		above = (i > rowSize - 1) && grid[ i - rowSize];
		down = (i <= size - rowSize) && grid[i + rowSize];
		left = (i != 0) && ((i - 1) % rowSize != rowSize - 1) && grid[i - 1];
		right = (i != size) && ((i + 1) % rowSize != 0) && grid[i + 1];
		
		//attaches the site using union
		if (above) { gridUF.union(i, i - rowSize); }//System.out.println("top connected"); }
		if (down)  { gridUF.union(i, i + rowSize); }//System.out.println("bottom connected"); }
		if (left)  { gridUF.union(i, i - 1); }//System.out.println("left connected"); }
		if (right) { gridUF.union(i, i + 1); }//System.out.println("right connected"); }
		
		//see if the attachment makes the system percolate.
		//int x = i / rowSize + 1;
		//int y = i % rowSize + 1;
		if (gridUF.connected(top, i) && bottomConnected(i)) { percolated=true; }
		//if (gridUF.connected(top, i) && gridUF.connected(bottom, i)) { percolated=true; }
		
	}
	
	/**
	 * Opens the site.
	 * @param i row 
	 * @param j column
	 * @throws Exception calls chkIndices()
 	 */
	public  void open(final int i, final int j) throws Exception {
		chkIndices(i, j);
		if (!isOpen(i, j)) { 
		    grid[(i - 1) * rowSize + (j - 1)] = true;	
		    attach((i - 1) * rowSize + (j - 1));
		}
	}
	/**
	 * 
	 * @return whether the system percolates
	 */
	public   boolean percolates() {
		return percolated;
	}
	

}

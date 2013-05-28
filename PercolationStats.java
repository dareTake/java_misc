
/**
 * 
 * @author dare
 *
 */
public class PercolationStats {

	/** the percolation object for the tests.*/
	private Percolation pr;
	/** array to store the  percolation threshold for each run.*/
	private double[] percThld;
	/** the number of experiments. */
	private int noExpr;
	/** mean of the thresholds.*/
	private double mean;
	/** the standard deviation. */
	private double deviation;
	/** constant for calculation. */
	private static final double ratio = 1.96;
	/**
	 * 
	 * @param args main arguments
	 */
	public static void main(final String[] args) {
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		Stopwatch st= new Stopwatch();
		try {
			PercolationStats percTest = new PercolationStats(n, t);
			System.out.println("mean   = " + percTest.mean());
			System.out.println("stddev = " + percTest.stddev());
			System.out.println("95% confidence level = " + percTest.confidenceLo() + ", " + percTest.confidenceHi());
			System.out.println("Time: " + st.elapsedTime());
			
		} catch (Exception e) {
			System.out.println("Error " + e.getStackTrace()[0]);
		}
	}
	/**
	 * 
	 * @param n number of rows; for a n*n matrix  
	 * @param t number of experiments.
	 * @throws Exception when either n or t is less than zero
	 */
	public PercolationStats(final int n, final int t) throws Exception {
		if (n <= 0 || t <= 0) { throw new java.lang.IllegalArgumentException(); }
		percThld = new double[t];
		noExpr = t;
		int count;
		int rndI, rndJ;

		//st.wait();
		for (int i = 0; i < t; i++) {
			count = 0;
			pr = new Percolation(n);
			while (!pr.percolates()) {
				rndI = StdRandom.uniform(1, n + 1);
				rndJ = StdRandom.uniform(1, n + 1);
				try {
					if (!pr.isOpen(rndI, rndJ)) { pr.open(rndI, rndJ); count++; }
				} catch (Exception e) {
					System.out.println("Error: " + e.toString());
				}
			}
			pr=null;
			percThld[i] = (double) (count / (double) (n * n));
		}
		
	}
	/**
	 * 
	 * @return mean of the thresholds.
	 */
	public double mean() {
		mean = StdStats.mean(percThld);
		return mean;
	}
	/**
	 * 
	 * @return the standard deviation
	 */
	public  double stddev() {
		deviation = StdStats.stddev(percThld);
		return deviation;
	}
	/**
	 * 
	 * @return .
	 */
	public double confidenceLo() {
		return (double) (mean - ((ratio  * deviation) / (double) Math.sqrt(noExpr)));
	}
	/**
	 * 
	 * @return .
	 */
	public double confidenceHi() {
		return (double) (mean + ((ratio * deviation) / (double) Math.sqrt(noExpr)));
	}
}

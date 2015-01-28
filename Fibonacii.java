/** Implementation of generation of nth Fibonacii number
 *  @author Sreesha
 */


public class Fibonacii {

	private static long startTime, endTime, elapsedTime;
	private static int phase = 0;

	public static int findPower(int x, int n, int p) {

		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return x;
		} else {

			int temp = (findPower(x, n / 2, p));
			if (n % 2 == 0) {

				return temp * temp;
			} else {

				return temp * temp * x;
			}

		}

	}

	public static long[][] findMatrixPower(long l, long p) {

		if (l == 0) {
			long[][] base = new long[2][2];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					base[i][j] = 1;
				}
			}
			return base;
		} else if (l == 1) {
			long[][] base = new long[2][2];
			base[0][0] = 1;
			base[0][1] = 1;
			base[1][0] = 1;
			base[1][1] = 0;
			return base;
		} else

		{

			if (l % 2 == 0) {

				long temp[][] = (findMatrixPower(l / 2, p));
				long[][] c = new long[2][2];
				long[][] result = new long[2][2];

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++)
							c[i][j] = (c[i][j] + (temp[i][k] * temp[k][j])) % p;
					}
				}
				return c;
			} else {
				long temp[][] = (findMatrixPower(l / 2, p));
				long[][] c = new long[2][2];
				long[][] result = new long[2][2];
				long[][] base = new long[2][2];
				base[0][0] = 1;
				base[0][1] = 1;
				base[1][0] = 1;
				base[1][1] = 0;

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++)
							c[i][j] = (c[i][j] + (temp[i][k] * temp[k][j])) % p;
					}
				}

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++)
							result[i][j] = (result[i][j] + (c[i][k] * base[k][j]))
									% p;
					}
				}

				return result;
			}

		}

	}

	public static long FiboRecursive(long n, int p) {

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 1;
		} else {
			return ((FiboRecursive((n - 1), p)) % p + (FiboRecursive((n - 2), p)) % p);
		}
	}
	
	public static long FiboIterative(long n,int p){
		
		 long prev1=0, prev2=1;
         for(int i=0; i<n; i++) {
             long savePrev1 = prev1;
             prev1 = prev2 % p;
             prev2 = (savePrev1 + prev2)% p;
         }
         return prev1 ;
	}

	public static long FiboDAC(long n, int p) {

		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 0;
		} else if (n == 2) {
			return 1;
		} else {
			long[][] res = (findMatrixPower(n - 1, p));

			return res[0][0];
		}
	}

	public static void timer() {
		if (phase == 0) {
			startTime = System.currentTimeMillis();
			phase = 1;
		} else {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime - startTime;
			phase = 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		timer();
		long res = FiboDAC(1326547894 	, 56417);
		long time1 = elapsedTime;
		timer();
		timer();
		long res2 = FiboIterative(1326547894  , 56417);
		timer();
		long time2 = elapsedTime;
		System.out.println(res + " "+" " + time2 + " " + time1);

	}
}

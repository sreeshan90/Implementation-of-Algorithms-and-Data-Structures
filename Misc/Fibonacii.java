/**
 * Solution to Swift Navigation Application Challenge
 * 
 * @author Sreesha Nagaraj
 */

public class SwiftNavigationChallenge {

	/**
	 * Method to check of input int is a Prime number
	 * 
	 * @param res
	 *            : input num
	 * @return : Boolean variable stating whether the input is a prime number or
	 *         not
	 */
	public static boolean isPrime(long res) {

		// skip all even numbers and only try up to the square root of the
		// number.
		if (res > 2 && res % 2 == 0 || res == 1) {

			return false;
		}

		int top = (int) Math.sqrt(res) + 1;

		for (int i = 2; i < top; i += 2) {
			if (res % i == 0) {

				return false;
			}
		}

		return true;
	}

	/**
	 * Method to calculate power of the matrix
	 * 
	 * @param num
	 *            : int - power
	 * @return : int[][] Matrix raised to given power
	 * 
	 */

	public static long[][] findMatrixPower(long pow) {

		if (pow == 0) {
			long[][] base = new long[2][2];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					base[i][j] = 1;
				}
			}
			return base;
		} else if (pow == 1) {
			long[][] base = new long[2][2];
			base[0][0] = 1;
			base[0][1] = 1;
			base[1][0] = 1;
			base[1][1] = 0;
			return base;
		} else

		{

			if (pow % 2 == 0) {

				long temp[][] = (findMatrixPower(pow / 2));
				long[][] c = new long[2][2];
				long[][] result = new long[2][2];

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++)
							c[i][j] = (c[i][j] + (temp[i][k] * temp[k][j]));
					}
				}
				return c;
			} else {
				long temp[][] = (findMatrixPower(pow / 2));
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
							c[i][j] = (c[i][j] + (temp[i][k] * temp[k][j]));
					}
				}

				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						for (int k = 0; k < 2; k++)
							result[i][j] = (result[i][j] + (c[i][k] * base[k][j]));

					}
				}

				return result;
			}

		}

	}

	/**
	 * Divide and Conquer method to calculate Fibonacii nummber
	 * 
	 * @param n
	 *            : long - nth fibonacci number
	 * @return : int[][] Matrix raised to given power
	 * 
	 */

	public static long FiboDAC(long n) {

		if (n == 0 || n < 0) {
			return -1;
		} else if (n == 1) {
			return 0;
		} else if (n == 2) {
			return 1;
		} else {
			long[][] res = (findMatrixPower(n - 1));

			return res[0][0];
		}
	}

	/**
	 *Main method of the class
	 * 
	 * @param args
	 *            : String[] - command params
	 * @return : void
	 * 
	 */
	public static void main(String[] args) {

		long res = FiboDAC(Integer.valueOf(args[0]));

		System.out.println(res);
		if (res % 3 == 0) {
			System.out.println("... Buzz");
		} else if (res % 5 == 0) {
			System.out.println("... Fizz");
		} else if (isPrime(res)) {
			System.out.println("... BuzzFizz");
		} else {
			System.out.println(res);
		}

	}

}

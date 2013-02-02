import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RecoverTheSequence {

	static Scanner in;
	static PrintStream out;

	public static void main(String[] args) throws FileNotFoundException {
		in = new Scanner(new File("in2.txt"));
		out = new PrintStream("out2.txt");
		int numTests = in.nextInt();
		in.nextLine();
		for (int t = 0; t < numTests; t++) {
			int n = in.nextInt();
			in.nextLine();
			String debug = in.nextLine();

			int[] sorted = new int[n];
			for (int i = 0; i < n; i++) {
				sorted[i] = i + 1;
			}

			String rem = mergeUnsort(sorted, new StringBuffer(debug).reverse()
					.toString());
			if (rem.length() > 0) {
				System.out.println("Something went wrong!");
			}

			int result = checksum(sorted);
			out.println(String.format("Case #%d: %d", t + 1, result));
		}
	}

	static String mergeUnsort(int[] sorted, String debug) {
		if (sorted.length == 1) {
			return debug;
		}

		System.out.println("Sorted: " + Arrays.toString(sorted));

		int[] left = new int[sorted.length / 2];
		int[] right = new int[(sorted.length + 1) / 2];

		int lCounter = left.length, rCounter = right.length;
		int strPointer = 0;
		List<String> mergeOptions = new ArrayList<>();
		while (lCounter > 0 || rCounter > 0) {
			if (lCounter == 0 || rCounter == 0) {
				mergeOptions.add(debug.substring(0, strPointer));
			}
			if (debug.charAt(strPointer++) == '1') {
				lCounter--;
				if (lCounter < 0)
					break;
			} else {
				rCounter--;
				if (rCounter < 0)
					break;
			}
		}

		String mergeStep = mergeOptions.get(0);
		System.out.println(mergeStep);
		lCounter = rCounter = 0;
		int i;
		for (i = 0; i < mergeStep.length(); i++) {
			if (debug.charAt(mergeStep.length() - i - 1) == '1') {
				left[lCounter++] = sorted[i];
			} else {
				right[rCounter++] = sorted[i];
			}
		}
		int copyLength = sorted.length - i;
		if (lCounter < left.length) {
			System.arraycopy(sorted, i, left, lCounter, copyLength);
		} else {
			System.arraycopy(sorted, i, right, rCounter, copyLength);
		}

		System.out.println("Split: " + Arrays.toString(left) + ", "
				+ Arrays.toString(right));

		String remDebug = mergeUnsort(right, debug.substring(mergeStep.length()));
		String remDebug2 = mergeUnsort(left, remDebug);

		System.arraycopy(left, 0, sorted, 0, left.length);
		System.arraycopy(right, 0, sorted, left.length, right.length);
		System.out.println("Unsorted: " + Arrays.toString(sorted));
		return remDebug2;
	}

	public static int checksum(int[] arr) {
		int result = 1;
		for (int i = 0; i < arr.length; i++) {
			result = 31 * result + arr[i] % 1000003;
		}
		return result;
	}
}

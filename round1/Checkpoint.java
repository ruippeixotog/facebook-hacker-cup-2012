import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Checkpoint {

	static Scanner in;
	static PrintStream out;

	public static void main(String[] args) throws FileNotFoundException {
		in = new Scanner(new File("in1.txt"));
		out = new PrintStream("out1.txt");
		int numTests = in.nextInt();
		in.nextLine();

		for (int t = 0; t < numTests; t++) {
			int s = in.nextInt();

			// num distinct paths -> shortest path
			Map<Integer, Integer> shortestPath = new HashMap<>();

			int n = 0;
			int[] pascal = { 1 };
			shortestPath.put(1, 1);

			int result = Integer.MAX_VALUE;
			boolean isFinished = false;

			if (s == 1) {
				result = 2;
				isFinished = true;
			}

			while (!isFinished) {
				int[] newPascal = new int[++n + 1];
				newPascal[0] = newPascal[n] = 1;
				for (int k = 1; k < n; k++) {
					newPascal[k] = pascal[k - 1] + pascal[k];
					int path = k + (n - k);

					int bestPath = shortestPath.containsKey(newPascal[k]) ? Math
							.min(path, shortestPath.get(newPascal[k])) : path;
					shortestPath.put(newPascal[k], bestPath);

					if (s % newPascal[k] == 0
							&& shortestPath.containsKey(s / newPascal[k])) {
						result = Math.min(result,
								bestPath + shortestPath.get(s / newPascal[k]));
					}

					if (newPascal[k] == s) {
						isFinished = true;
						break;
					}
				}
				pascal = newPascal;
			}
			out.println(String.format("Case #%d: %d", t + 1, result));
		}
	}
}

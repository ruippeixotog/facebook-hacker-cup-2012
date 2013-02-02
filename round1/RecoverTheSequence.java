import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class RecoverTheSequence {

	static Scanner in;
	static PrintStream out;

	public static void main(String[] args) throws FileNotFoundException {
		in = new Scanner(new File("in3.txt"));
		out = new PrintStream("out3.txt");
		int numTests = in.nextInt();
		in.nextLine();
		for (int t = 0; t < numTests; t++) {
			int result = 0;
			out.println(String.format("Case #%d: %d", t + 1, result));
		}
	}
}

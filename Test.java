import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Dumitrache Daniela Andreea
 *
 */
public class Test {
	public static void main(String[] args) throws IOException {
		/**
		 * fisierele de intrare si de iesire
		 */
		FileReader in_file = new FileReader("test.in");
		FileWriter out_file = new FileWriter("test.out");
		/**
		 * construim o transformare pe baza fisierului de intrare dat
		 */
		Construct_Transform constr = new Construct_Transform();
		constr.create_Transform(in_file, out_file);
		in_file.close();
		out_file.close();
	}
}

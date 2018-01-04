import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CN {

	public static void main(String[] args) throws IOException {
		System.out.println("Name der Datei: ");
		Scanner sc = new Scanner(System.in);
		String filename = sc.next();
		String name = decode(getSplitStringOfFile(filename, 0));

		String passwort = decode(getSplitStringOfFile(filename, 1));

		String notiz = getContentOfFile(filename);
		System.out.println("Nutzername: " + name);
		System.out.println("Passwort: " + passwort);
		System.out.println("Notiz: " + notiz);

		sc.close();
	}

	public static String decode(String toDecode) {
		String encoded = "";
		if (!(toDecode == null || toDecode.equals(""))) {
			for (String parseInt : toDecode.split((toDecode.split("l").length * 13) + "l")) {
				encoded = encoded + ((char) Integer.parseInt(parseInt));
			}
		}
		return encoded;
	}

	public static String getSplitStringOfFile(String Filename, int pos/* , Context c */) throws IOException {
		if (Filename != null/* && checkFile(Filename, c) */) {
			// String s = readFile(Filename, c);

			String s;
			BufferedReader br = new BufferedReader(new FileReader(Filename));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				s = sb.toString();
			} finally {
				br.close();
			}

			if (!(s == null || s.indexOf("#") == -1)) {
				String[] parts = s.split("#");
				if (parts.length > pos) {
					return parts[pos];
				}
			}
		}
		return null;
	}

	public static String getContentOfFile(String Filename/* , Context c */) throws IOException {
		String part = getSplitStringOfFile(Filename, 2/* , c */);
		if (part == null) {
			return "";
		}
		String s = "";
		for (String decode : part.split("n")) {
			s = s + decode(decode) + "\n";
		}
		return s;
	}

}

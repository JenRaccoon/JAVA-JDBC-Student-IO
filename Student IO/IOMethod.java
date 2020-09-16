package javahw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IOMethod {
	//讀取資料
	public String[] FileInput(String filename) throws IOException {
		
		ArrayList<String> lines = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(filename));
		while (br.ready()) {
			String line = br.readLine();
			if (line.charAt(0) == '#')
				continue;
			lines.add(line);
		}
		return lines.toArray(new String[lines.size()]);

	}
}

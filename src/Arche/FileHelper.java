package Arche;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHelper {
	
	//Datei einlesen und in eine Array-List übernehmen
	public static ArrayList<String> readFile(String data) {
		File file = new File(data);
		ArrayList<String> fileList = new ArrayList<>();
		
		//falls Datei nicht vorhanden
		if(!file.canRead() || !file.isFile()) {
			System.out.println("Could not read file: " + data);
			System.exit(0);
		}
		
		BufferedReader in = null;
		
		//Zeile für Zeile einlesen
		try {
			in = new BufferedReader(new FileReader(data));
			String zeile = null;
			while((zeile = in.readLine()) != null) {
				//System.out.println("Gelesene Zeile:" + zeile);
				fileList.add(zeile);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		
		return fileList;
	}
}

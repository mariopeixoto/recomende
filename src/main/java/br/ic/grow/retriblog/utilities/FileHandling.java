package br.ic.grow.retriblog.utilities;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileHandling {

	boolean saveFileString(String fileName, String message, boolean add){//add = false - sobescreve
		FileWriter writer;
		try {
			writer = new FileWriter(fileName, add);
			PrintWriter out = new PrintWriter(writer);
			out.println(message);  
			out.close();  
			writer.close(); 
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	boolean saveFileStringUtf(String fileName, String message, boolean add){//add = false - sobescreve
//		FileWriter writer;
		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8");
//			writer = new FileWriter(fileName, add);
//			PrintWriter out = new PrintWriter(writer,"UTF-8");
			out.write(message);  
			out.close();  
//			writer.close(); 
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	boolean saveFileInputstream(String fileName, String oldDtd, String newDtd, InputStream message, boolean add){//add = false - sobescreve
		FileWriter writer;
		BufferedReader reader = new BufferedReader(new InputStreamReader(message));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	message.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int firstIndex = sb.indexOf(oldDtd);
        int lastIndex = sb.indexOf("\">", firstIndex);
        sb.replace(firstIndex, lastIndex, newDtd);
        
		try {
			writer = new FileWriter(fileName, add);
			PrintWriter out = new PrintWriter(writer);
			out.println(sb);  
			out.close();  
			writer.close(); 
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	

	boolean changeDtdFile(String oldDtd, String newDtd, String fileName) throws IOException {//  \\ - esse caractere da problema
		FileHandling fh = new FileHandling();
		StringBuffer bufOut = new StringBuffer();
		FileReader reader = new FileReader(fileName);
		BufferedReader in = new BufferedReader(reader);
		String line= null; 
		String ondFile= null; 
		String newFile = null;
		line = in.readLine();
		while((line = in.readLine())!=null) {  
			bufOut.append(line + "\n");   
		}  
		in.close();  
		reader.close(); 
		ondFile = bufOut.toString();
		newFile = ondFile.replace (oldDtd , newDtd);
		if(fh.saveFileString(fileName,newFile,false)){
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean changeDtdFileInputstream(String oldDtd, String newDtd, String fileName) throws IOException {//  \\ - esse caractere da problema
		FileHandling fh = new FileHandling();
		StringBuffer bufOut = new StringBuffer();
		FileReader reader = new FileReader(fileName);
		BufferedReader in = new BufferedReader(reader);
		String line= null; 
		String ondFile= null; 
		String newFile = null;
		line = in.readLine();
		while((line = in.readLine())!=null) {  
			bufOut.append(line + "\n");   
		}  
		in.close();  
		reader.close(); 
		ondFile = bufOut.toString();
		newFile = ondFile.replace (oldDtd , newDtd);
		if(fh.saveFileString(fileName,newFile,false)){
			return true;
		}
		else{
			return false;
		}
	}

}

package br.ic.grow.retriblog.utilities;

import java.io.InputStream;

import br.ic.grow.retriblog.textExtraction.TextExtraction;



public interface Utilities {

	public String getPost(String url, String first, String last, TextExtraction textExtraction);
	public String getBlogText(String uri, String excerpt, TextExtraction textExtraction);
	public boolean saveFileString(String fileName, String message, boolean add);
	public boolean saveFileStringUtf(String fileName, String message, boolean add);
	public boolean saveFileInputstream(String fileName, String oldDtd, String newDtd, InputStream message, boolean add);
	public boolean changeDtdFile(String oldDtd, String newDtd, String fileName);
	public boolean changeDtdFileInputstream(String oldDtd, String newDtd, String fileName);
	public String languageDetect (String text);
	public String cleanSpace(String text);
	public String getPage(String url);
	public void savePage(String url, String oldDtd, String newDtd);
	public String convertStreamToString(InputStream text);
	public Object loadXML( String src, boolean validate);
}

package bherma3;

import java.io.Serializable;
import java.util.UUID;

public final class Document implements Serializable {
	
	private String Author;
	private String DocumentUUID;
	private String password;
	private String Text;

	public String getUUID() {
		return DocumentUUID;
	}
	public String getText() {
		return Text;
	}
	public String getAuthor() {
		return Author;
	}
	public Document(String text, String autor){
		UUID generated = null;
		generated=UUID.randomUUID();
		DocumentUUID = generated.toString();
		Author = autor;
		Text=text;
	}
	
}

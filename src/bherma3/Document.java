package bherma3;

import java.io.Serializable;

public final class Document implements Serializable {
	
	private	 String UUID;
	private String Author;
	private String DocumentUUID;
	private String password;
	public String getID() {
		return ID;
	}
	public String getText() {
		return Text;
	}
	public String getAuthor() {
		return Author;
	}
	public Document(String i, String t, String a){
		ID=i;
		Text = t;
		Author = a;
	}
	
}

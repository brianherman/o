package bherma3;

import java.io.Serializable;

public final class DocumentDistributedObject implements Serializable {
	private	 String ID;
	private String Text;
	private String Author;
	public String getID() {
		return ID;
	}
	public String getText() {
		return Text;
	}
	public String getAuthor() {
		return Author;
	}
	public DocumentDistributedObject(String i, String t, String a){
		ID=i;
		Text = t;
		Author = a;
	}
	
}

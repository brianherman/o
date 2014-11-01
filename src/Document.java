package test2;

import java.io.Serializable;

public class Document implements Serializable{
private String ID;
private String Content;
public Document(String idInfo, String text){
	ID=idInfo;
	Content = text;
}
public String content(){
	return Content;
}
public void content(String s){
	Content = s;
}
public String id(){
	return ID;
}
public String toString(){
	return ID+
		   "\n====================\n"+
		   Content+ "\n\n\n";
}
}

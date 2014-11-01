package bherma3;

import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
 
 
public class TestDocument {
 
    public static void main(String[] args) {
        Session session = SessionFactoryUtil.getSessionFactory().getCurrentSession();
 
        session.beginTransaction();
 
        createPerson(session);
 
        queryPerson(session);
 
    }
 
    private static void queryDocument(Session session) {
        Query query = session.createQuery("from Document");                 
        List <Document>list = query.list();
        java.util.Iterator<Document> iter = list.iterator();
        while (iter.hasNext()) {
 
            Person person = iter.next();
            System.out.println("Person: \"" + person.getName() +"\", " + person.getSurname() +"\", " +person.getAddress());
 
        }
 
        session.getTransaction().commit();
 
    }
 
    public static void createPerson(Document session) {
        Document person = new Document();
 
//        person.setName("Barak");
//        person.setSurname("Obhama");       
//        person.setAddress("White House");       
 
        session.save(person);
    }
}

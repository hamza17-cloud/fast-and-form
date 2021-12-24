package fr.utbm.da50.fastandform.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralRepository {
 // private static final String DatabaseName = "fastandform";
  //private static final String CollectionName = "users";

  @Autowired
  private MongoClient client;
  private MongoTemplate mongoTemplate;

  public List<String> findAllDocuments(String DatabaseName, String CollectionName) {
    final List<String> list = new ArrayList<>();
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
    data.find().map(Document::toJson).forEach(list::add);
    return list;
}

  public String findOneDocumentById(String DatabaseName, String CollectionName, String id) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
    System.out.println("hahahah");
    Document myDoc = data.find(eq("id", id)).first();
    return myDoc.toJson();
}
public DeleteResult DeleteID(String DatabaseName, String CollectionName, String firstName) {

  System.out.println("hahahah");
  Query query = new Query(); 
  query.addCriteria(Criteria.where("firstName").is(firstName));
  boolean dataExists = mongoTemplate.exists(query, "Users");
  System.out.println("ok");
  System.out.println(dataExists); //true 
DeleteResult hah = mongoTemplate.remove(query , CollectionName); 
  return hah;
}
/*
public void DeleteaDocumentbyId(String DatabaseName, String CollectionName, String id) {
  final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
  Document myDoc= collection.deleteOne(id:?);
   return myDoc.toJson();

}
*/
public void deleteSomething( String firstName,String CollectionName ) {

 
   Query query = new Query(); 
query.addCriteria(Criteria.where("firstName").is("toto"));
boolean dataExists = mongoTemplate.exists(query, "Users");
System.out.println("hahahah");
System.out.println(dataExists); //true 
mongoTemplate.remove(query , CollectionName); 


}
}

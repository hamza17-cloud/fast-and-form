
package fr.utbm.da50.fastandform.core.repository;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.mongodb.client.model.Filters;

@Repository
public class WriteRepository{
    
  @Autowired
  private MongoClient client;

  public List<String> findAllDocuments(String DatabaseName, String CollectionName) {
    final List<String> list = new ArrayList<>();
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
    data.find().map(Document::toJson).forEach(list::add);
    return list;
  }
  
  public void SaveOneDocument(String DateBaseName, String CollectionName, HashMap<String, Object> map){
    final MongoCollection<Document> data = client.getDatabase(DateBaseName).getCollection(CollectionName);
    data.insertOne((new Document(map)));
  }

  public void UpdateOneDocument(String DateBaseName, String CollectionName, HashMap<String, Object> map,String id,String key){
    final MongoCollection<Document> data = client.getDatabase(DateBaseName).getCollection(CollectionName);
    Document myDoc = data.find(eq(key, id)).first();

    // if( myDoc == null){
    //   data.insertOne((new Document(map)));
    // } 
    // data.updateOne(Filters.eq(key, id), myDoc);
  }

}
package fr.utbm.da50.fastandform.core.repository;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralRepository {
  // private static final String DatabaseName = "fastandform";
  // private static final String CollectionName = "users";

  @Autowired
  private MongoClient client;

  public List<String> findAllDocuments(String DatabaseName, String CollectionName) {
    final List<String> list = new ArrayList<>();
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
    data.find().map(Document::toJson).forEach(list::add);
    return list;
  }

  public String findOneDocumentById(String DatabaseName, String CollectionName, String id) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
    Document myDoc = data.find(eq("id", id)).first();
    return myDoc.toJson();
  }

  public List<String> findOneDocumentBy(String DatabaseName, String CollectionName, String key, String val) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);

    FindIterable<Document> docs = data.find(eq(key, val));
    List<String> list = new ArrayList<>();
    for (Document document : docs) {

      list.add(document.toJson());
    }
    return list;
  }
}

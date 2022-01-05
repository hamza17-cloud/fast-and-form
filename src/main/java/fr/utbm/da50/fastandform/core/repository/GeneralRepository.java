package fr.utbm.da50.fastandform.core.repository;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

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

  public void DeleteOneDocByspecificID(String DatabaseName, String CollectionName, String id) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);

    try {
      data.deleteOne(new Document("id", id));
      long count = data.countDocuments();
      System.out.println(count);

    } catch (Exception e) {
      System.out.println(e);

    }

  }

  public void DeleteManyDocMatchingSpecificID(String DatabaseName, String CollectionName, String username) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);

    try {
      data.deleteMany(new Document("username", username));
      long count = data.countDocuments();
      System.out.println(count);

    } catch (Exception e) {
      System.out.println(e);

    }

  }

  public void DeleteBy(String DatabaseName, String CollectionName, String key, String valeur,
      Map<String, String> dataQuery) {
    // public void DeleteBy(String DatabaseName, String CollectionName, String key,
    // String val) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);

    try {

      // Document participants = new Document();
      // participants.append(name, null);
      // Document username = new Document();
      // username.append(String.format("username.%s", key), null);

      Document document = new Document();
      // Collection<String> valeur = dataQuery.values();
      document.append("$unset", dataQuery);
      // document.append("$unset", valeur);
      // document.append("$unset", );

      UpdateResult result = data.updateOne(Filters.eq(key, valeur), document);
      if (result.getMatchedCount() != 1) {
        throw new IllegalStateException(
            String.format("Error occurred while deleting"));
      }
    } catch (RuntimeException error) {

    }

  }

  public void DeleteMatchingRecordsBy(String DatabaseName, String CollectionName, String key, String valeur,
      Map<String, String> dataQuery) {
    // public void DeleteBy(String DatabaseName, String CollectionName, String key,
    // String val) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);

    try {

      Document document = new Document();
      document.append("$unset", dataQuery);

      UpdateResult result = data.updateMany(Filters.eq(key, valeur), document);
      if (result.getMatchedCount() != 1) {
        throw new IllegalStateException(
            String.format("Error occurred while deleting"));
      }
    } catch (RuntimeException error) {

    }

  }

  public List<String> findListDocumentBy(String DatabaseName, String CollectionName, String key, String val) {
    final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);

    FindIterable<Document> docs = data.find(eq(key, val));
    List<String> list = new ArrayList<>();
    for (Document document : docs) {

      list.add(document.toJson());
    }
    return list;
  }

}

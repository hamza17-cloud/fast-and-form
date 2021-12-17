package fr.utbm.da50.fastandform.core.repository;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GeneralRepository {
  private static final String DatabaseName = "fastandform";
  private static final String CollectionName = "users";

  @Autowired
  private MongoClient client;

  public List<String> allDocuments() {
      final List<String> list = new ArrayList<>();
      final MongoCollection<Document> data = client.getDatabase(DatabaseName).getCollection(CollectionName);
      data.find().map(Document::toJson).forEach(list::add);
      return list;
  }
}

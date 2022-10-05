package fr.utbm.da50.fastandform.core.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.utbm.da50.fastandform.core.FastAndFormSettings;
import fr.utbm.da50.fastandform.core.entity.EntityTemplate;
import fr.utbm.da50.fastandform.core.repository.GeneralRepository;
import fr.utbm.da50.fastandform.core.repository.WriteRepository;

@Service
public class EntityService {

  private static HashMap<String, EntityTemplate> entities;

  public EntityService() {
    // TODO document why this constructor is empty
  }

  @Autowired
  private GeneralRepository generalRepository;

  @Autowired
  private WriteRepository writerep;

  @Autowired
  FastAndFormSettings fastAndFormSettings;

  // You cannot load entities inside the constructor because Spring didn't
  // assigned the beans yet. When you need, is call a mathod after,
  // hopefully javax Post construct annotaion is called after the autowire
  // https://stackoverflow.com/questions/44681142/postconstruct-annotation-and-spring-lifecycle/44681477
  @PostConstruct
  public void postConstruct() throws Exception {
    this.loadEntities();
  }

  public ArrayList<String> getEntitiesLocation() {
    return fastAndFormSettings.getEntitiesLocation();
  }

  public void loadEntities() throws Exception {
    if (entities != null)
      return;

    HashMap<String, EntityTemplate> result = new HashMap<>();

    EntityTemplate tmp;
    for (String location : getEntitiesLocation()) {
      tmp = loadEntity(location);
      result.put(tmp.getName(), tmp);
    }
    entities = result;
    System.out.println(entities);
  }

  public static EntityTemplate loadEntity(String location) throws Exception {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader(location));
    EntityTemplate result = gson.fromJson(reader, EntityTemplate.class);
    return result;
  }

  public EntityTemplate getEntityByName(String entityName) throws Exception {
    loadEntities();

    EntityTemplate result = entities.get(entityName);

    if (result == null)
      throw new Exception("Could not find entity " + entityName);

    return result;
  }

  public String findAllDocuments(String DatabaseName, String CollectionName) {

    return generalRepository.findAllDocuments(DatabaseName, CollectionName).toString();
  }

  public String findOneDocumentById(String DatabaseName, String CollectionName, String id) {
    return generalRepository.findOneDocumentById(DatabaseName, CollectionName, id).toString();
  }

  public void DeleteOneDocByspecificID(String DatabaseName, String CollectionName, String id) {
    generalRepository.DeleteOneDocByspecificID(DatabaseName, CollectionName, id);
  }

  public void DeleteByRecord(String DatabaseName, String CollectionName, String k, String v,
      Map<String, String> dataQuery) {
    generalRepository.DeleteBy(DatabaseName, CollectionName, k, v, dataQuery);
  }

  public void DeleteByManyRecords(String DatabaseName, String CollectionName, String k, String v,
      Map<String, String> dataQuery) {
    generalRepository.DeleteMatchingRecordsBy(DatabaseName, CollectionName, k, v, dataQuery);
  }

  public void DeleteManyDocMatchingSpecificID(String DatabaseName, String CollectionName, String id) {
    generalRepository.DeleteManyDocMatchingSpecificID(DatabaseName, CollectionName, id);
  }

  public void SaveDocument(String DatabaseName, String CollectionName, HashMap<String, Object> mappingData) {
    writerep.SaveOneDocument(DatabaseName, CollectionName, mappingData);
  }

  public String findListDocumentBy(String DatabaseName, String CollectionName, String n, String v) {
    return generalRepository.findListDocumentBy(DatabaseName, CollectionName, n, v).toString();
  }

  public void UpdateOneDocument(String DatabaseName, String CollectionName, HashMap<String, Object> map,
      String id, String key) {
    writerep.UpdateOneDocument(DatabaseName, CollectionName, map, id, key);
  }

}

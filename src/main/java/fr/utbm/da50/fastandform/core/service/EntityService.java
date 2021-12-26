package fr.utbm.da50.fastandform.core.Service;

import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.utbm.da50.fastandform.core.FastAndFormSettings;
import fr.utbm.da50.fastandform.core.entity.EntityTemplate;
import fr.utbm.da50.fastandform.core.repository.GeneralRepository;

@Service
public class EntityService {
  @Autowired
  private GeneralRepository generalRepository;
  private static String[] entitiesLocation;

  private static HashMap<String, EntityTemplate> entities;

  public static void setEntitiesLocation(String values) {
    if (entitiesLocation == null) {
      entitiesLocation = values.split(",");
    }
  }

  public static String[] getEntitiesLocation() {
    return entitiesLocation;
  }

  public static void loadEntities() throws Exception {
    if (entities != null)
      return;

    HashMap<String, EntityTemplate> result = new HashMap<>();

    if (entitiesLocation == null) {
      entitiesLocation = FastAndFormSettings.getInstance().getEntitiesLocation();
    }

    EntityTemplate tmp;
    for (String location : entitiesLocation) {
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

  public EntityService() {
  }

  public String findAllDocuments(String DatabaseName, String CollectionName) {

    return generalRepository.findAllDocuments(DatabaseName, CollectionName).toString();
  }

  public String findOneDocumentById(String DatabaseName, String CollectionName, String id) {
    return generalRepository.findOneDocumentById(DatabaseName, CollectionName, id).toString();
  }
  public String DeleteOneDocByspecificID(String DatabaseName, String CollectionName, String id) {
    return generalRepository.DeleteOneDocByspecificID(DatabaseName, CollectionName, id).toString();
  }
  public String DeleteManyDocMatchingSpecificID(String DatabaseName, String CollectionName, String id) {
    return generalRepository.DeleteManyDocMatchingSpecificID(DatabaseName, CollectionName, id).toString();
  }
  


  public String findOneDocumentBy(String DatabaseName, String CollectionName, String n, String v) {
    return generalRepository.findOneDocumentBy(DatabaseName, CollectionName, n, v).toString();
  }
  
}

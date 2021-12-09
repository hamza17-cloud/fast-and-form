package fr.utbm.da50.fastandform.core.service;
import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import fr.utbm.da50.fastandform.core.entity.EntityTemplate;

public class EntityService {

  private static String[] entitiesLocation;
  
  public static String[] setEntitiesLocation(String[] values) {
    if(entitiesLocation != null) return entitiesLocation;
    entitiesLocation = values;

    return entitiesLocation;
  }

  private static HashMap<String, EntityTemplate> entities;

  public static void loadEntities() throws Exception {
    if(entities != null) return;

    HashMap<String, EntityTemplate> result = new HashMap<>();
    
    EntityTemplate tmp;
    for(String location : entitiesLocation) {
      tmp = loadEntity(location);
      result.put(tmp.getName(), tmp);
    }
    entities = result;
  } 

  public static EntityTemplate loadEntity(String location) throws Exception {
    Gson gson = new Gson();
    JsonReader reader = new JsonReader(new FileReader(location));
    EntityTemplate result = gson.fromJson(reader, EntityTemplate.class);
    System.out.println(result);
    return result;
  }

  public static EntityTemplate getEntityByName(String entityName) throws Exception {
    loadEntities();

    EntityTemplate result = entities.get(entityName);

    if(result == null) throw new Exception("Could not find entity " + entityName);

    return result;
  }

  public EntityService() {
  }
}

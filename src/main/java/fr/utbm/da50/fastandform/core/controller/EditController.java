package fr.utbm.da50.fastandform.core.controller;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.utbm.da50.fastandform.core.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.utbm.da50.fastandform.core.service.EntityService;
import fr.utbm.da50.fastandform.core.service.VerifyService;

@RestController
class EditController {

    @Autowired
    private VerifyService verifservice;
    @Autowired
    private EntityService entityservice;


    @GetMapping(value = "/{entity}/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String resultctr(@PathVariable String entity,@PathVariable Integer id ) {
        return "L'entité est : " + entity + ": "+ id;
    }
    
    //http://localhost:8080/fastandform/users
    @PostMapping(value = "/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody JsonNode jsonNode,@PathVariable String DatabaseName,@PathVariable String CollectionName) throws Exception {

        String data = jsonNode.toString();
        
        // transform the json request body in hashmap
        HashMap<String, Object> mappingData = JsonToHashMap(data);

        //verify the data integriy
        String res =  verifservice.verif(mappingData,CollectionName);

        //Write the data in Database
        if("ok".equals(res)){
          entityservice.SaveDocument(DatabaseName, CollectionName, mappingData);
        }

        return res;
    }

    //http://localhost:8080/fastandform/users/xsara?type=name
    @PatchMapping(value = "/{DatabaseName}/{CollectionName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody JsonNode jsonNode,@PathVariable String DatabaseName, 
    @PathVariable String CollectionName, @PathVariable String id, @RequestParam String key) throws Exception{

        String data = jsonNode.toString();        
      // transform the json request body in hashmap
        HashMap<String, Object> mappingData = JsonToHashMap(data);

        //verify the data integriy
        String res =  verifservice.verif(mappingData,CollectionName);

        //Write the data in Database
        if("ok".equals(res)){
          entityservice.UpdateOneDocument(DatabaseName, CollectionName, mappingData,id, key);
        }

        return res;
    }

    //Convert Json to Hashmap
  public HashMap<String, Object> JsonToHashMap(String data) throws Exception {
    TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
    };
    HashMap<String, Object> mapping = new ObjectMapper().readValue(data, typeRef);
    return mapping;
  }

}
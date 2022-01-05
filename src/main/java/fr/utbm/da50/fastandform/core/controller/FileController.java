package fr.utbm.da50.fastandform.core.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.utbm.da50.fastandform.core.repository.GeneralRepository;
import fr.utbm.da50.fastandform.core.service.EntityService;

@RestController
class FileController {

  @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  String result() {
    return "Hello world";
  }

  @Autowired
  private GeneralRepository generalRepository;
  @Autowired
  private EntityService entityService;

  // exemple : http://localhost:8080/fastandform/users
  @GetMapping(value = "/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String findAll(@PathVariable String DatabaseName, @PathVariable String CollectionName) {
    return entityService.findAllDocuments(DatabaseName, CollectionName).toString();
    // return generalRepository.findAllDocuments(DatabaseName,
    // CollectionName).toString();
  }

  // exemple : http://localhost:8080/fastandform/users/207471947662098432
  @GetMapping(value = "/{DatabaseName}/{CollectionName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String findById(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String id) {
    return entityService.findOneDocumentById(DatabaseName, CollectionName, id).toString();
    // return generalRepository.findOneDocumentById(DatabaseName, CollectionName,
    // id).toString();
  }

<<<<<<< HEAD
//exp : it will delete the first matching id in the url
//http://localhost:8080/delete/fastandform/users/TheRolf
  @DeleteMapping(value = "/delete/{DatabaseName}/{CollectionName}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void DeleteOneDocByusername(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String username) {
   entityService.DeleteOneDocByspecificID(DatabaseName, CollectionName, username);

  }
  // exp: if we have two matching usernames  in different docs it will delete both
  //http://localhost:8080/Delete/fastandform/users/207471947662098432
  //P.S:To delete all documents in a collection, pass in an empty document ({ })
  @DeleteMapping(value = "/Delete/{DatabaseName}/{CollectionName}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void DeleteManyDocByusername(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String username) { 
 entityService.DeleteManyDocMatchingSpecificID(DatabaseName, CollectionName, username);
=======
  // exp : it will delete the first matching id in the url
  // http://localhost:8080/delete/fastandform/users/207471947662098432
  @GetMapping(value = "/delete/{DatabaseName}/{CollectionName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void DeleteOneDocById(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String id) {
    entityService.DeleteOneDocByspecificID(DatabaseName, CollectionName, id);

  }

  // exp: if we have two matching usernames in different docs it will delete both
  // http://localhost:8080/Delete/fastandform/users/207471947662098432
  // P.S:To delete all documents in a collection, pass in an empty document ({ })
  @GetMapping(value = "/Delete/{DatabaseName}/{CollectionName}/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public void DeleteManyDocById(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String username) {
    entityService.DeleteManyDocMatchingSpecificID(DatabaseName, CollectionName, username);
>>>>>>> 596c2ca2d60f7d67c307d38aee317188302bff19

  }

  @GetMapping(value = "/param/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
  // exemple : http://localhost:8080/param/fastandform/users?type=Developer
  public String findByParams(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @RequestParam Map<String, String> dataQuery) {

    Set<String> name = dataQuery.keySet();
    Collection<String> valeur = dataQuery.values();
    System.out.println(name.iterator().next());
    System.out.println(valeur.iterator().next());
    String n = name.iterator().next();
    String v = valeur.iterator().next();
    return entityService
        .findListDocumentBy(DatabaseName, CollectionName, n, v).toString();

    // return dataQuery.toString();
  }
<<<<<<< HEAD
  //This method will delete the key and the value once it finds the record even if the key has other values , I am still working on deleting only the value without the Key!
  //it will delete the first found matching value depending on the criterias from the params only
  //you can try it on any type from collection users with a value to understand me
  @DeleteMapping(value = "/params/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
=======

  // This method will delete the key and the value once it finds the record even
  // if the key has other values , I am still working on deleting only the value
  // without the Key!
  // it will delete the first found matching value depending on the criterias from
  // the params only
  // you can try it on any type from collection users with a value to understand
  // me
  @GetMapping(value = "/params/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
>>>>>>> 596c2ca2d60f7d67c307d38aee317188302bff19
  // exemple : http://localhost:8080/param/fastandform/users?type=Developer
  public void deleteByParams(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @RequestParam Map<String, String> dataQuery) {

    Set<String> name = dataQuery.keySet();
    Collection<String> valeur = dataQuery.values();
    System.out.println(name.iterator().next());
    System.out.println(valeur.iterator().next());
    String n = name.iterator().next();
    String v = valeur.iterator().next();
<<<<<<< HEAD
  // generalRepository.DeleteBy(DatabaseName, CollectionName, n, v);
   entityService.DeleteByRecord(DatabaseName, CollectionName, n, v,dataQuery);

  }
  //it will delete all the records that match the same values directly 
  @DeleteMapping(value = "DeleteMany/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
=======
    // generalRepository.DeleteBy(DatabaseName, CollectionName, n, v);
    generalRepository.DeleteBy(DatabaseName, CollectionName, n, v, dataQuery);

  }

  // it will delete all the records that match the same values directly
  @GetMapping(value = "DeleteMany/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
>>>>>>> 596c2ca2d60f7d67c307d38aee317188302bff19
  // exemple : http://localhost:8080/param/fastandform/users?type=Developer
  public void DeleteManyByParams(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @RequestParam Map<String, String> dataQuery) {

    Set<String> name = dataQuery.keySet();
    Collection<String> valeur = dataQuery.values();
    System.out.println(name.iterator().next());
    System.out.println(valeur.iterator().next());
    String n = name.iterator().next();
    String v = valeur.iterator().next();
<<<<<<< HEAD
  // generalRepository.DeleteBy(DatabaseName, CollectionName, n, v);
   entityService.DeleteByManyRecords(DatabaseName, CollectionName, n, v, dataQuery);
=======
    // generalRepository.DeleteBy(DatabaseName, CollectionName, n, v);
    generalRepository.DeleteMatchingRecordsBy(DatabaseName, CollectionName, n, v, dataQuery);
>>>>>>> 596c2ca2d60f7d67c307d38aee317188302bff19

  }

}

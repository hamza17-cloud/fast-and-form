package fr.utbm.da50.fastandform.core.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
        .findOneDocumentBy(DatabaseName, CollectionName, n, v).toString();

    // return dataQuery.toString();
  }

  @DeleteMapping("/delete/{DatabaseName}/{CollectionName}/{firstName}")
  public String delete(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String firstName) {

    return null;
    // return generalRepository.DeleteID(DatabaseName, CollectionName,
    // firstName).toString();

  }

}

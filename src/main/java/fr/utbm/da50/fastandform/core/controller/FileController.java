package fr.utbm.da50.fastandform.core.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private EntityService entityService;

  // exemple : http://localhost:8080/fastandform/users
  @GetMapping(value = "/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String findAll(@PathVariable String DatabaseName, @PathVariable String CollectionName) {
    // return entityService.findAllDocuments(DatabaseName,
    // CollectionName).toString();
    return generalRepository.findAllDocuments(DatabaseName, CollectionName).toString();
  }

  // exemple : http://localhost:8080/fastandform/users/207471947662098432
  @GetMapping(value = "/{DatabaseName}/{CollectionName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public String findById(@PathVariable String DatabaseName, @PathVariable String CollectionName,
      @PathVariable String id) {
    // return entityService.findOneDocumentById(DatabaseName, CollectionName,
    // id).toString();
    return generalRepository.findOneDocumentById(DatabaseName, CollectionName, id).toString();
  }
}

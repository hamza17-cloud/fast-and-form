package fr.utbm.da50.fastandform.core.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.utbm.da50.fastandform.core.repository.GeneralRepository;

@RestController
class FileController {

  @GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  String result() {
    return "Hello world";
  }

  @Autowired
  private GeneralRepository generalRepository;

  // exemple : http://localhost:8080/fastandform/users
  @GetMapping(value="/{DatabaseName}/{CollectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String findAll(@PathVariable String DatabaseName, @PathVariable String CollectionName) {

		return generalRepository.findAllDocuments(DatabaseName, CollectionName).toString();
	}
  // exemple : http://localhost:8080/fastandform/users/207471947662098432
  @GetMapping(value="/{DatabaseName}/{CollectionName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String findById(@PathVariable String DatabaseName, @PathVariable String CollectionName, @PathVariable String id) {

		return generalRepository.findOneDocumentById(DatabaseName, CollectionName, id).toString();
	}
}

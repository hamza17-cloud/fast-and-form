package fr.utbm.da50.fastandform.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping(value="/test", produces = MediaType.APPLICATION_JSON_VALUE)
  String test() {
    return generalRepository.allDocuments().toString();
  }
}

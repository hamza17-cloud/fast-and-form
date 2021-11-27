package fr.utbm.da50.fastandform.core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.utbm.da50.fastandform.core.entity.ParameterFile;

public interface ParameterFileRepository extends MongoRepository<ParameterFile, String> {
  public ParameterFile findByName(String name);
}

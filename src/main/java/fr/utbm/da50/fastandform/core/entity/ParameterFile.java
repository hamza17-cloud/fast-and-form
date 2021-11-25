package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.google.gson.Gson;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "file")
public class ParameterFile implements Serializable, Comparable<ParameterFile> {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String name;

  @Column(name="description")
  private String description;

  @Column(name="forms")
  private List<Form> forms;

  @Column(name="groups")
  private List<Group> groups;

  @Column(name="field")
  private List<Field> fields;

  @Column(name="localizations")
  private List<Localization> localizations;

  @Column(name="entities")
  private List<Entity> entities;

  public ParameterFile() {
  }

  public ParameterFile(String name, String description, List<Form> forms, List<Group> groups, List<Field> fields, List<Localization> localizations, List<Entity> entities) {
    this.name = name;
    this.description = description;
    this.forms = forms;
    this.groups = groups;
    this.fields = fields;
    this.localizations = localizations;
    this.entities = entities;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Form> getForms() {
    return this.forms;
  }

  public void setForms(List<Form> forms) {
    this.forms = forms;
  }

  public List<Group> getGroups() {
    return this.groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }

  public List<Field> getFields() {
    return this.fields;
  }

  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

  public List<Localization> getLocalizations() {
    return this.localizations;
  }

  public void setLocalizations(List<Localization> localizations) {
    this.localizations = localizations;
  }

  public List<Entity> getEntities() {
    return this.entities;
  }

  public void setEntities(List<Entity> entities) {
    this.entities = entities;
  }

  public ParameterFile name(String name) {
    setName(name);
    return this;
  }

  public ParameterFile description(String description) {
    setDescription(description);
    return this;
  }

  public ParameterFile forms(List<Form> forms) {
    setForms(forms);
    return this;
  }

  public ParameterFile groups(List<Group> groups) {
    setGroups(groups);
    return this;
  }

  public ParameterFile fields(List<Field> fields) {
    setFields(fields);
    return this;
  }

  public ParameterFile localizations(List<Localization> localizations) {
    setLocalizations(localizations);
    return this;
  }

  public ParameterFile entities(List<Entity> entities) {
    setEntities(entities);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ParameterFile)) {
            return false;
        }
        ParameterFile parameterFile = (ParameterFile) o;
        return Objects.equals(name, parameterFile.name) && Objects.equals(description, parameterFile.description) && Objects.equals(forms, parameterFile.forms) && Objects.equals(groups, parameterFile.groups) && Objects.equals(fields, parameterFile.fields) && Objects.equals(localizations, parameterFile.localizations) && Objects.equals(entities, parameterFile.entities);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, forms, groups, fields, localizations, entities);
  }

  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      ", description='" + getDescription() + "'" +
      ", forms='" + getForms() + "'" +
      ", groups='" + getGroups() + "'" +
      ", fields='" + getFields() + "'" +
      ", localizations='" + getLocalizations() + "'" +
      ", entities='" + getEntities() + "'" +
      "}";
  }

  @Override
  public int compareTo(ParameterFile o) {
    int result;

    result = getName().compareTo(o.getName());
    if(result != 0) return result;

    result = getDescription().compareTo(o.getDescription());
    if(result != 0) return result;

    return 0;
  }

  public String toJSON() {
    return new Gson().toJson(this);
  }

  public static ParameterFile fromJSON(String rawJSON) {
    return new Gson().fromJson(rawJSON, ParameterFile.class);
  }
}

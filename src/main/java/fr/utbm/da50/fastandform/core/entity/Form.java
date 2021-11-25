package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name="form")
public class Form implements Serializable, Comparable<Form> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name="name")
  private String name;

  @Column(name="description")
  private String description;


  @Column(name="forms")
  private List<Form> forms;

  @Column(name="groups")
  private List<Group> groups;

  @Column(name="field")
  private List<Field> fields;


  public Form() {
  }

  public Form(Integer id, String name, String description, List<Form> forms, List<Group> groups, List<Field> fields) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.forms = forms;
    this.groups = groups;
    this.fields = fields;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Form id(Integer id) {
    setId(id);
    return this;
  }

  public Form name(String name) {
    setName(name);
    return this;
  }

  public Form description(String description) {
    setDescription(description);
    return this;
  }

  public Form forms(List<Form> forms) {
    setForms(forms);
    return this;
  }

  public Form groups(List<Group> groups) {
    setGroups(groups);
    return this;
  }

  public Form fields(List<Field> fields) {
    setFields(fields);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Form)) {
            return false;
        }
        Form form = (Form) o;
        return Objects.equals(id, form.id) && Objects.equals(name, form.name) && Objects.equals(description, form.description) && Objects.equals(forms, form.forms) && Objects.equals(groups, form.groups) && Objects.equals(fields, form.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, forms, groups, fields);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", description='" + getDescription() + "'" +
      ", forms='" + getForms() + "'" +
      ", groups='" + getGroups() + "'" +
      ", fields='" + getFields() + "'" +
      "}";
  }

    @Override
    public int compareTo(Form o) {
      int result;

      result = getId().compareTo(o.getId());
      if(result != 0) return result;
  
      result = getName().compareTo(o.getName());
      if(result != 0) return result;
  
      result = getDescription().compareTo(o.getDescription());
      if(result != 0) return result;
  
      return 0;
    }

    public String toJSON() {
      return new Gson().toJson(this);
    }
  
    public static Form fromJSON(String rawJSON) {
      return new Gson().fromJson(rawJSON, Form.class);
    }
}

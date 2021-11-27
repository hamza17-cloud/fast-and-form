package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


import com.google.gson.Gson;

public class Group implements Serializable, Comparable<Group> {

  private Integer id;

  private String name;

  private String description;

  private List<Form> forms;

  private List<Group> groups;

  private List<Field> fields;

  public Group() {
  }

  public Group(Integer id, String name, String description, List<Form> forms, List<Group> groups, List<Field> fields) {
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

  public Group id(Integer id) {
    setId(id);
    return this;
  }

  public Group name(String name) {
    setName(name);
    return this;
  }

  public Group description(String description) {
    setDescription(description);
    return this;
  }

  public Group forms(List<Form> forms) {
    setForms(forms);
    return this;
  }

  public Group groups(List<Group> groups) {
    setGroups(groups);
    return this;
  }

  public Group fields(List<Field> fields) {
    setFields(fields);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Group)) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(description, group.description) && Objects.equals(forms, group.forms) && Objects.equals(groups, group.groups) && Objects.equals(fields, group.fields);
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
  public int compareTo(Group o) {
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

  public static Group fromJSON(String rawJSON) {
    return new Gson().fromJson(rawJSON, Group.class);
  }
}

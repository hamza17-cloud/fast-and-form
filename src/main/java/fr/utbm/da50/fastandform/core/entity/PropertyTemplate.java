package fr.utbm.da50.fastandform.core.entity;

import java.util.HashMap;
import java.util.Objects;

public class PropertyTemplate {
  private String type;

  private HashMap<String,Rule> rules;

  public PropertyTemplate() {
  }

  public PropertyTemplate(String type, HashMap<String,Rule> rules) {
    this.type = type;
    this.rules = rules;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public HashMap<String,Rule> getRules() {
    return this.rules;
  }

  public void setRules(HashMap<String,Rule> rules) {
    this.rules = rules;
  }

  public PropertyTemplate type(String type) {
    setType(type);
    return this;
  }

  public PropertyTemplate rules(HashMap<String,Rule> rules) {
    setRules(rules);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PropertyTemplate)) {
            return false;
        }
        PropertyTemplate propertyTemplate = (PropertyTemplate) o;
        return Objects.equals(type, propertyTemplate.type) && Objects.equals(rules, propertyTemplate.rules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, rules);
  }

  @Override
  public String toString() {
    return "{" +
      " type='" + getType() + "'" +
      ", rules='" + getRules() + "'" +
      "}";
  }

}

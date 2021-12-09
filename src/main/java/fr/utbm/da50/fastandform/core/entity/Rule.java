package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;


public abstract class Rule implements Verifiable, Serializable, Comparable<Rule> {
  
  private Integer id;

  private String type;

  private Map<String, Object> options;

  public Rule() {
  }

  public Rule(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Rule id(Integer id) {
    setId(id);
    return this;
  }


  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Map<String,Object> getOptions() {
    return this.options;
  }

  public void setOptions(Map<String,Object> options) {
    this.options = options;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) o;
        return Objects.equals(id, rule.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}

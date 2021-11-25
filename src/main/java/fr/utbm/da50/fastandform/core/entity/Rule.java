package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rule")
public abstract class Rule implements Verifiable, Serializable, Comparable<Rule> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


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

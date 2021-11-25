package fr.utbm.da50.fastandform.core.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.google.gson.Gson;

public class Localization implements Serializable, Comparable<Localization> {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "localization_entry", 
    joinColumns = {@JoinColumn(name = "localization_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "localization_entry_id", referencedColumnName = "id")})
  @MapKey(name = "itemName")
  private Map<String, LocalizationEntry> entries;


  public Localization() {
  }

  public Localization(Integer id, Map<String,LocalizationEntry> entries) {
    this.id = id;
    this.entries = entries;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Map<String,LocalizationEntry> getEntries() {
    return this.entries;
  }

  public void setEntries(Map<String,LocalizationEntry> entries) {
    this.entries = entries;
  }

  public Localization id(Integer id) {
    setId(id);
    return this;
  }

  public Localization entries(Map<String,LocalizationEntry> entries) {
    setEntries(entries);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Localization)) {
            return false;
        }
        Localization localization = (Localization) o;
        return Objects.equals(id, localization.id) && Objects.equals(entries, localization.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, entries);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", entries='" + getEntries() + "'" +
      "}";
  }

  @Override
  public int compareTo(Localization o) {
    int result;

    result = getId().compareTo(o.getId());
    if(result != 0) return result;

    return 0;
  }

  public String toJSON() {
    return new Gson().toJson(this);
  }

  public static Localization fromJSON(String rawJSON) {
    return new Gson().fromJson(rawJSON, Localization.class);
  }

}

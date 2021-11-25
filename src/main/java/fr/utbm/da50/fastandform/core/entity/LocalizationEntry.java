package fr.utbm.da50.fastandform.core.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.google.gson.Gson;

public class LocalizationEntry {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Transient
  private List<String> values;


  public LocalizationEntry() {
  }

  public LocalizationEntry(int id, List<String> values) {
    this.id = id;
    this.values = values;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<String> getValues() {
    return this.values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public LocalizationEntry id(int id) {
    setId(id);
    return this;
  }

  public LocalizationEntry values(List<String> values) {
    setValues(values);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LocalizationEntry)) {
            return false;
        }
        LocalizationEntry localizationEntry = (LocalizationEntry) o;
        return id == localizationEntry.id && Objects.equals(values, localizationEntry.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, values);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", values='" + getValues() + "'" +
      "}";
  }

  public String toJSON() {
    return new Gson().toJson(this);
  }

  public static LocalizationEntry fromJSON(String rawJSON) {
    return new Gson().fromJson(rawJSON, LocalizationEntry.class);
  }
}

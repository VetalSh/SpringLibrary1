package ua.vetal.library.models;

import javax.validation.constraints.*;

public class Person {
  private int id;

  @NotEmpty(message = "Name should not be empty")
  @Size(min = 2, max = 100, message = "Full name should be between 2 and 100 characters")
  private String full_name;

  @Min(value = 1900, message = "Year of birth should be greater than 1900")
  private int year_of_birth;

  public Person() {
  }

  public Person(int id, String full_name, int year_of_birth) {
    this.id = id;
    this.full_name = full_name;
    this.year_of_birth = year_of_birth;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public int getYear_of_birth() {
    return year_of_birth;
  }

  public void setYear_of_birth(int year_of_birth) {
    this.year_of_birth = year_of_birth;
  }
}

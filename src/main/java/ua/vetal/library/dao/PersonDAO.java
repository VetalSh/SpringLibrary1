package ua.vetal.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.vetal.library.models.Book;
import ua.vetal.library.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PersonDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Person> index() {
    return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
  }

  public Person show(int id) {
    return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
        new BeanPropertyRowMapper<>(Person.class))
        .stream().findAny().orElse(null);
  }

  // Used to check the uniqueness of the full name
  public Optional<Person> show(String full_name) {
    return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?", new Object[]{full_name},
        new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
  }

  public void save(Person person) {
    jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)", person.getFull_name(),
        person.getYear_of_birth());
  }

  public void update(int id, Person updatedPerson) {
    jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE id=?",
        updatedPerson.getFull_name(), updatedPerson.getYear_of_birth(), id);
  }

  public void delete(int id) {
    jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
  }

  // Здесь Join не нужен, так как получили человека с помощью отдельного метода
  public List<Book> getBooksByPersonId(int id) {
    return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id},
        new BeanPropertyRowMapper<>(Book.class));
  }
}
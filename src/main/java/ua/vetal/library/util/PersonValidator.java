package ua.vetal.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.vetal.library.dao.PersonDAO;
import ua.vetal.library.models.Person;

@Component
public class PersonValidator implements Validator {
  private final PersonDAO personDAO;

  public PersonValidator(PersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return Person.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    Person person = (Person) o;

    if(personDAO.show(person.getFull_name()).isPresent()) {
      errors.rejectValue("full_name", "", "A person with that full name already exists");
    }
  }
}

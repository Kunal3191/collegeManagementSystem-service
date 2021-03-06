package com.college.service;

import com.college.model.Attendance;
import com.college.model.Course;
import com.college.model.Exam;
import com.college.model.Person;
import com.college.repository.AttendanceRepository;
import com.college.repository.CourseRepository;
import com.college.repository.ExamRepository;
import com.college.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Person> getPersonByPersonType(String personType) {
        List<Person> personList = null;
        if (personType != null) {
            personList = personRepository.getPersonByPersonType(personType).stream()
                    .filter(person -> person.getPersonType().equals(personType))
                    .sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
        }
        return personList;
    }

    public Person findPersonByEmail(String email) {
        Person person = null;
        if (email != null) {
            person = personRepository.findPersonByEmail(email);
        }
        return person;
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public Person updatePerson(Person person, int personId) {
        Person existPerson = null;
        Optional<Person> getPersonById = personRepository.findById(personId);
        if (getPersonById.isPresent()) {
            existPerson = getPersonById.get();
        }

        existPerson.setFirstName(person.getFirstName());
        existPerson.setLastName(person.getLastName());
        existPerson.setMiddleName(person.getMiddleName());
        existPerson.setGender(person.getGender());
        existPerson.setProgram(person.getProgram());
        existPerson.setAddressLine1(person.getAddressLine1());
        existPerson.setAddressLine2(person.getAddressLine2());
        existPerson.setState(person.getState());
        existPerson.setCountry(person.getCountry());
        existPerson.setZipCode(person.getZipCode());
        existPerson.setEmail(person.getEmail());
        existPerson.setDateOfBirth(person.getDateOfBirth());
        existPerson.setPersonType(person.getPersonType());

        personRepository.save(existPerson);
        return existPerson;
    }

    public List<Person> findPersonByFirstNameAndPersonType(String firstName, String personType) {
        List<Person> personList = null;
        if (firstName != null && personType != null) {
            personList = personRepository.findPersonByFirstNameAndPersonType(firstName, personType).stream().collect(Collectors.toList());
        }
        return personList;
    }

    public void deletePerson(int personId) {
        Person person = personRepository.findById(personId).get();
        if (person != null) {
            person.getCourses().stream()
                    .forEach(course -> {
                        course.getAttendances().forEach(attendance -> attendanceRepository.delete(attendance));
                    });
            person.getCourses().stream()
                    .forEach(course -> {
                        course.getExams().forEach(exam -> examRepository.delete(exam));
                    });
            person.getCourses().forEach(course -> courseRepository.delete(course));
            personRepository.delete(person);
        }

    }

    public Person findPersonById(int personId) {
        Person person = null;
        if (personId > 0) {
            person = personRepository.findById(personId).get();
        }
        return person;
    }
}

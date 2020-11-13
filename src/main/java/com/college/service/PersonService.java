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

    public List<Person> getPersonByPersonType(String personType){
        List<Person> personList = null;
        if(personType != null){
            personList = personRepository.getPersonByPersonType(personType).stream()
                    .filter(person -> person.getPersonType().equals(personType))
                    .sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
        }
        return personList;
    }

    public Person findPersonByEmail(String email){
        Person person = null;
        if(email != null){
             person = personRepository.findPersonByEmail(email);
        }
        return person;
    }
    public void savePerson(Person person){
        personRepository.save(person);
    }

    public Person updatePerson(Person person, int personId){
        Person existPerson =  null;
        Optional<Person> getPersonById = personRepository.findById(personId);
        if(getPersonById.isPresent()) {
            existPerson = getPersonById.get();
        }
        if(existPerson.getFirstName() != null){
            existPerson.setFirstName(person.getFirstName());
        }
        if(existPerson.getLastName() != null){
            existPerson.setLastName(person.getLastName());
        }
        if(existPerson.getMiddleName() != null){
            existPerson.setMiddleName(person.getMiddleName());
        }
        if(existPerson.getGender() != null){
            existPerson.setGender(person.getGender());
        }
        if(existPerson.getProgram() != null){
            existPerson.setProgram(person.getProgram());
        }
        if(existPerson.getAddressLine1() != null){
            existPerson.setAddressLine1(person.getAddressLine1());
        }
        if(existPerson.getAddressLine2() != null){
            existPerson.setAddressLine2(person.getAddressLine2());
        }
        if(existPerson.getState() != null){
            existPerson.setState(person.getState());
        }
        if(existPerson.getCountry() != null){
            existPerson.setCountry(person.getCountry());
        }
        if(existPerson.getZipCode() != null){
            existPerson.setZipCode(person.getZipCode());
        }
        if(existPerson.getEmail() != null){
            existPerson.setEmail(person.getEmail());
        }
        if(existPerson.getDateOfBirth() != null){
            existPerson.setDateOfBirth(person.getDateOfBirth());
        }
        if(existPerson.getPersonType() != null){
            existPerson.setPersonType(person.getPersonType());
        }
        personRepository.save(existPerson);
        return existPerson;
    }

    public List<Person> findPersonByFirstNameAndPersonType(String firstName, String personType){
        List<Person> personList = null;
        if(firstName != null && personType != null) {
            personList = personRepository.findPersonByFirstNameAndPersonType(firstName, personType).stream().collect(Collectors.toList());
        }
        return personList;
    }

    public void deletePerson(int personId){
        Person findPersonById= personRepository.findById(personId).get();
        if(findPersonById != null) {
            Set<Exam> exams = findPersonById.getExams();
            exams.forEach(exam -> {
                if(exam !=null){
                    examRepository.delete(exam);
                }
            });

            List<Attendance> attendances = findPersonById.getAttendances();
            attendances.forEach(attendance -> {
                if(attendance != null){
                    attendanceRepository.delete(attendance);
                }
            });

            Set<Course> courses = findPersonById.getCourses();
            courses.forEach(course -> {
                if(course != null){
                    courseRepository.delete(course);
                }
            });
            personRepository.delete(findPersonById);
        }

    }
    public Person findPersonById(int personId){
        Person person = null;
        if(personId > 0){
            person = personRepository.findById(personId).get();
        }
        return person;
    }
}

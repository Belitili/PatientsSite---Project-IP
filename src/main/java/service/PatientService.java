package service;

import domain.db.DBException;
import domain.db.DBFactory;
import domain.db.PatientDB;
import domain.db.PatientSingletonDB;
import domain.model.Address;
import domain.model.Factory;
import domain.model.Patient;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * Facade class to access all functionalities of the Patient logic
 * @author Marie
 */
public class PatientService {
    
    PatientDB db;
    
    public PatientService(String typeDB) throws ParseException {
        if (typeDB.equals("Memory")) {
            db = DBFactory.getSingletonDB();
        } else {
            if (typeDB.equals("JPA-DB")) {
                db = DBFactory.getJpaDB();
            } else {
                throw new DBException("This DB type (" + typeDB +") is not supported.");
            }
        }
    }
    
    public void create(String fname, String lname, int byear, int bmonth, int bday, 
                        String street, int nr, String addOn, String zipcode, String city, String state, String country,
                        int weight, int height) {
        Address address = Factory.createAddress(street, nr, addOn, zipcode, city, state, country);
        Date bdate = new Date(byear,bmonth,bday);
        Patient patient = Factory.createPatient(fname, lname, bdate, address, weight, height);
        db.add(patient);
    }
    
    public void create(Patient patient) {
        db.add(patient);
    }
    
    public Collection<Patient> read() {
        return db.getAll();
    }
    
    public void update(long id, String fname, String lname, int byear, int bmonth, int bday, 
                        String street, int nr, String addOn, String zipcode, String city, String state, String country,
                        int weight, int height) {
        Address address = Factory.createAddress(street, nr, addOn, zipcode, city, state, country);
        Date bdate = new Date(byear,bmonth,bday);
        Patient patient = new Patient(id, fname, lname, bdate, address, weight, height);
        db.update(patient);
    }
    
    public void update(Patient patient) {
        db.update(patient);
                
    }
    
    public void delete(long id) {
        db.remove(id);
    }
    
    public Patient find(long id) {
        return db.find(id);
    }
    
    public HashMap<String, Double> getPatientStatistics() {
        HashMap<String, Double> stats = new HashMap<String, Double>();
        stats.put("Average height (in cm)", db.getAverageHeight());
        stats.put("Average weight (in kg)", db.getAverageWeight());
        stats.put("Average age", db.getAverageAge());
        return stats;
    }
    
}

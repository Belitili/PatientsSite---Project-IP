package controller;

import domain.model.Factory;
import domain.model.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Marie
 */
@Controller
public class PatientController {
    
    @Autowired
    private final PatientService service;
    
    public PatientController(@Autowired PatientService service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }
    
    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public ModelAndView getPatientsOverview() {
        return new ModelAndView("patients", "patients", service.read());
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView getFormAddPatient() {
        return new ModelAndView("form", "patient", Factory.createPatient());
    }
    
    @RequestMapping(value = "/createPatient", method = RequestMethod.POST)
    public ModelAndView createPatient(@Valid @ModelAttribute("patient") Patient patient, 
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Nr of errors: " + bindingResult.getAllErrors().size());
            return new ModelAndView("form");
        }
        service.create(patient);
        return getPatientsOverview();
    }
    
    @RequestMapping(value = "editPatient/{id}", method = RequestMethod.GET)
    public ModelAndView getEditForm(@PathVariable long id) {
        System.out.println(service.find(id));
        return new ModelAndView("form", "patient", service.find(id));
    }
    
    @RequestMapping(value = "requestDeletePatient/{id}", method = RequestMethod.GET)
    public ModelAndView requestDeletePatient(@PathVariable long id) {
        System.out.println(service.find(id));
        return new ModelAndView("deletePatientConfirmation", "patient", service.find(id));
    }
    
    @RequestMapping(value = "deletePatient/{id}", method = RequestMethod.GET)
    public ModelAndView deletePatient(@PathVariable long id) {
        System.out.println(service.find(id));
        service.delete(id);
        return new ModelAndView("patients", "patients", service.read());
    }
    
    @InitBinder
    public void initBinder (WebDataBinder binder) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("mm/DD/yyyy");
        dateFormatter.setLenient(false);
        binder.registerCustomEditor(Date.class,
                                    new CustomDateEditor(dateFormatter, true));
    }
    
}

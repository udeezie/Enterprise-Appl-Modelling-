package com.example.Assign4_UrielVortia;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WorkshopController {

    private static final String WORKSHOP_REGISTRATION = "workshopRegistration";
	@Autowired
    private WorkshopRegistrationRepository registrationRepository;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute(WORKSHOP_REGISTRATION, new WorkshopRegistration());
        return "registration-form";
    }

    @PostMapping("/register")
    public String registerParticipant(@Valid @ModelAttribute(WORKSHOP_REGISTRATION) WorkshopRegistration workshopRegistration,
                                      BindingResult bindingResult,
                                      Model model) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        registrationRepository.save(workshopRegistration);
        model.addAttribute("message", "Registration successful!");
        return "registration-success";
    }
}

package com.example.myhomepage;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ContactController {

  private final ContactRepository contactRepository;

  public ContactController(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @GetMapping("/contact")
  public String showContactForm(Model model) {
    model.addAttribute("contact", new Contact());
    return "contact";
  }

  @PostMapping("/contact")
  public String submitContact(
      @Valid @ModelAttribute("contact") Contact contact,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "contact";
    }

    contact.setCreatedAt(LocalDateTime.now());
    contactRepository.save(contact);

    return "redirect:/contact/complete";
  }

  @GetMapping("/contact/complete")
  public String showComplete() {
    return "contact-complete";
  }
}

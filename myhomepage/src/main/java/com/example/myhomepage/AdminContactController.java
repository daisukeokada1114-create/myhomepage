package com.example.myhomepage;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminContactController {

  private final ContactRepository contactRepository;

  public AdminContactController(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @GetMapping("/admin/contacts")
  public String showContacts(Model model) {
    List<Contact> contacts = contactRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    model.addAttribute("contacts", contacts);
    return "admin-contacts";
  }
}

package com.example.weekfivechallenge;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    Userrepository userrepository;
    @Autowired
    Postingrepository postingrepository;

    @RequestMapping("/")
    public String listPostings(Model model) {
        model.addAttribute("postings", postingrepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String postingform(Model model) {
        model.addAttribute("posting", new Posting());
        return "postingform";
    }

    @PostMapping("/process")
    public String postingform(@Valid Posting posting,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "postingform";
        }
        postingrepository.save(posting);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showposting(@PathVariable("id") long id, Model model) {
        model.addAttribute("postings", postingrepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updatePosting(@PathVariable("id") long id,
                                Model model) {
        model.addAttribute("postings", postingrepository.findById(id).get());
        return "postingform";
    }

    @RequestMapping("/delete/{id}")
    public String delPosting(@PathVariable("id") long id) {
        postingrepository.deleteById(id);
        return "redirect:/";
    }



  //  create a user
  //  RequestMapping("/")
//    public String index(Model model) {
//        User user = new User();
//        user.setName("mike malor");

        //create a messge
//        Posting posting = new Posting();
//        posting.setTitle("topic of vacation");
//        posting.setContent("i am going on vacation to cancun!");
//        posting.setPostedDate(2019);
//        posting.setPostedBy("mike");
//
//        Set<Posting> postings = new HashSet<>();
//        postings.add(posting);
//
//
//        posting = new Posting();
//        posting.setTitle("topic of buy car");
//        posting.setContent("i am going to buy a honda!");
//        posting.setPostedDate(2018);
//        posting.setPostedBy("mike");
//
//        posting = new Posting();
//        posting.setTitle("topic of eat lunch");
//        posting.setContent("i am going to eat at pizza hut!");
//        posting.setPostedDate(2017);
//        posting.setPostedBy("mike");

//        user.setPosting(postings);
//
//        userrepository.save(user);
//
//        model.addAttribute("users", userrepository.findAll());
//        return "index";
//    }

}

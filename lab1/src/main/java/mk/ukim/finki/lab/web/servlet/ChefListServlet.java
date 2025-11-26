package mk.ukim.finki.lab.web.servlet;

import mk.ukim.finki.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listChefs")
public class ChefListServlet {
    private final ChefService chefService;

    public ChefListServlet(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping
    public String listChefs(Model model) {
        model.addAttribute("chefs", chefService.listChefs());
        return "listChefs";
    }
}
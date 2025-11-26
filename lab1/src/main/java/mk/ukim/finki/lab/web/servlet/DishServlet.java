package mk.ukim.finki.lab.web.servlet;

import mk.ukim.finki.lab.service.ChefService;
import mk.ukim.finki.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dish")
public class DishServlet {
    private final DishService dishService;
    private final ChefService chefService;

    public DishServlet(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @PostMapping
    public String showDishes(@RequestParam Long chefId, Model model) {
        model.addAttribute("dishes", dishService.listDishes());
        model.addAttribute("chef", chefService.findById(chefId));
        model.addAttribute("chefId", chefId);
        return "dishesList";
    }
}
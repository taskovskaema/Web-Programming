package mk.ukim.finki.lab.web.servlet;

import mk.ukim.finki.lab.service.ChefService;
import mk.ukim.finki.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chefDetails")
public class ChefDetailsServlet {

    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsServlet(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    // -------------------------------------------
    // 1) GET → open page using:
    //    http://localhost:9090/chefDetails?chefId=1
    // -------------------------------------------
    @GetMapping
    public String getChefDetails(@RequestParam Long chefId, Model model) {
        model.addAttribute("chef", chefService.findById(chefId));
        return "chefDetails";
    }

    // -------------------------------------------
    // 2) POST → called from /dish selection form
    // -------------------------------------------
    @PostMapping
    public String addDishToChef(@RequestParam Long chefId,
                                @RequestParam String dishId,
                                Model model) {

        chefService.addDishToChef(chefId, dishId);
        model.addAttribute("chef", chefService.findById(chefId));
        return "chefDetails";
    }
}

package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.Dish;
import mk.ukim.finki.lab.service.ChefService;
import mk.ukim.finki.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    // -------------------------------------------
    // 1) Листање на јадења
    // -------------------------------------------
    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error,
                                Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("dishes", dishService.listDishes());
        return "listDishes"; // listDishes.html
    }

    // -------------------------------------------
    // 2) Празна форма за додавање ново јадење со избор на готвач
    // -------------------------------------------
    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("chefs", chefService.listChefs()); // список на готвачи
        return "dish-form"; // празна форма
    }

    // -------------------------------------------
    // 3) Форма за уредување постоечко јадење
    // -------------------------------------------
    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        model.addAttribute("chefs", chefService.listChefs()); // список на готвачи
        return "dish-form"; // dish-form.html со постоечки податоци
    }

    // -------------------------------------------
    // 4) Додавање ново јадење и додавање кај готвач
    // -------------------------------------------
    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {

        Dish newDish = dishService.create(dishId, name, cuisine, preparationTime);

        // Ако е избран готвач, додади јадење кај него
        if (chefId != null) {
            chefService.addDishToChef(chefId, newDish.getDishId());
        }

        return "redirect:/dishes";
    }

    // -------------------------------------------
    // 5) Ажурирање на постоечко јадење
    // -------------------------------------------
    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime,
                           @RequestParam(required = false) Long chefId) {

        Dish updatedDish = dishService.update(id, dishId, name, cuisine, preparationTime);

        // Ако е избран готвач, додај го јадењето кај него
        if (chefId != null && updatedDish != null) {
            chefService.addDishToChef(chefId, updatedDish.getDishId());
        }

        return "redirect:/dishes";
    }

    // -------------------------------------------
    // 6) Бришење на јадење
    // -------------------------------------------
    @PostMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}

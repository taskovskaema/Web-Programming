Лабораториска вежба 2 Б
Во оваа вежба ќе треба да продолжите со работа во рамки на проектот од претходната лабораториска вежба.

1. Модели
Во класата Dish додадете уште едно својство private Long id кое е уникатно за секое јадење. Притоа, id генерирајте го за секое јадење како што е направено во аудиториската вежба (со статички counter или слично).

2. Repository слој
Креирајте CRUD функционалност за Dish.

Проширете го интерфејсот DishRepository во пакетот mk.ukim.finki.wp.lab.repository:

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
    Optional<Dish> findById(Long id);
    Dish save(Dish dish);
    void deleteById(Long id);
}
Креирајте InMemoryDishRepository класа која го имплементира овој интерфејс.

Имплементација: - findAll() – ја враќа листата DataHolder.dishes

findByDishId(String dishId) – го враќа јадењето по dishId

findById(Long id) – го враќа јадењето по Long id

save(Dish dish) – ако постои ажурирај, инаку додади ново

deleteById(Long id) – избришете го јадењето од листата

3. Service слој
Проширете го DishService интерфејсот во пакетот mk.ukim.finki.wp.lab.service:

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime);
    void delete(Long id);
}
Креирајте DishServiceImpl класа која го имплементира DishService.
DishServiceImpl треба да зависи од DishRepository.

4. Web слој — контролери и погледи
Сега преминуваме на Spring MVC контролери (без сервлети).

Креирајте пакет mk.ukim.finki.wp.lab.web.controller и во него DishController.

4.1 Метод за листање на јадења
Имплементирајте метод:

public String getDishesPage(@RequestParam(required = false) String error, Model model) кој треба да го прикаже погледот listDishes.html со приказ на сите јадења.

Нека одговара на mapping: /dishes.

До секое јадење додадете две копчиња: - едно за едитирање на јадењето, - едно за бришење на јадењето.

4.2 Додавање ново јадење
Имплементирајте метод:

public String saveDish(@RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime)

Кој ќе овозможи додавање на ново јадење и ќе одговара на mapping: /dishes/add. При успешно додавање редиректирајте кон /dishes.

4.3 Ажурирање јадење
Имплементирајте метод:

public String editDish(@PathVariable Long id, @RequestParam String dishId, @RequestParam String name, @RequestParam String cuisine, @RequestParam int preparationTime)

Нека одговара на mapping: /dishes/edit/{id}
При успешно ажурирање → редирект кон /dishes.

4.4 Бришење јадење
Имплементирајте метод: public String deleteDish(@PathVariable Long id)

Mapping: /dishes/delete/{id} . По успешно бришење → повторно прикажете ја листата со јадења.

5. Поврзани сервис и repository методи
Креирајте ги сите потребни методи во сервис и репозиториум слојот согласно контролерите.
Сè останува in-memory како во претходната вежба.

6. Страници (views)
6.1 dish-form.html
Креирајте страна за додавање/уредување на јадење.

Формата треба да содржи полиња: - dishId - name - cuisine - preparationTime

Истата форма да се користи и за додавање и за уредување (како на вежби).

6.2 listDishes.html
Прикажете ги сите јадења (dishId, name, cuisine, preparationTime)
Додадете копче за уредување
Додадете копче за бришење
Додадете копче за додавање ново јадење
7. Дополнителни рути за форми
Имплементирајте метод: public String getEditDishForm(@PathVariable Long id, Model model)

Mapping: /dishes/dish-form/{id}

При уредување прикажете ги постоечките податоци.
Ако id не постои → редирект кон /dishes?error=DishNotFound.

Имплементирајте метод: public String getAddDishPage(Model model)

Mapping: /dishes/dish-form
Прикажува празна форма за додавање ново јадење.

8. Интеграција со Chef
Во DishController оставете ја функционалноста за додавање јадење кај готвач (од Лаб 1Б).
ChefService и DishService треба да функционираат заедно како претходно.

9. Проверка
Проверете: - прикажување на сите јадења - додавање, уредување и бришење на јадења - правилна работа на формите - правилно функционирање со Chef модулот од претходната вежба

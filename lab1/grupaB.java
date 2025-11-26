Спецификација за лабораториската вежба
1. Креирање на Spring Boot проект
Креирајте нов Spring Boot проект со следните карактеристики:

groupId: mk.ukim.finki.wp
artifactId: lab
Зависности: Истите како проектот од аудиториските вежби (видете ги <dependency> таговите во pom.xml)
2. Креирање на Model класи
Дефинирајте пакет mk.ukim.finki.wp.lab.model и креирајте ги следните класи:

2.1 Chef класа
Креирајте класа Chef која содржи:

Long id
String firstName
String lastName
String bio
List<Dish> dishes
Еден готвач може да подготвува повеќе јадења.

2.2 Dish класа
Креирајте класа Dish која содржи:

String dishId
String name
String cuisine
int preparationTime
3. Креирање на DataHolder класа
Креирајте класа DataHolder во пакетот mk.ukim.finki.wp.lab.bootstrap која ќе содржи:

Статична листа: public static List<Chef> chefs = new ArrayList<>(); иницијализирана со 5 вредности
Статична листа: public static List<Dish> dishes = new ArrayList<>(); иницијализирана со 5 вредности
4. Креирање на Repository слој
4.1 ChefRepository интерфејс
Дефинирајте интерфејс ChefRepository во пакетот mk.ukim.finki.wp.lab.repository:

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef save(Chef chef);
}
4.2 InMemoryChefRepository имплементација
Креирајте класа InMemoryChefRepository која го имплементира ChefRepository интерфејсот.

Имплементација:

findAll() - враќа ја листата од DataHolder.chefs
findById(Long id) - го враќа готвачот со id еднакво на пратената вредност во параметарот
save(Chef chef) - ако готвачот веќе постои во листата, ажурирај го, инаку додај нов готвач. Врати го зачуваниот готвач
4.3 DishRepository интерфејс
Дефинирајте интерфејс DishRepository во пакетот mk.ukim.finki.wp.lab.repository:

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
}
4.4 InMemoryDishRepository имплементација
Креирајте класа InMemoryDishRepository која го имплементира DishRepository интерфејсот.

Имплементација:

findAll() - враќа ја листата од DataHolder.dishes
findByDishId(String dishId) - го враќа јадењето чиј dishId е еднаков на испратената вредност
5. Креирање на Service слој
5.1 ChefService интерфејс
Дефинирајте интерфејс ChefService во пакетот mk.ukim.finki.wp.lab.service:

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
}
5.2 DishService интерфејс
Дефинирајте интерфејс DishService во пакетот mk.ukim.finki.wp.lab.service:

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
}
5.3 Имплементација на сервисите
Имплементирајте ги сервисите (креирајте ChefServiceImpl и DishServiceImpl класи).

ChefService треба да зависи од ChefRepository и DishRepository. 

Методот addDishToChef треба да го најде готвачот по chefId, да го најде јадењето по dishId, да го додаде јадењето во листата на јадења на готвачот, и потоа да го зачува готвачот преку save методот.

DishService треба да зависи од DishRepository.

6. Креирање на Web слој (Servlets)
Целта на вежбата е да се креираат страници од кои ќе се избере готвач и јадење, а потоа избраното јадење да се додаде во листата на јадења што ги подготвува избраниот готвач. Следете ги наредните чекори за да го имплементирате ваквото однесување.

6.1 ChefListServlet
Креирајте сервлет ChefListServlet во пакетот mk.ukim.finki.wp.lab.web и мапирајте го на патеката /listChefs. Овој сервлет треба да зависи од ChefService и да ги прикаже сите добиени готвачи од методот listChefs().

Прилагодете го фајлот listChefs.html за изгледот на оваа страница.

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Restaurant Chefs - Welcome to Our Restaurant</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to Our Restaurant</h1>
    </header>
    <main>
        <h2>Choose a chef:</h2>
        <form action="/dish" method="POST">
            <!-- Display radio buttons for each chef,
                 the value should be the chef id 
                 and the displayed text should be: 
                 Name: <firstName> <lastName>, Bio: <bio> -->
            <input type="radio" name="chefId" value="1"> Chef 1 <br/>
            <input type="radio" name="chefId" value="2"> Chef 2 <br/>
            <input type="radio" name="chefId" value="3"> Chef 3 <br/>
            <input type="submit" value="Submit">
        </form>
    </main>
</body>
</html>
Забелешка: Вредноста на секоја ставка во radio листата е id-то на готвачот.

6.2 DishServlet
По избор на готвач, треба да ја прикажете страница со јадења. За оваа цел креирајте сервлет DishServlet мапиран на /dish. Овој сервлет треба да зависи од DishService и ChefService.

Сервлетот треба да:

Го прочита избраното chefId од формата
Ја прикаже страната за избор на јадење за додавање во листата на јадења на избраниот готвач
Ги прикаже сите достапни јадења од listDishes()
Прикаже информации за избраниот готвач
Овозможи испраќање на потребните податоци до следниот сервлет
Во фолдерот src/main/resources/templates додадете фајл dishesList.html и прилагодете го за изгледот на оваа страница.

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Dish to Chef</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table, td, th {
            border: 1px solid black;
            padding: 10px;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        section {
            float: left;
            margin: 0 1.5%;
            width: 63%;
        }
        aside {
            float: right;
            margin: 0 1.5%;
            width: 30%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Select the Dish to add to the Chef</h1>
    </header>
    <section>
        <h2>Select dish:</h2>
        <form action="/chefDetails" method="POST">
            <!-- Dynamically display radio buttons for each dish -->
            <input type="radio" name="dishId" value="1"> Pasta Carbonara <br/>
            <input type="radio" name="dishId" value="2"> Beef Wellington <br/>
            <input type="radio" name="dishId" value="3"> Chicken Tikka Masala <br/>
            <br/>
            <input type="submit" value="Add dish">
        </form>
    </section>
    <aside>
        <table>
            <tr>
                <td><b>Chef ID</b></td>
                <td><!-- Display selected chefId --></td>
            </tr>
            <tr>
                <td><b>Chef Name</b></td>
                <td><!-- Display chef name --></td>
            </tr>
        </table>
    </aside>
</body>
</html>
Забелешка: Вредноста на секоја ставка во radio листата е dishId-от на јадењето.

6.3 ChefDetailsServlet
Креирајте сервлет со име ChefDetailsServlet мапиран на /chefDetails. Овој сервлет треба да зависи од ChefService и DishService.

Сервлетот треба да:

Ги прочита податоците од формата (chefId и dishId)
Го најде избраниот готвач и јадење
Го додаде јадењето во листата на јадења на избраниот готвач
Ги прикаже сите детали за готвачот: име, биографија и сите јадења што ги подготвува
Прилагодете го фајлот chefDetails.html за изгледот на оваа страница.

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Chef Details</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 5px;
            margin: 5px 0;
            background-color: #f5f5f5;
            border-radius: 3px;
        }
    </style>
</head>
<body>
    <!-- Dynamically display data -->
    <header>
        <h1>Chef: Gordon Ramsay</h1>
    </header>
    <section>
        <h2>Bio: World-renowned chef with 16 Michelin stars</h2>
        <h2>Dishes prepared by this chef:</h2>
        <ul>
            <li>Beef Wellington (British, 45 min)</li>
            <li>Sticky Toffee Pudding (British, 30 min)</li>
        </ul>
    </section>
</body>
</html>Спецификација за лабораториската вежба
1. Креирање на Spring Boot проект
Креирајте нов Spring Boot проект со следните карактеристики:

groupId: mk.ukim.finki.wp
artifactId: lab
Зависности: Истите како проектот од аудиториските вежби (видете ги <dependency> таговите во pom.xml)
2. Креирање на Model класи
Дефинирајте пакет mk.ukim.finki.wp.lab.model и креирајте ги следните класи:

2.1 Chef класа
Креирајте класа Chef која содржи:

Long id
String firstName
String lastName
String bio
List<Dish> dishes
Еден готвач може да подготвува повеќе јадења.

2.2 Dish класа
Креирајте класа Dish која содржи:

String dishId
String name
String cuisine
int preparationTime
3. Креирање на DataHolder класа
Креирајте класа DataHolder во пакетот mk.ukim.finki.wp.lab.bootstrap која ќе содржи:

Статична листа: public static List<Chef> chefs = new ArrayList<>(); иницијализирана со 5 вредности
Статична листа: public static List<Dish> dishes = new ArrayList<>(); иницијализирана со 5 вредности
4. Креирање на Repository слој
4.1 ChefRepository интерфејс
Дефинирајте интерфејс ChefRepository во пакетот mk.ukim.finki.wp.lab.repository:

public interface ChefRepository {
    List<Chef> findAll();
    Optional<Chef> findById(Long id);
    Chef save(Chef chef);
}
4.2 InMemoryChefRepository имплементација
Креирајте класа InMemoryChefRepository која го имплементира ChefRepository интерфејсот.

Имплементација:

findAll() - враќа ја листата од DataHolder.chefs
findById(Long id) - го враќа готвачот со id еднакво на пратената вредност во параметарот
save(Chef chef) - ако готвачот веќе постои во листата, ажурирај го, инаку додај нов готвач. Врати го зачуваниот готвач
4.3 DishRepository интерфејс
Дефинирајте интерфејс DishRepository во пакетот mk.ukim.finki.wp.lab.repository:

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
}
4.4 InMemoryDishRepository имплементација
Креирајте класа InMemoryDishRepository која го имплементира DishRepository интерфејсот.

Имплементација:

findAll() - враќа ја листата од DataHolder.dishes
findByDishId(String dishId) - го враќа јадењето чиј dishId е еднаков на испратената вредност
5. Креирање на Service слој
5.1 ChefService интерфејс
Дефинирајте интерфејс ChefService во пакетот mk.ukim.finki.wp.lab.service:

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
}
5.2 DishService интерфејс
Дефинирајте интерфејс DishService во пакетот mk.ukim.finki.wp.lab.service:

public interface DishService {
    List<Dish> listDishes();
    Dish findByDishId(String dishId);
}
5.3 Имплементација на сервисите
Имплементирајте ги сервисите (креирајте ChefServiceImpl и DishServiceImpl класи).

ChefService треба да зависи од ChefRepository и DishRepository. 

Методот addDishToChef треба да го најде готвачот по chefId, да го најде јадењето по dishId, да го додаде јадењето во листата на јадења на готвачот, и потоа да го зачува готвачот преку save методот.

DishService треба да зависи од DishRepository.

6. Креирање на Web слој (Servlets)
Целта на вежбата е да се креираат страници од кои ќе се избере готвач и јадење, а потоа избраното јадење да се додаде во листата на јадења што ги подготвува избраниот готвач. Следете ги наредните чекори за да го имплементирате ваквото однесување.

6.1 ChefListServlet
Креирајте сервлет ChefListServlet во пакетот mk.ukim.finki.wp.lab.web и мапирајте го на патеката /listChefs. Овој сервлет треба да зависи од ChefService и да ги прикаже сите добиени готвачи од методот listChefs().

Прилагодете го фајлот listChefs.html за изгледот на оваа страница.

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Restaurant Chefs - Welcome to Our Restaurant</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to Our Restaurant</h1>
    </header>
    <main>
        <h2>Choose a chef:</h2>
        <form action="/dish" method="POST">
            <!-- Display radio buttons for each chef,
                 the value should be the chef id 
                 and the displayed text should be: 
                 Name: <firstName> <lastName>, Bio: <bio> -->
            <input type="radio" name="chefId" value="1"> Chef 1 <br/>
            <input type="radio" name="chefId" value="2"> Chef 2 <br/>
            <input type="radio" name="chefId" value="3"> Chef 3 <br/>
            <input type="submit" value="Submit">
        </form>
    </main>
</body>
</html>
Забелешка: Вредноста на секоја ставка во radio листата е id-то на готвачот.

6.2 DishServlet
По избор на готвач, треба да ја прикажете страница со јадења. За оваа цел креирајте сервлет DishServlet мапиран на /dish. Овој сервлет треба да зависи од DishService и ChefService.

Сервлетот треба да:

Го прочита избраното chefId од формата
Ја прикаже страната за избор на јадење за додавање во листата на јадења на избраниот готвач
Ги прикаже сите достапни јадења од listDishes()
Прикаже информации за избраниот готвач
Овозможи испраќање на потребните податоци до следниот сервлет
Во фолдерот src/main/resources/templates додадете фајл dishesList.html и прилагодете го за изгледот на оваа страница.

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add Dish to Chef</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }
        table, td, th {
            border: 1px solid black;
            padding: 10px;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        section {
            float: left;
            margin: 0 1.5%;
            width: 63%;
        }
        aside {
            float: right;
            margin: 0 1.5%;
            width: 30%;
        }
    </style>
</head>
<body>
    <header>
        <h1>Select the Dish to add to the Chef</h1>
    </header>
    <section>
        <h2>Select dish:</h2>
        <form action="/chefDetails" method="POST">
            <!-- Dynamically display radio buttons for each dish -->
            <input type="radio" name="dishId" value="1"> Pasta Carbonara <br/>
            <input type="radio" name="dishId" value="2"> Beef Wellington <br/>
            <input type="radio" name="dishId" value="3"> Chicken Tikka Masala <br/>
            <br/>
            <input type="submit" value="Add dish">
        </form>
    </section>
    <aside>
        <table>
            <tr>
                <td><b>Chef ID</b></td>
                <td><!-- Display selected chefId --></td>
            </tr>
            <tr>
                <td><b>Chef Name</b></td>
                <td><!-- Display chef name --></td>
            </tr>
        </table>
    </aside>
</body>
</html>
Забелешка: Вредноста на секоја ставка во radio листата е dishId-от на јадењето.

6.3 ChefDetailsServlet
Креирајте сервлет со име ChefDetailsServlet мапиран на /chefDetails. Овој сервлет треба да зависи од ChefService и DishService.

Сервлетот треба да:

Ги прочита податоците од формата (chefId и dishId)
Го најде избраниот готвач и јадење
Го додаде јадењето во листата на јадења на избраниот готвач
Ги прикаже сите детали за готвачот: име, биографија и сите јадења што ги подготвува
Прилагодете го фајлот chefDetails.html за изгледот на оваа страница.

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Chef Details</title>
    <style type="text/css">
        body {
            width: 800px;
            margin: auto;
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            padding: 5px;
            margin: 5px 0;
            background-color: #f5f5f5;
            border-radius: 3px;
        }
    </style>
</head>
<body>
    <!-- Dynamically display data -->
    <header>
        <h1>Chef: Gordon Ramsay</h1>
    </header>
    <section>
        <h2>Bio: World-renowned chef with 16 Michelin stars</h2>
        <h2>Dishes prepared by this chef:</h2>
        <ul>
            <li>Beef Wellington (British, 45 min)</li>
            <li>Sticky Toffee Pudding (British, 30 min)</li>
        </ul>
    </section>
</body>
</html>

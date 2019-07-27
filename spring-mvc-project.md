## Spring MVC Demo Project with multiple pages

1.  Create new maven project

    ```yaml
    Project:    mvclabdemo
    groupId:    com.cg
    artifactId: mvc-lab-demo
    ```

2.  Add following dependencies in `pom.xml` file.

    ```xml
    <dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-webmvc</artifactId>
  		<version>5.1.6.RELEASE</version>
  	</dependency>

  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>jstl</artifactId>
  		<version>1.2</version>
  	</dependency>
    ```

3.  Right click on project > Java EE Tools > Generate Deployment descriptor stub > edit web.xml file

    ```xml
  	<servlet>
  	<servlet-name>dispatcher</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
   	<param-name>contextConfigLocation</param-name>
   	<param-value>
         /WEB-INF/spring.xml
   	</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
   	</servlet>
	<servlet-mapping>
  		<servlet-name>dispatcher</servlet-name>
  		<url-pattern>/</url-pattern>
    </servlet-mapping>
    ```

4.  Create new Spring bean configuration file with namespaces : beans, context and mvc
    NOTE: Make sure you select 'beans' first and context and mvc later.

    ```xml
    <beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
		http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd">

        <context:component-scan base-package="com.cg"/>
        <mvc:annotation-driven/>
        <mvc:view-resolvers>
            <mvc:jsp suffix=".jsp" prefix="/WEB-INF/pages/"  />
        </mvc:view-resolvers>
    </beans>
    ```

5.  Create a model/entity class `com.cg.entities.Book`

    ```java
    public class Book {
        private Integer bookId;    
        private String title;
        private String author;

        //...getters,setters and two constructors...
    }
    ```

5.  Create new BookDAO class `com.cg.services.BookDAO`

    ```java
    @Repository
    public class BookDAO {
    private List<Book> books = new ArrayList<>();
        
        @PostConstruct
        public void init() {
            books.add(new Book(101, "Let Us C", "Kanetkar"));
            books.add(new Book(102,"OOPs using C++","Balaguruswami"));
        }
        
        public Book findById(int bookId) {
            for(Book b : books) {
                if(b.getBookId()==bookId) {
                    return b;
                }
            }
            return null;
        }
        
        public List<Book> getAll(){
            return books;
        }
        
        public void update(int id,Book b) {
            Book temp = findById(id);
            temp.setAuthor(b.getAuthor());
            temp.setTitle(b.getTitle());
        }
        
        public void delete(Book book) {
            books.remove(book);
        }

        public void create(Book book) {
            books.add(book);
        }
    }

    ```
6.  Create an Exception class `com.cg.exceptions.ApplicationException`

    ```java
    public class ApplicationException extends RuntimeException {

        public ApplicationException(String arg0) {
            super(arg0);
            // TODO Auto-generated constructor stub
        }

    }
    ```

7.  Create a Book Service class `com.cg.services.BookService`

    ```java
    @Service
    public class BookService {
        
        @Autowired private BookDAO dao;
        
        public Book findById(int id) {
            Book book =	dao.findById(id);
            if(book==null) {
                throw new ApplicationException("Book not found!");
            }
            return book;
        }
        
        public void update(Book book) {
            Book temp = findById(book.getBookId());
            dao.update(book.getBookId(), book);
        }
        
        public void delete(int id) {
            Book book = findById(id);
            dao.delete(book);
        }
        
        public List<Book> getAll(){
            List<Book> books = dao.getAll();
            if(books == null || books.isEmpty()) {
                throw new ApplicationException("No books available yet!");
            }
            return books;
        }
        
        public void save(Book book) {
            Book temp = dao.findById(book.getBookId());
            if(temp==null) {
                dao.create(book);
            }
            else
                throw new ApplicationException("Book already exists!");
        }
    }

    ```

8.  Create the home controller (welcome page with links) `com.cg.controllers.HomeController`

    ```java
    @Controller
    @RequestMapping("/")
    public class HomeController {

        @GetMapping
        public String viewHome() {
            System.out.println("Home controller");
            return "home";
        }
    }

    ```

9.  Create a BookController class `com.cg.controllers.BookController`

    ```java
    @Controller
    @RequestMapping("/books")
    public class BookController {
        
        @Autowired private BookService service;
        
        //on GET request just show FORM
        @GetMapping("/delete")
        public String deleteFind() {
            return "delete-view";
        }
        
        //on POST request display details and delete button
        @PostMapping("/delete")
        public String deleteForm(Model model,@RequestParam int id) {
            try {
                Book book = service.findById(id);
                model.addAttribute("book", book);
            }catch(ApplicationException ex) {
                System.out.println("Error "+ex.getMessage());
                model.addAttribute("msg","No record found!");
            }
            return "delete-view";
        }
        
        //on POST request delete by id
        @PostMapping("/delete-by-id")
        public String deleteById(Model model,@RequestParam int id) {
            try {
                service.delete(id);
                model.addAttribute("msg","Book deleted successfuly!");
            }catch(ApplicationException ex) {
                System.out.println("Error "+ex.getMessage());
                model.addAttribute("msg","No record found!");
            }
            return "delete-view";
        }
        
        //on GET request just show FORM
        @GetMapping("/edit")
        public String editView() {
            return "edit-view";
        }
        
        //on POST request display details
        @PostMapping("/edit")
        public String editDetails(Model model,@RequestParam int id) {
            try {
                Book book = service.findById(id);
                model.addAttribute("book", book);
            }catch(ApplicationException ex) {
                System.out.println("Error "+ex.getMessage());
                model.addAttribute("msg","No record found!");
            }
            return "edit-view";
        }
        
        //on GET request just show FORM
        @GetMapping("/view")
        public String viewOne(Model model) {
            return "view-one";
        }
        
        //on POST request display all book details
        @PostMapping("/view")
        public String viewDetails(Model model, @RequestParam int id) {
            try {
            Book book = service.findById(id);
            model.addAttribute("book", book);
            }catch(ApplicationException ex) {
                System.out.println("Error "+ex.getMessage());
                model.addAttribute("msg","No record found!");
            }
            return "view-one";
        }
        
        @GetMapping("/view-all")
        public String findAll(Model model) {
            String message = null;
            try {
            List<Book> books = service.getAll();
            message = books.size()+" records found!";
            model.addAttribute("books",books);
            }catch(ApplicationException ex) {
                message = "No records found!";
            }
            model.addAttribute("msg",message);
            return "view-all";
        }
        
        @PostMapping("/edit-save")
        public String editSave(Model model, @ModelAttribute Book book,BindingResult result) {
            if(result.hasErrors()) {
                model.addAttribute("msg","Invalid form input");
            }
            try {
                service.update(book);
            }catch(ApplicationException ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("msg",ex.getMessage());
            }
            return "edit-view";
        }
        
        
        @GetMapping("/create")
        public String newSave(Model model) {
                model.addAttribute("book",new Book());
                return "new-view";
            }
            
        @PostMapping("/create")
        public String newSave(Model model, @ModelAttribute Book book,BindingResult result) {
            if(result.hasErrors()) {
                model.addAttribute("msg","Invalid form input");
            }
            try {
                service.save(book);
                model.addAttribute("msg","Book "+book.getTitle()+" added successfuly!");
                model.addAttribute("book",new Book()); //Empty the form fields on success
            }catch(ApplicationException ex) {
                System.out.println(ex.getMessage());
                model.addAttribute("msg",ex.getMessage());
            }
            return "new-view";
        }
    }

    ```
10. Now create following JSP Pages inside `src/main/webapp/WEB-INF/pages` directory.

    - home.jsp
    - view-one.jsp
    - new-view.jsp
    - view-all.jsp
    - edit-view.jsp
    - delete-view.jsp

11. The Home page [home.jsp]

    ```html
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home page</title>
    </head>
    <body>
        <h2>Book library!</h2>
        
        <h3>${msg }</h3>
        <table>
            <tr>
                <td>Operations</td>
            </tr>
            <tr>
                <td><a href="books/create">Add a book</a></td>
            </tr>
            <tr>
                <td><a href="books/delete">Delete a book</a></td>
            </tr>
            <tr>
                <td><a href="books/edit"> Modify a book </a></td>
            </tr>
            <tr>
                <td><a href="books/view"> Find a book</a></td>
            </tr>
            <tr>
                <td><a href="books/view-all">View all books</a></td>
            </tr>
        </table>
    </body>
    </html>
    ```
12. The View-one page to find and display one record.

    ```html
    <html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Book</title>
    </head>
    <body>
    <h2>View Book</h2>
    <h3>${msg }</h3>
    <form method="post">
        Enter book id : <input type="text" value="0" name="id"/>
        <input type="submit" value="find"/>
    </form>

    <table border=1 cellpadding="3" cellspacing="0">
    <tr>
    <td>Book Id</td>
    <td>Title</td>
    <td>Author</td>
    </tr>
        <c:if test="${book !=null}">
        <tr>
        <td>${book.bookId }</td>
        <td>${book.title }</td>
        <td>${book.author }</td>
        </tr>
        </c:if>
    </table>
    </body>
    </html>
    ```

13. The view-all page which would list all books.

    ```html
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View All Books</title>
    </head>
    <body>
    <h2>View All Books</h2>
    ${msg }
    <table border=1 cellpadding="3" cellspacing="0">
    <tr>
    <td>Book Id</td>
    <td>Title</td>
    <td>Author</td>
    </tr>
    <c:forEach items="${books}" var="b">
        <tr>
        <td>${b.bookId }</td>
        <td>${b.title }</td>
        <td>${b.author }</td>
        </tr>
    </c:forEach>
    </table>
    </body>
    </html>
    ```

14. the edit-view page, that allows editing existing record.

    ```html
    <html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Book</title>
    </head>
    <body>
    <h3>${msg }</h3>
    <form method="post">
        Enter book id : <input type="text" value="0" name="id"/>
        <input type="submit" value="find"/>
    </form>

    <c:if test="${book !=null}">
    <s:form action="edit-save" method="post" modelAttribute="book">
    <table border=1 cellpadding="3" cellspacing="0">
    <tr>
    <td>Book Id</td> <td><s:input path="bookId" value="${book.bookId}"/></td>
    </tr>
    <td>Title</td> <td><s:input path="title" value="${book.title}"/></td>
    </tr>
    <td>Author</td> <td><s:input path="author" value="${book.author}"/></td>
    </tr>
    <tr>
    <td colspan="3"> 
    <input type="submit" value="save"/> 
    </td>
    </tr>
        
    </table>
    </s:form>
    </c:if>
    </body>
    </html>
    ```

15. The delete-view page, that allows deleting a record.

    ```html
    <html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Delete Book</title>
    </head>
    <body>
    <h3>${msg }</h3>
    <form method="post">
        Enter book id : <input type="text" value="0" name="id"/>
        <input type="submit" value="find"/>
    </form>

    <c:if test="${book !=null}">
    <table border=1 cellpadding="3" cellspacing="0">
    <tr>
    <td>Book Id</td>
    <td>Title</td>
    <td>Author</td>
    </tr>
        
        <tr>
        <td>${book.bookId }</td>
        <td>${book.title }</td>
        <td>${book.author }</td>
        </tr>
        
    </table>
    <form action="delete-by-id" method="post" >
        <input type="hidden" value="${book.bookId }" name="id"/>
        <input type="submit" value="delete"/>
    </form>
    </c:if>
    </body>
    </html>
    ```
16. Now, run application on tomcat 8+ 

   
>    I have not implemented FORM VALIDATION, please add it.

>    Code may have some bugs, please report bugs on my mail address MahendraShinde@synergetics-india.com

    
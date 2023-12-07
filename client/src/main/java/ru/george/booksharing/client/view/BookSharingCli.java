package ru.george.booksharing.client.view;

import ru.george.booksharing.client.controller.ApplicationCache;
import ru.george.booksharing.client.controller.BookController;
import ru.george.booksharing.client.controller.UserController;
import ru.george.booksharing.client.model.user.GetUserResponse;
import ru.george.booksharing.client.model.user.UserRole;

import java.util.List;
import java.util.Scanner;

import static ru.george.booksharing.client.view.CliState.*;

public class BookSharingCli {
    private final ApplicationCache cache = new ApplicationCache();
    private final UserController userController = new UserController(cache);
    private final BookController bookController = new BookController();

    private CliState state = MAIN_MENU;

    public void start() {
        boolean isLoop = true;
        while (isLoop) {
            switch (state) {
                case MAIN_MENU -> mainMenu();
                case LOGIN -> login();
                case REGISTRATION -> registration();
                case USER_APPLICATION -> userApplication();
                case ADMIN_APPLICATION -> adminApplication();
                case EXIT -> isLoop = false;
            }
        }
    }

    private void mainMenu() {
        try {
            System.out.println("""
                    Enter function:
                    1. login
                    2. registration
                    0. exit"""
            );

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> state = LOGIN;
                case "2" -> state = REGISTRATION;
                case "0" -> state = EXIT;
                default -> System.out.println("No such function. Try again\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter username:");
            String username = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            boolean isSuccessful = userController.login(username, password);
            if (isSuccessful) {
                System.out.println("User login successfully\n");
                boolean isAdmin = cache.getUser().role() == UserRole.ADMIN;
                if (isAdmin) {
                    state = ADMIN_APPLICATION;
                } else {
                    state = USER_APPLICATION;
                }
            } else {
                System.out.println("User login failed\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO: Fix duplicated code
    private void registration() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter username:");
            String username = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            System.out.println("""
                    Enter user role:
                    1. user
                    2. admin"""
            );

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    boolean isSuccessful = userController.createUser(username, password, UserRole.USER);
                    if (isSuccessful) {
                        System.out.println("User created successfully\n");
                        state = MAIN_MENU;
                    } else {
                        System.out.println("User creating failed\n");
                    }
                }
                case "2" -> {
                    boolean isSuccessful = userController.createUser(username, password, UserRole.ADMIN);
                    if (isSuccessful) {
                        System.out.println("Admin created successfully\n");
                        state = MAIN_MENU;
                    } else {
                        System.out.println("User creating failed\n");
                    }
                }
                default -> System.out.println("No such role. Try again\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void userApplication() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("""
                    Enter function:
                    1. Show all books
                    2. Add book
                    0. Exit"""
            );

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> showAllBooks(USER_APPLICATION);
                case "2" -> addBook(USER_APPLICATION);
                case "0" -> state = EXIT;
                default -> System.out.println("No such function. Try again\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adminApplication() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("""
                    Enter function:
                    1. Show all books
                    2. Add book
                    3. Add user
                    4. Delete book
                    0. Exit"""
            );

            String input = scanner.nextLine();
            switch (input) {
                case "1" -> showAllBooks(ADMIN_APPLICATION);
                case "2" -> addBook(ADMIN_APPLICATION);
                case "3" -> addUser();
                case "4" -> deleteBook();
                case "0" -> state = EXIT;
                default -> System.out.println("No such function. Try again\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllBooks(CliState endState) {
        try {
            List<GetUserResponse> users = userController.getAllUsers();
            for (GetUserResponse user : users) {
                System.out.println(user);
            }
            System.out.println();
            state = endState;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addBook(CliState endState) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter book title:");
            String title = scanner.nextLine();

            System.out.println("Enter book author:");
            String author = scanner.nextLine();

            System.out.println("Enter book year:");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.println("Enter book genre:");
            String genre = scanner.nextLine();

            int userId = cache.getUser().id();
            boolean isSuccessful = bookController.createBook(title, author, year, genre, userId);
            if (isSuccessful) {
                System.out.println("Book created successfully\n");
                state = endState;
            } else {
                System.out.println("Book creating failed\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // TODO: Fix duplicated code
    private void addUser() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter username:");
            String username = scanner.nextLine();

            System.out.println("Enter password:");
            String password = scanner.nextLine();

            System.out.println("""
                    Enter user role:
                    1. user
                    2. admin"""
            );
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> {
                    boolean isSuccessful = userController.createUser(username, password, UserRole.USER);
                    if (isSuccessful) {
                        System.out.println("User created successfully\n");
                        state = ADMIN_APPLICATION;
                    } else {
                        System.out.println("User creating failed\n");
                    }
                }
                case "2" -> {
                    boolean isSuccessful = userController.createUser(username, password, UserRole.ADMIN);
                    if (isSuccessful) {
                        System.out.println("Admin created successfully\n");
                        state = ADMIN_APPLICATION;
                    } else {
                        System.out.println("User creating failed\n");
                    }
                }
                default -> System.out.println("No such role. Try again\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter book id:");
            int bookId = Integer.parseInt(scanner.nextLine());

            boolean isSuccessful = bookController.deleteBook(bookId);
            if (isSuccessful) {
                System.out.println("Book deleted successfully\n");
                state = ADMIN_APPLICATION;
            } else {
                System.out.println("Book deleted failed\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

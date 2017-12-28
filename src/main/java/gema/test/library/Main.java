package gema.test.library;

import gema.test.library.config.DataConfig;
import gema.test.library.entity.Book;
import gema.test.library.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);
        BookService bookService = context.getBean(BookService.class);



        while (true) {
            System.out.println("1-add book \n" +
                    "2-delete book \n" +
                    "3-edit book \n" +
                    "4-show all books \n"+
                    "0-exit \n");


            try {
                switch (scanner.nextInt()) {
                    case 1:
                        bookService.save();
                        break;
                    case 2:

                        bookService.deleteBook();
                        break;
                    case 3:
                        bookService.editBook();
                        break;
                    case 4:
                        for (Book book:bookService.findAll()) {
                            System.out.println("'"+book.getNameOfBook()+"'"+" by "+book.getAuthor());
                        }
                        break;
                    case 0:
                        System.out.println("Bye");
                        return;
                    default:
                        System.out.println("No such operation");
                        break;

                }
            }catch (InputMismatchException e){
                System.out.println("No such operation");
                break;
            }catch (EmptyResultDataAccessException e){
                System.out.println("wrong id");
            }
        }

    }
}

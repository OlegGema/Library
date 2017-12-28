package gema.test.library.service.impl;

import gema.test.library.dao.BookDAO;
import gema.test.library.entity.Book;
import gema.test.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;
@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO dao;


    public void save() {
        System.out.println("Enter name of book");
        String nameOfBook=new Scanner(System.in).nextLine();
        System.out.println("Enter name of author");
        String nameOfAuthor=new Scanner(System.in).nextLine();

        if (!nameOfBook.isEmpty()&& nameOfBook!=null&&!nameOfAuthor.isEmpty()){
            dao.save(new Book(nameOfBook,nameOfAuthor));
            System.out.println("book "+nameOfAuthor+" '"+nameOfBook+"' was added");
        }else if (!nameOfBook.isEmpty()&&nameOfAuthor.isEmpty()||nameOfAuthor==null){
            dao.save(new Book(nameOfBook));
            System.out.println("book Unknow"+" '"+nameOfBook+" ' was added");
        }else {
            System.out.println("please enter name of book");
        }
    }

    public List<Book> findAll() {
        System.out.println("1-Sort by author \n" +
                "2-Sort by book name\n" +
                "3-Wihout sortig\n");
        Scanner scanner = new Scanner(System.in);
        while (true){
            switch (scanner.nextInt()){
                case 1:
                    return dao.findAll(new Sort(Sort.Direction.ASC,"author"));

                case 2:
                    return dao.findAll(new Sort(Sort.Direction.ASC,"nameOfBook"));

                case 3:
                    return dao.findAll();

                default:
                    System.out.println("no such option");
                    findAll();
            }
        }
    }

    public List<Book> findByBookName(String nameOfBook) {
        return dao.findByBookName(nameOfBook);
    }

    public Book findOne(int id) {
        return dao.findOne(id);
    }

    public void editBook() {
        System.out.println("Enter name of book");
        String nameOfBook=new Scanner(System.in).nextLine();
        List<Book> books = findByBookName(nameOfBook);

        System.out.println("What you wath change? \n" +
                "1-Name of book \n"+
                "2-Author \n");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()){
            case 1:
                if (books.size()>1){
                    int idOfBook=moreThanOneBook(books);
                    System.out.println("Enter new name of book");
                    String newNameOfBook=new Scanner(System.in).nextLine();
                    dao.setNewBookName(newNameOfBook,idOfBook);
                }else if (books.size()==1){
                    System.out.println("Enter new name of book");
                    String newNameOfBook=new Scanner(System.in).nextLine();
                    Book book = books.get(0);
                    dao.setNewBookName(newNameOfBook,book.getId());
                }else {
                    System.out.println("no such book");
                }
                break;
            case 2:
                if (books.size()>1){
                    int idOfBook=moreThanOneBook(books);
                    System.out.println("Enter new author");
                    String newAuthor=new Scanner(System.in).nextLine();
                    dao.setNewAuthor(newAuthor,idOfBook);
                }else if (books.size()==1){
                    System.out.println("Enter new author");
                    String newAuthor=new Scanner(System.in).nextLine();
                    Book book = books.get(0);
                    dao.setNewAuthor(newAuthor,book.getId());
                }else{
                    System.out.println("no such book");
                }
                break;
            default:
                System.out.println("no such operation");
                break;
        }
    }

    public void deleteBook() {
        System.out.println("Enter name of book");
        String nameOfBook=new Scanner(System.in).nextLine();
        List<Book>books=findByBookName(nameOfBook);
        if (books.size()>1){
            int id=(moreThanOneBook(books));
            dao.delete(id);
            System.out.println("book "+nameOfBook+" was deleted");
        }else if (books.size()==1){
            Book book = books.get(0);
            dao.delete(book.getId());
            System.out.println("book "+nameOfBook+" was deleted");
        }else {
            System.out.println("no such book");
        }


    }

    public int moreThanOneBook(List<Book>list){
        System.out.println("there a few books with this name");
        for (Book book:list) {
            System.out.println("id- "+book.getId()+" "+book.getNameOfBook()+" by "+book.getAuthor());
        }
        System.out.println("enter id of book that you want pick");
        int id=new Scanner(System.in).nextInt();
        return id;
    }
}
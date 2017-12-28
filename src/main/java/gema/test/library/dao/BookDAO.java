package gema.test.library.dao;

import gema.test.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDAO extends JpaRepository<Book,Integer>{
    @Query("from Book where nameOfBook=:nameOfBook")
    List<Book> findByBookName(@Param("nameOfBook") String nameOfBook);

    @Modifying
    @Query("update Book set nameOfBook=:nameOfBook where id=:id")
    void setNewBookName(@Param("nameOfBook")String nameOfBook,@Param("id")int id);

    @Modifying
    @Query("update Book set author=:author where id=:id")
    void setNewAuthor(@Param("author")String author,@Param("id")int id);
}

package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void t1(){
        Book book = Book.builder()
                .bookCode(1L)
                .bookName("JAVA의 정석")
                .publisher("이지퍼블리싱")
                .isbn("2222-1111")
                .build();
        // INSERT
//          bookRepository.save(book);

        // UPDATE
//          bookRepository.save(book);

        // DELETE
//          bookRepository.deleteById(1L);

        // SELECT
//        Optional<Book> bookOptional =
//            bookRepository.findById(1L);
//        Book rBook = null;
//        if(bookOptional.isPresent())
//        {
//         rBook = bookOptional.get();
//         System.out.println(rBook);
//        }

        // SELECTALL
        List<Book> list = bookRepository.findAll();
        list.forEach(System.out::println);

    }

    @DisplayName("-- 함수명명법 TEST --")
    @Test
     public void t2(){
//        List<Book> list = bookRepository.findByBookName("a");
//        list.forEach(System.out::println);

    List<Book> list = bookRepository.findByBookNameContains("d");
    list.forEach(System.out::println);
    }
//    public void t3(){
//        List<Book> list = bookRepository.findByBookName("b");
//        list.forEach(System.out::println);
//    }
//    public void t4(){
//        List<Book> list = bookRepository.findByBookName("c");
//        list.forEach(System.out::println);
//    }



}
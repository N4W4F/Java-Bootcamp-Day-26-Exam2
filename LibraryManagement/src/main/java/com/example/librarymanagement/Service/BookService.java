package com.example.librarymanagement.Service;

import com.example.librarymanagement.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {

    ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getBooks() {
        if (books.isEmpty())
            return null;

        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean updateBook(String id, Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.set(i, book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public Book getBookByName(String name) {
        for (Book b : books) {
            if (b.getName().equalsIgnoreCase(name))
                return b;
        }
        return null;
    }

    public ArrayList<Book> getBookByCategory(String category) {
        ArrayList<Book> newBooks = new ArrayList<>();

        for (Book b : books) {
            if (b.getCategory().equalsIgnoreCase(category)) {
                newBooks.add(b);
            }
        }
        if (newBooks.isEmpty())
            return null;

        return newBooks;
    }

    public ArrayList<Book> getBookByPages(int pages) {
        ArrayList<Book> newBooks = new ArrayList<>();

        for (Book b : books) {
            if (b.getNumber_of_pages() >= pages) {
                newBooks.add(b);
            }
        }
        if (newBooks.isEmpty())
            return null;

        return newBooks;
    }

    public String changeStatus(String id, String role) {
        if (role.equalsIgnoreCase("Librarian")) {
            for (Book b : books) {
                if (b.getId().equalsIgnoreCase(id)) {
                    b.setAvailable(false);
                    return "Yes";
                }
            }
            return "No";
        }
        else if (role.equalsIgnoreCase("customer"))
            return role;

        return "none";
    }
}

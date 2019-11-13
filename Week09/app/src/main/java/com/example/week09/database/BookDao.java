package com.example.week09.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.week09.model.Book;

import java.util.List;

@Dao
public interface BookDao {
    // Data Access Object for books.
    // Here I define all my methods that you are allowed to use to access the books table in the
    // database.
    //
    // The methods are annotated with @Query("SQL code"), essentially saying whenever this method
    // is called, run this SQL query and return the results.

    @Query("SELECT * FROM book ORDER BY rank ASC")
    List<Book> getAll();


    // Notice in this query where it has ":isbn". This notation means that we substitute a value
    // in the place of :isbn. What value is put in there? The long isbn that you can see in the
    // method parameter.
    @Query("SELECT * FROM book WHERE isbn = :isbn")
    Book findBookByIsbn(long isbn); // <<<=== the value of this isbn parameter will substitute the
                                    //        :isbn in the query.
                                    // e.g. if i call findBookByIsbn(100), it will run the query:
                                    //          SELECT * FROM book WHERE isbn = 100;


    // The onConflictStrategy determines how to handle when duplicate primary keys are inserted.
    // Without this, your app will only run once, and crash every time after because it keeps trying
    // to insert already existing primary keys.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Book> books);

    @Delete
    void delete(Book book);

}

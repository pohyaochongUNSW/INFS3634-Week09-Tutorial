package com.example.week09;

import com.example.week09.model.Book;

public interface AsyncTaskDelegate {
    // Whichever class implements this interface will then HAVE to implement handleTaskResult(String).
    // This means we can guarantee that any class with this interface knows how to handle a task result.

    // You may need to create more of these interfaces, if your results come in types other than String.
    void handleTaskResult(String result);

    void handleTaskResult(Book result);
}
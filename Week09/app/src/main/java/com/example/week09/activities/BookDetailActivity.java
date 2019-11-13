package com.example.week09.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.week09.AsyncTaskDelegate;
import com.example.week09.GetSingleBookAsyncTask;
import com.example.week09.R;
import com.example.week09.database.AppDatabase;
import com.example.week09.model.Book;

public class BookDetailActivity extends AppCompatActivity implements AsyncTaskDelegate {
    ConstraintLayout bookConstraintLayout;
    TextView titleTextView;
    TextView authorTextView;
    TextView descriptionTextView;
    TextView rankTextView;
    ImageView coverImageView;
    public static Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookConstraintLayout = findViewById(R.id.book);
        titleTextView = bookConstraintLayout.findViewById(R.id.tv_title);
        authorTextView = bookConstraintLayout.findViewById(R.id.tv_author);
        rankTextView = bookConstraintLayout.findViewById(R.id.tv_rank);
        coverImageView = bookConstraintLayout.findViewById(R.id.iv_cover);
        descriptionTextView = findViewById(R.id.tv_description);

        Intent intent = getIntent();

        long isbn = intent.getLongExtra("isbn", 0);
        AppDatabase db = AppDatabase.getInstance(this);
        // Book book = db.bookDao().findBookByIsbn(isbn);
        GetSingleBookAsyncTask getSingleBookAsyncTask = new GetSingleBookAsyncTask(db);
        getSingleBookAsyncTask.setDelegate(BookDetailActivity.this);

        getSingleBookAsyncTask.execute(isbn);


    }

    @Override
    public void handleTaskResult(String result) {

    }

    @Override
    public void handleTaskResult(Book result) {
        book = result;

        titleTextView.setText(book.getTitle());
        authorTextView.setText(book.getAuthor());
        rankTextView.setText("#" + String.valueOf(book.getRank()));
        descriptionTextView.setText(book.getDescription());

        String imageUrl = book.getBookImage();
        Glide.with(this).load(imageUrl).into(coverImageView);
    }
}

package de.kulturbremen.memorize.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.ui.edit.EditQuiz;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void addQuiz(View view){
        Intent intent = new Intent(this, EditQuiz.class);
        startActivity(intent);
    }
}
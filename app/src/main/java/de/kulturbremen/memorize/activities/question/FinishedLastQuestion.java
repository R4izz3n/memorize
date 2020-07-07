package de.kulturbremen.memorize.activities.question;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import de.kulturbremen.memorize.R;
import de.kulturbremen.memorize.activities.main.MainActivity;

public class FinishedLastQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_last_question);
    }

    /** called when the return button is tapped */
    public void returnToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
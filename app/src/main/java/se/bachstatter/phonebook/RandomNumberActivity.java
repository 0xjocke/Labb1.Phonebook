package se.bachstatter.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


import java.util.Random;


public class RandomNumberActivity extends Activity implements OnClickListener {
    /**
     * Class variables for randomBtn and randomNumberfield.
     */
    private Button randomBtn;
    private EditText randomNumberField;


    /**
     *  Finds the numberfield and the button and saves it to  respectives class variable
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);
        randomNumberField = (EditText)findViewById(R.id.randomNumberField);
        randomBtn = (Button)findViewById(R.id.randomBtn);
        randomBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phonebook, menu);
        return true;
    }

    /**
     * Handles menu clicks. Checks which item is clicked and
     * starts that intent
     * If its current page the user gets a toast with instructions
     *
     * @param item
     * @return super.onOptionsItemSelected(item);
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch(id){
            case R.id.contactBtn:
                intent = new Intent(this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.randomBtn:
                Toast.makeText(this, "Klicka på knappen för att få ditt nummer", Toast.LENGTH_LONG).show();
                break;
            case R.id.backgroundBtn:
                intent = new Intent(this, ChangeBackgroundActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Generate random number with 2 decimals between 0 - 1000
     * Initialize a Random, runs nextDouble which returns a random number between 0-1.
     * Then that number gets multiplied bi 1000.
     * After that the number gets rounded to 2 decimals.
     * And finaly put the number to the numberField
     */
    public void generateRandomNumber() {
        Random rand = new Random();
        Double randomNr = rand.nextDouble() * 1000;
        randomNr = Math.round(randomNr * 100.0) / 100.0;
        randomNumberField.setText(randomNr+"");

    }

    /**
     * If its randomBtn that is clicked run generateRandomNumber()
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.randomBtn:
                generateRandomNumber();
        }

    }
}

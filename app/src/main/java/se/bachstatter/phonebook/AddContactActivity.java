package se.bachstatter.phonebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;


public class AddContactActivity extends Activity implements OnClickListener {
    /**
     * Class variables for textfields and contact button.
     */
    private EditText editTextName, editTextPhone, editTextEmail;
    private Button addContactBtn;

    /**
     * Finds the textfields/button and saves it to its global class variable.
     * The button also get a clicklistener nad since I implements OnClickListener
     * the clicklistener get the contect as a parameter.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextPhone = (EditText)findViewById(R.id.editTextPhone);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        addContactBtn = (Button)findViewById(R.id.addContactBtn);
        addContactBtn.setOnClickListener(this);

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
        Intent intent = null;
        int id = item.getItemId();
        switch(id){
            case R.id.contactBtn:
                Toast.makeText(this, "Fyll i fälten och klicka på 'Lägg till' knappen", Toast.LENGTH_LONG).show();
                break;
            case R.id.randomBtn:
                intent = new Intent(this, RandomNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.backgroundBtn:
                intent = new Intent(this, ChangeBackgroundActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * If its randomBtn that is clicked run addContact()
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.addContactBtn:
                addContact();
        }
    }

    /**
     * Get the string from editTextname field.
     * if that string is empty set error
     * else initialize a new Contact intent and
     * put all the values from the textfields to the intent.
     *
     * When its done startactivity.
     * (even if number or/and email is empty )
     */
    private void addContact() {
        String name = editTextName.getText().toString();
        if (name.equals("")) {
            editTextName.setError("Du måste fylla i ett namn");
        } else {
            // Creates a new Intent to insert a contact
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            // Sets the MIME type to match the Contacts Provider
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, editTextName.getText().toString())
                    .putExtra(ContactsContract.Intents.Insert.EMAIL, editTextEmail.getText().toString())
                    .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                    .putExtra(ContactsContract.Intents.Insert.PHONE, editTextPhone.getText())
                    .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK);

            startActivity(intent);
        }
    }
}

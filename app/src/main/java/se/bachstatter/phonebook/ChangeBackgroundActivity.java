package se.bachstatter.phonebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import se.bachstatter.phonebook.External_Classes.ColorPicker;


public class ChangeBackgroundActivity extends Activity{

    /**
     * Class variables for RelativeLayout.
     */
    private RelativeLayout changeBgView;

    /**
     * Finds the RelativeLayout and saves it to the global class variable
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);
        changeBgView = (RelativeLayout)findViewById(R.id.changeBgView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phonebook, menu);
        return true;
    }

    /**
     * Handles menu clicks. Checks which item is clicked and
     * initialize that intent
     * If its the current page the user gets a toast with instructions
     *
     * @param item
     * @return super.onOptionsItemSelected(item);
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        int id = item.getItemId();
        switch(id){
            case R.id.contactBtn:
                intent = new Intent(this, AddContactActivity.class);
                startActivity(intent);
                break;
            case R.id.randomBtn:
                intent = new Intent(this, RandomNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.backgroundBtn:
                Toast.makeText(this, "Klicka på byt bakgrund", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Gets called when a user clicks on changeBgBtn.
     * Creates new instance of ColorpickerDialog and
     * and sends a OnColorSelectedListener to its constructor.
     * After that is runs show().
     *
     * @param v
     */
    public void changeBackground(View v){
        ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, Color.WHITE, new ColorPickerDialog.OnColorSelectedListener() {
            /**
             * When user picks a color I will get that chosen color in param 'color'
             * this color get sets as background to the relativeView
             * @param color
             */
            @Override
            public void onColorSelected(int color) {
                changeBgView.setBackgroundColor(color);

            }
        });
        colorPickerDialog.show();
    }
}

 class ColorPickerDialog extends AlertDialog {

    private ColorPicker colorPickerView;
    private final OnColorSelectedListener onColorSelectedListener;

    public ColorPickerDialog(Context context, int initialColor, OnColorSelectedListener onColorSelectedListener) {
        super(context);

        this.onColorSelectedListener = onColorSelectedListener;

        RelativeLayout relativeLayout = new RelativeLayout(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        colorPickerView = new ColorPicker(context);
        colorPickerView.setColor(initialColor);

        relativeLayout.addView(colorPickerView, layoutParams);

        setButton(BUTTON_POSITIVE, context.getString(android.R.string.ok), onClickListener);
        setButton(BUTTON_NEGATIVE, context.getString(android.R.string.cancel), onClickListener);

        setView(relativeLayout);

    }

    private OnClickListener onClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case BUTTON_POSITIVE:
                    int selectedColor = colorPickerView.getColor();
                    onColorSelectedListener.onColorSelected(selectedColor);
                    break;
                case BUTTON_NEGATIVE:
                    dialog.dismiss();
                    break;
            }
        }
    };

    public interface OnColorSelectedListener {
        public void onColorSelected(int color);
    }

}
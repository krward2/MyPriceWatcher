package edu.utep.cs.cs4330.mypricewatcher;
/**
 * @author Kenneth Ward
 * @version 1.0
 * @since 2019-2-20
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import java.lang.Math;

/**
 * Encodes UI elements for the main page of the application.
 * Includes text elements for displaying the current and initial prices,
 * as well as buttons for updating the current price and viewing the item's webpage.
 */
public class MainActivity extends AppCompatActivity implements EditNameDialog.EditDialogListener{
    /*In the future all of the following fields will be specified by the user*/
    /* The item to be monitored*/
    private Item watchedItem;
    /*The item's url*/
    private String url;
    /*The items's name, as displayed in the app*/
    private String name;

    TextView textName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Specifying the items parameters and instantiating it.*/
        name = "Nvidia GEFORCE GTX 1080 Ti";
        url = "https://www.amazon.com/Nvidia-GEFORCE-GTX-1080-Ti/dp/B06XH5ZCLP/" +
                "ref=sr_1_2?keywords=nvidia+1080+ti&qid=1550694460&s=gateway&sr=8-2";
        watchedItem = new Item(name, url);

        /*Displays the name*/
        textName = findViewById(R.id.textName);
        textName.setText(name);

        /*Displays the initial price*/
        TextView textInitialPrice = findViewById(R.id.textInitialPrice);
        textInitialPrice.setText("Initial Price: $" + formatDouble(watchedItem.getPriceInitial()));

        /*Displays the current price*/
        TextView textCurrentPrice = findViewById(R.id.textCurrentPrice);
        textCurrentPrice.setText("Current Price: $" + formatDouble(watchedItem.getPriceCurrent()));

        /*Displays the price change (percent)*/
        TextView textPriceChange = findViewById(R.id.textPriceChange);
        textPriceChange.setText("Price Change: " + formatDouble(watchedItem.getPriceChange()) + "%");

        /*Creates a listener for the update button*/
        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this::updateClicked);

        /*Creates a listener for the web page button*/
        Button buttonWebPage = findViewById(R.id.buttonWebPage);
        buttonWebPage.setOnClickListener(this::webPageClicked);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int viewToEditID;
        String viewToEditName;
        switch(item.getItemId()){
            case R.id.editMenuButtonName:
                viewToEditID = R.id.textName;
                viewToEditName = (String)item.getTitle();
                openEditDialog(viewToEditID, viewToEditName);
                return true;
            case R.id.editMenuButtonUrl:
                viewToEditID = 0;
                viewToEditName = (String)item.getTitle();
                openEditDialog(viewToEditID, viewToEditName);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyText(int viewToEditID, String newName) {
        if(viewToEditID == 0){
            url = newName;
        }
        else{
            TextView viewToEdit = findViewById(viewToEditID);
            viewToEdit.setText(newName);
        }
    }


    private void openEditDialog(int viewToEditID, String viewToEditName){
        Bundle bundle = new Bundle();
        bundle.putString("viewToEditName", viewToEditName);
        bundle.putInt("viewToEditID", viewToEditID);
        EditNameDialog dialog = new EditNameDialog();
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "Edit Dialog");
    }

    /**
     * Listens for activity from the "buttonUpdate" element. Upon click, retrieves the current price
     * of the item, and updates the "textCurrentPrice" and "textPriceChange" elements
     * accordingly.
     * @param view View object
     */
    private void updateClicked(View view){
        watchedItem.updatePrice();
        TextView textCurrentPrice = findViewById(R.id.textCurrentPrice);
        textCurrentPrice.setText("Current Price: $" + formatDouble(watchedItem.getPriceCurrent()));
        TextView textPriceChange = findViewById(R.id.textPriceChange);
        textPriceChange.setText("Price Change: " + formatDouble(watchedItem.getPriceChange()) + "%");
    }

    /**
     * Listens for the activity from the "buttonWebPage" element. Upon click, declares an
     * intent which accesses the default browser and takes the user to the item's web page.
     * @param view
     */
    private void webPageClicked(View view){
        Intent getWebPage = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(getWebPage);

    }

    /**
     * Used to format the price and percent change doubles returned from the "Item"
     * class so that they are represented as strings to the nearest hundredth.
     * @param d The prices and percent change of the item
     * @return Returns a formatted string representation of the given double ##...##.##
     */
    private String formatDouble(double d){
        /*Wraps the double so it can be converted to a string*/
        Double dWrapper = d;
        /*If zero the following operations are invalid*/
        if(d == 0) return"0.00";

        /*Determines how many numbers before the decimal point*/
        int magnitude = (int)Math.log10(d);

        /*Determines the number of characters before decimal point, and the two after
         * including the decimal point itself*/
        magnitude += 4;

        /*Slices the string so that all numbers before the decimal and the two following it are included*/
        return dWrapper.toString().substring(0, magnitude);
    }
}

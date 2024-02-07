package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private TextView textViewGreetings;
    private TextView textViewAdditives;


    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;


    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLime;

    private Spinner spinnerTea;
    private Spinner spinnerCoffee;


    private Button buttonMakeOrder;

    private String drink;
    private String userName;
    private String drinkType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        initViews();
        setupUserName();

        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (id == radioButtonTea.getId()) {
                    onUserChoseTea();
                } else if (id == radioButtonCoffee.getId()) {
                    onUserChoseCoffee();
                }
            }
        });
        radioButtonTea.setChecked(true);

        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserMadeOrder();
            }
        });
    }

    private void launchNextScreen(String userName, String drink, String drinkType, String checkedElement) {
        Intent intent = OrderDetailActivity.newIntent(this, userName, drink, drinkType, checkedElement);
        startActivity(intent);
    }

    private void onUserMadeOrder() {
        ArrayList<String> checkedGroup = new ArrayList<>();

        if (checkBoxSugar.isChecked()) {
            checkedGroup.add(checkBoxSugar.getText().toString());
        }

        if (checkBoxMilk.isChecked()) {
            checkedGroup.add(checkBoxMilk.getText().toString());
        }

        if (radioButtonTea.isChecked() && checkBoxLime.isChecked()) {
            checkedGroup.add(checkBoxLime.getText().toString());
        }


        drinkType = "";
        if (radioButtonTea.isChecked()) {
            drinkType = spinnerTea.getSelectedItem().toString();
        } else if (radioButtonCoffee.isChecked()) {
            drinkType = spinnerCoffee.getSelectedItem().toString();
        }

        launchNextScreen(userName, drink, drinkType, checkedGroup.toString());
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

    private void onUserChoseTea() {
        drink = getString(R.string.tea);
        String additivesLable = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLable);
        checkBoxLime.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee() {
        drink = getString(R.string.coffee);
        String additivesLable = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLable);
        checkBoxLime.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        textViewGreetings = findViewById(R.id.textViewGreetings);
        textViewAdditives = findViewById(R.id.textViewAdditives);

        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);


        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxLime = findViewById(R.id.checkBoxLime);

        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
    }

    private void setupUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings, userName);
        textViewGreetings.setText(greetings);
    }


}
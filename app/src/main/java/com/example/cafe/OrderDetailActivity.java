package com.example.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;


public class OrderDetailActivity extends AppCompatActivity {
    private final static String EXTRA_USER_NAME = "userName";
    private final static String EXTRA_DRINK = "drink";
    private final static String EXTRA_DRINK_TYPE = "drinkType";
    private final static String EXTRA_CHECKED_ELEMENT = "checkedElement";

    private TextView textViewName;
    private TextView textViewDrink;
    private TextView textViewTypeDrink;
    private TextView textViewDobavki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initeViews();
        setValueViews();

    }

    private void initeViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewTypeDrink = findViewById(R.id.textViewTypeDrink);
        textViewDobavki = findViewById(R.id.textViewDobavki);
    }

    private void setValueViews(){
        textViewName.setText(getIntent().getStringExtra(EXTRA_USER_NAME));
        textViewDrink.setText(getIntent().getStringExtra(EXTRA_DRINK));
        textViewTypeDrink.setText(getIntent().getStringExtra(EXTRA_DRINK_TYPE));
        textViewDobavki.setText(getIntent().getStringExtra(EXTRA_CHECKED_ELEMENT));
    }

    public static Intent newIntent(Context context, String userName, String drink, String drinkType, String checkedElement) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        intent.putExtra(EXTRA_CHECKED_ELEMENT, checkedElement);
        return intent;
    }
}
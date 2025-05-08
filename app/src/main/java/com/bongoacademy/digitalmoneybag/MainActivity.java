package com.bongoacademy.digitalmoneybag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvFinalBalance,tvShowAllDataExpense,tvAddExpense,tvAddIncome,
            tvShowAllDataIncome,tvTotalIncome,tvTotalExpense;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvFinalBalance=findViewById(R.id.tvFinalBalance);
        tvShowAllDataExpense=findViewById(R.id.tvShowAllDataExpense);
        tvAddExpense=findViewById(R.id.tvAddExpense);
        tvAddIncome=findViewById(R.id.tvAddIncome);
        tvShowAllDataIncome=findViewById(R.id.tvShowAllDataIncome);
        tvTotalIncome=findViewById(R.id.tvTotalIncome);
        tvTotalExpense=findViewById(R.id.tvTotalExpense);
        dbhelper = new DatabaseHelper(this);

        tvAddExpense.setOnClickListener(v->{

            startActivity(new Intent(MainActivity.this,AddData.class));

        });

        updateUi();


    }

    //===================================================================
public void updateUi(){
        tvTotalExpense.setText("BDT: "+dbhelper.CalculatetotalExpense());
}

    //===================================================================


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUi();
    }
}
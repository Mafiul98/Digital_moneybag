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

            AddData.EXPENSE = true;
            startActivity(new Intent(MainActivity.this,AddData.class));

        });

        tvAddIncome.setOnClickListener(v->{

            AddData.EXPENSE = false;
            startActivity(new Intent(MainActivity.this,AddData.class));

        });

        tvShowAllDataExpense.setOnClickListener(v->{
            ShowData.EXPENSE = true;
            startActivity(new Intent(MainActivity.this, ShowData.class));

        });

        tvShowAllDataIncome.setOnClickListener(v->{
            ShowData.EXPENSE = false;
            startActivity(new Intent(MainActivity.this, ShowData.class));

        });


        updateUi();

    }

    //===================================================================
public void updateUi(){
        tvTotalExpense.setText("BDT: "+dbhelper.CalculatetotalExpense() );
        tvTotalIncome.setText("BDT: "+dbhelper.CalculatetotalIncome() );
        double Balance = dbhelper.CalculatetotalIncome()-dbhelper.CalculatetotalExpense();
        tvFinalBalance.setText("BDT: "+Balance);
}

    //===================================================================


    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateUi();
    }
}
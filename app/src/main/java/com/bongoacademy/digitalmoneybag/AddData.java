package com.bongoacademy.digitalmoneybag;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddData extends AppCompatActivity {

    TextView tvTitle;
    EditText edAmount,edReason;
    Button button;
    DatabaseHelper dbhelper;

    public static boolean EXPENSE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        tvTitle=findViewById(R.id.tvTitle);
        edAmount=findViewById(R.id.edAmount);
        edReason=findViewById(R.id.edReason);
        button=findViewById(R.id.button);
        dbhelper = new DatabaseHelper(this);


        if (EXPENSE==true) tvTitle.setText("Add Expense");

        button.setOnClickListener(v->{

            if (edAmount.length()>0 && edReason.length()>0){
                String Aamount = edAmount.getText().toString();
                String reason = edReason.getText().toString();
                Double amount = Double.parseDouble(Aamount);

                if (EXPENSE==true){
                    dbhelper.addExpense(amount,reason);
                    tvTitle.setText("Expense Add");
                }else {
                    dbhelper.addIncome(amount,reason);
                    tvTitle.setText("Income Add");
                }

                edAmount.setText("");
                edReason.setText("");


            }else Toast.makeText(AddData.this,"Please Insert Data",Toast.LENGTH_LONG).show();

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
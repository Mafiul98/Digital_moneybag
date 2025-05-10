package com.bongoacademy.digitalmoneybag;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowData extends AppCompatActivity {

    TextView tvtitle;
    ListView listview;
    DatabaseHelper dbhelper;
    ArrayList<HashMap<String,String>> arrayList;
    HashMap<String,String> hashMap;

    public static boolean EXPENSE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#ffffff"));}
        WindowCompat.getInsetsController(getWindow(),getWindow().getDecorView())
                .setAppearanceLightStatusBars(true);
        setContentView(R.layout.activity_show_data);
        tvtitle=findViewById(R.id.tvtitle);
        listview=findViewById(R.id.listview);
        dbhelper = new DatabaseHelper(this);

        if (EXPENSE == true) tvtitle.setText("Expense List");
        else tvtitle.setText("Income List");

       loadData();

    }

    //=================================================================
    public void loadData (){

        Cursor cursor = null;
        if (EXPENSE==true) cursor = dbhelper.getAllExpense();
        else cursor = dbhelper.getAllIncome();

        if (cursor!=null & cursor.getCount()>0){

            arrayList = new ArrayList<>();

            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);

                hashMap = new HashMap<>();
                hashMap.put("id",String.valueOf(id));
                hashMap.put("amount",String.valueOf(amount));
                hashMap.put("reason",String.valueOf(reason));
                hashMap.put("time",String.valueOf(time));
                arrayList.add(hashMap);
            }

            listview.setAdapter(new myAdpter());

        }else {
            tvtitle.setText("No Data Found");
        }



    }
    //=================myadapter============================================
    public class myAdpter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myview = layoutInflater.inflate(R.layout.item,parent,false);
            TextView tvamount = myview.findViewById(R.id.tvamount);
            TextView tvreason = myview.findViewById(R.id.tvReason);
            ImageView delete = myview.findViewById(R.id.delete);
            TextView title = myview.findViewById(R.id.title);
            TextView tvTime = myview.findViewById(R.id.tvTime);
            if (EXPENSE==true){
                title.setText("Expense");
            }
            else {
                title.setText("Income");
               tvamount . setTextColor(getResources().getColor(R.color.green));
            }

            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String amount = hashMap.get("amount");
            String reason = hashMap.get("reason");
            String time = hashMap.get("time");

            tvamount.setText("BDT: "+amount);

            // ===== Date format করা হচ্ছে =====
            long timeInMillis = (long) Double.parseDouble(time);
            Date date = new Date(timeInMillis);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            String formattedDate = sdf.format(date);
            // ==================================

            tvTime.setText(formattedDate);
            tvreason.setText(reason);

            delete.setOnClickListener(v->{

                if (EXPENSE==true)dbhelper.deleteExpense(id);
                else dbhelper.deleteIncome(id);

                loadData();

            });

            return myview;
        }
    }
}
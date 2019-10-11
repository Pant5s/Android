package com.npdevs.healthcastle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckSafe extends AppCompatActivity {
	private EditText food,amount;
	private Button check;
	DatabaseHelper databaseHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_safe);
		food = findViewById(R.id.editText9);
		amount = findViewById(R.id.editText10);
		check = findViewById(R.id.button6);
		databaseHelper = new DatabaseHelper(this);
		check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String str1 = food.getText().toString().trim();
				String str2 = amount.getText().toString().trim();
				if(str1.equals(null) || str2.equals(null)){
					Toast.makeText(getApplicationContext(),"Enter values!!!",Toast.LENGTH_SHORT).show();
				}
				else{
					Cursor res = databaseHelper.getAllData();
					if(res.getCount()==0){
						Toast.makeText(getApplicationContext(),"No Data!!!",Toast.LENGTH_SHORT).show();
					}
					else{
						boolean flag = true;
						while (res.moveToNext()){
							// Log.e("Ashu",res.getString(1)+" "+res.getInt(2)+" "+res.getInt(3));
							if(res.getString(1).equals(str1)){
								flag = false;
								int x = res.getInt(2);
								int y = res.getInt(3);
								int z = Integer.parseInt(str2);
								int ans = (x*z)/y;
								AlertDialog.Builder builder = new AlertDialog.Builder(CheckSafe.this);
								builder.setCancelable(true);
								builder.setTitle("Calorie Calculated");
								builder.setMessage(ans+"");
								builder.show();
								break;
							}
						}
						if(flag){
							Toast.makeText(CheckSafe.this,"Food Item Not Found!!!",Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		});
	}
}
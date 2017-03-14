package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import utilities.AlertDialogManager;

import static utilities.CommonUtilities.SENDER_ID;
import static utilities.CommonUtilities.SERVER_URL;
public class RegisterActivity extends Activity {


	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Internet detector
	ConnectionDetector cd;
	CheckBox pak, ind, sa,eng,aus,sl,zim,nz,wi,uae,ken;
	String temp,temp2,temp3,temp4,temp5,temp6,temp7,temp8,temp9,temp10,day;

	// Register button
	Button btnRegister,back;
    String key,type,inn;
    Intent i;
    LinearLayout layout;
    Advertise advertise;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
        layout= (LinearLayout)findViewById(R.id.add);
        advertise = new Advertise();
        advertise.Banner(layout, this);
        i = getIntent();
        key = i.getStringExtra("key");
        type = i.getStringExtra("type");
        inn = i.getStringExtra("innings");
        day = i.getStringExtra("day");
        temp="0";
        temp2="0";
        temp3="0";
        temp4="0";
        temp5="0";
        temp6="0";
        temp7="0";
        temp8="0";
        temp9="0";
        temp10="0";
        back=(Button)findViewById(R.id.back);
		pak=(CheckBox)findViewById(R.id.pak);
		ind=(CheckBox)findViewById(R.id.ind);
		sa=(CheckBox)findViewById(R.id.sa);
		eng=(CheckBox)findViewById(R.id.eng);
		aus=(CheckBox)findViewById(R.id.aus);
		sl=(CheckBox)findViewById(R.id.sl);
        zim=(CheckBox)findViewById(R.id.zim);
        nz=(CheckBox)findViewById(R.id.nz);
        uae=(CheckBox)findViewById(R.id.uae);
        ken=(CheckBox)findViewById(R.id.ken);
       SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        final boolean checkBoxValue1 = sharedPreferences.getBoolean("CheckBox_Value1", false);
        final boolean checkBoxValue2 = sharedPreferences.getBoolean("CheckBox_Value2", false);
        final boolean checkBoxValue3 = sharedPreferences.getBoolean("CheckBox_Value3", false);
        final boolean checkBoxValue4 = sharedPreferences.getBoolean("CheckBox_Value4", false);
        final boolean checkBoxValue5 = sharedPreferences.getBoolean("CheckBox_Value5", false);
        final boolean checkBoxValue6 = sharedPreferences.getBoolean("CheckBox_Value6", false);
        final boolean checkBoxValue7 = sharedPreferences.getBoolean("CheckBox_Value7", false);
        final boolean checkBoxValue8 = sharedPreferences.getBoolean("CheckBox_Value8", false);
        final boolean checkBoxValue9 = sharedPreferences.getBoolean("CheckBox_Value9", false);
        final boolean checkBoxValue10 = sharedPreferences.getBoolean("CheckBox_Value10", false);



		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(RegisterActivity.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			 return;
		}

		Log.d("Res", "" + pak.isChecked());

back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), FullCardTesting.class);
                i.putExtra("key", key);
                i.putExtra("type", type);
                i.putExtra("innings",inn);
                i.putExtra("day",day);
                startActivity(i);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
    }
});

        if (checkBoxValue1) {
            pak.setChecked(true);
            temp="Pakistan";
        }
        else
        {
            pak.setChecked(false);
        }
        if (checkBoxValue2) {
            aus.setChecked(true);
            temp2="Australia";
        }
        else
        {
            aus.setChecked(false);
        }
        if (checkBoxValue3) {
            ind.setChecked(true);
            temp3="India";
        }
        else
        {
            ind.setChecked(false);
        }
        if (checkBoxValue4) {
            sl.setChecked(true);
            temp4="Sri Lanka";
        }
        else
        {
            sl.setChecked(false);
        }
        if (checkBoxValue5) {
            eng.setChecked(true);
            temp5="England";
        }
        else
        {
            eng.setChecked(false);
        }
        if (checkBoxValue6) {
            sa.setChecked(true);
            temp6="South Africa";
        }
        else
        {
            sa.setChecked(false);
        }
        if (checkBoxValue7) {
            uae.setChecked(true);
            temp7="Bangladesh";
        }
        else
        {
            uae.setChecked(false);
        }
        if (checkBoxValue8) {
            ken.setChecked(true);
            temp8="Kenya";
        }
        else
        {
            ken.setChecked(false);
        }
        if (checkBoxValue9) {
            nz.setChecked(true);
            temp9="New Zealand";
        }
        else
        {
            nz.setChecked(false);
        }
        if (checkBoxValue10) {
            zim.setChecked(true);
            temp10="Zimbabve";
        }
        else
        {
            zim.setChecked(false);
        }
        pak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;


                    if (checkBox.isChecked()) {

                            savePreferences("CheckBox_Value1",true);

                        temp = "Pakistan";
                        Log.d("Res", "" + temp);
                    } else {
                        savePreferences("CheckBox_Value1",false);
                        temp = "0";
                        Log.d("Res", "" + temp);
                    }

            }
        });
        aus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value2",true);
                    temp2="Australia";
                    Log.d("Res",""+temp2);
                }
                else
                {
                    savePreferences("CheckBox_Value2",false);
                    temp2="0";
                    Log.d("Res",""+temp2);
                }
            }
        });

        ind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value3",true);
                    temp3="India";
                    Log.d("Res",""+temp3);
                }
                else
                {
                    savePreferences("CheckBox_Value3",false);
                    temp3="0";
                    Log.d("Res",""+temp3);
                }
            }
        });
        sl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value4",true);
                    temp4="Sri Lanka";
                }
                else
                {
                    savePreferences("CheckBox_Value4",false);
                    temp4="0";
                }
            }
        });
        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value5",true);
                    temp5="England";
                }
                else
                {
                    savePreferences("CheckBox_Value5",false);
                    temp5="0";
                }
            }
        });

        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value6",true);
                    temp6="South Africa";
                }
                else
                {
                    savePreferences("CheckBox_Value6",false);
                    temp6="0";
                }
            }
        });

        uae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value7",true);
                    temp7="Bangladesh";
                }
                else
                {
                    savePreferences("CheckBox_Value7",false);
                    temp7="0";
                }
            }
        });
        ken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value8",true);

                    temp8="Kenya";
                }
                else
                {
                    savePreferences("CheckBox_Value8",false);
                    temp8="0";
                }
            }
        });
        nz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value9",true);
                    temp9="New Zealand";
                }
                else
                {
                    savePreferences("CheckBox_Value9",false);
                    temp9="0";
                }
            }
        });
        zim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    savePreferences("CheckBox_Value10",true);
                    temp10="Zimbabve";
                }
                else

                {
                    savePreferences("CheckBox_Value10",false);
                    temp10="0";
                }
            }
        });

		btnRegister = (Button) findViewById(R.id.btnRegister);
		
		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
             /*   if(temp.equalsIgnoreCase("Pakistan")) {
                    savePreferences("CheckBox_Value1", pak.isChecked());
                }
                else
                {
                    savePreferences("CheckBox_Value1", !(pak.isChecked()));
                }
               if(temp2.equalsIgnoreCase("Australia")) {
                    savePreferences("CheckBox_Value2", aus.isChecked());
                }
               else
               {
                   savePreferences("CheckBox_Value1", !(aus.isChecked()));
               }
                if(temp3.equalsIgnoreCase("India")) {
                    savePreferences("CheckBox_Value3", ind.isChecked());
                }
                else
                {
                    savePreferences("CheckBox_Value1", !(ind.isChecked()));
                }
               if(temp4.equalsIgnoreCase("Sri Lanka")) {
                    savePreferences("CheckBox_Value4", sl.isChecked());
                }        else
               {
                   savePreferences("CheckBox_Value1", !(sl.isChecked()));
               }
                 if(temp5.equalsIgnoreCase("England")) {
                    savePreferences("CheckBox_Value5", eng.isChecked());
                }
                 else
                 {
                     savePreferences("CheckBox_Value1", !(eng.isChecked()));
                 }

                if(temp6.equalsIgnoreCase("South Africa")) {
                    savePreferences("CheckBox_Value6", sa.isChecked());
                }  else
                {
                    savePreferences("CheckBox_Value1", !(sa.isChecked()));
                }
                if(temp7.equalsIgnoreCase("Bangladesh")) {
                    savePreferences("CheckBox_Value7", uae.isChecked());
                }
                else
                {
                    savePreferences("CheckBox_Value1", !(uae.isChecked()));
                }
                if(temp8.equalsIgnoreCase("Kenya")) {
                    savePreferences("CheckBox_Value8", ken.isChecked());
                }
                else
                {
                    savePreferences("CheckBox_Value1", !(ken.isChecked()));
                }
                    if(temp9.equalsIgnoreCase("New Zealand")) {
                    savePreferences("CheckBox_Value9", nz.isChecked());
                }
                    else {
                        savePreferences("CheckBox_Value1", !(nz.isChecked()));
                    }
                        if(temp10.equalsIgnoreCase("Zimbabve")) {
                    savePreferences("CheckBox_Value10", zim.isChecked());
                }
                        else {
                            savePreferences("CheckBox_Value1", !(zim.isChecked()));
                        }*/


                            Intent i = new Intent(getApplicationContext(), DemActivity.class);
					
					// Registering user on our server					
					// Sending registraiton details to MainActivity
					i.putExtra("country1", temp);
					i.putExtra("country2", temp2);
				i.putExtra("country3", temp3);
				i.putExtra("country4", temp4);
				i.putExtra("country5", temp5);
				i.putExtra("country6", temp6);
                i.putExtra("country7", temp7);
                i.putExtra("country8", temp8);
                i.putExtra("country9", temp9);
                i.putExtra("country10", temp10);
                i.putExtra("key", key);
                i.putExtra("type", type);
                i.putExtra("innings",inn);
                i.putExtra("day",day);
					startActivity(i);
					finish();

			}
		});
	}
    private void savePreferences(String key, boolean  value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
}

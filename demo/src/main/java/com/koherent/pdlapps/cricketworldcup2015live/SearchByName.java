package com.koherent.pdlapps.cricketworldcup2015live;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.playerstatitem;
import utilities.CommonMethods;
import utilities.Constants;
import adapter.PlayerStatAdapter;
import adapter.ServiceHandler;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class SearchByName extends Activity {

    Button back;
    EditText inputSearch;
    PlayerStatAdapter adapter;
    ArrayList<playerstatitem> playerobject;
    String url = Constants.SearchName_URL;
    String jsonStr;
    JSONArray arr = null;
    ServiceHandler sh;
    JSONArray contacts = null;
    private ListView listView;
    int textlength = 0;
    Intent i;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_name);
        back=(Button)findViewById(R.id.back);
        listView=(ListView)findViewById(R.id.listname);
        inputSearch = (EditText)findViewById(R.id.inputSearch);

        playerobject=new ArrayList<playerstatitem>();
        i=getIntent();
        s=i.getStringExtra("Name");
        Log.d("Response S",s);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), Player_Stats.class);
                startActivity(i);
                finish();
            }
        });

        if(CommonMethods.isNetworkAvailable(getApplicationContext())) {

            new PlayerState().execute();
        }
        else {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
    }

    private class PlayerState extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            CommonMethods.showProgressDialog(SearchByName.this);

        }

        @Override
        protected Void doInBackground(String... Url) {
            try {
                sh = new ServiceHandler();

                // Making a request to url and getting response
                jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();


            }
            return null;

        }

        @Override
        protected void onPostExecute(Void args) {

            CommonMethods.hideProgressDialog();
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    contacts = jsonObj.getJSONArray("Toqeer");
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String name = c.getString("Player Name");
                        final String country = c.getString("Country");

                        final String pid=c.getString("Player_Id");
                        final String cover_imae=c.getString("Cover_Image");
                        final String pprofile=c.getString("Profile_Pic");
                        Log.d("Response", "" + jsonStr);
                        if (s.equalsIgnoreCase("name")) {
                            playerobject.add(new playerstatitem(name,country,pid,pprofile,cover_imae));

                            adapter = new PlayerStatAdapter(getApplicationContext(), playerobject);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    playerstatitem item = playerobject.get(i);
                                    Toast.makeText(getApplicationContext(), "" + item.getName(), Toast.LENGTH_SHORT).show();
                                    Intent in=new Intent(getApplicationContext(),PlayerStatDetail.class);
                                    in.putExtra("key",item.getPid());
                                    in.putExtra("cover",item.getCover_image());
                                    in.putExtra("pprofile",item.getPprofile());
                                    startActivity(in);

                                }
                            });
                        }
                        else if(s.equalsIgnoreCase(country)){
                            playerobject.add(new playerstatitem(name,country,pid,pprofile,cover_imae));
                            adapter = new PlayerStatAdapter(getApplicationContext(), playerobject);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    playerstatitem item = playerobject.get(i);
                                    Toast.makeText(getApplicationContext(), "" + item.getName(), Toast.LENGTH_SHORT).show();
                                    Intent in=new Intent(getApplicationContext(),PlayerStatDetail.class);
                                    in.putExtra("key",item.getPid());
                                    in.putExtra("cover", item.getCover_image());
                                    in.putExtra("pprofile",item.getPprofile());
                                    startActivity(in);

                                }
                            });

                        }


                        inputSearch.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                                // When user changed the Text
                                int textlength = cs.length();
                                ArrayList<playerstatitem> tempArrayList = new ArrayList<playerstatitem>();
                                for (playerstatitem c : playerobject) {
                                    if (textlength <= c.getName().length()) {
                                        if (c.getName().toLowerCase().contains(cs.toString().toLowerCase())) {
                                            tempArrayList.add(c);
                                        }
                                    }
                                }
                                adapter = new PlayerStatAdapter(getApplicationContext(), tempArrayList);
                                listView.setAdapter(adapter);


                            }

                            @Override
                            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                          int arg3) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void afterTextChanged(Editable arg0) {
                                // TODO Auto-generated method stub

                            }
                        });

                    }


                }
                catch (Exception ex)
                {


                }


            }

            // Close progressbar
        }
    }
}

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

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.CountryItem;
import utilities.CommonMethods;
import utilities.Constants;
import adapter.SearchCountryAdapter;
import adapter.ServiceHandler;

/**
 * Created by TOQEER on 8/17/2015.
 */
public class SearchByCountry extends Activity {


    EditText inputSearch;
    SearchCountryAdapter adapter;
    ArrayList<CountryItem> countryobject;
        Intent i;
    private ListView listView;
    JSONArray arr = null;
    ServiceHandler sh;
    JSONArray contacts = null;
    String jsonStr=null;
    HttpResponse response;
    String url = Constants.SearchCountry_URL;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchcountry);
        countryobject = new ArrayList<CountryItem>();
        back=(Button)findViewById(R.id.back);
        listView=(ListView)findViewById(R.id.listcountry);
        inputSearch = (EditText)findViewById(R.id.inputSearch);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    i=new Intent(getApplicationContext(),Player_Stats.class);
                        startActivity(i);
                finish();
            }
        });

        if(CommonMethods.isNetworkAvailable(getApplicationContext())) {

            new GetCountry().execute();
        }
        else {
            Toast.makeText(getApplicationContext(), "Internet is not Available", Toast.LENGTH_LONG).show();

        }
    }

    private class GetCountry extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressbar
            CommonMethods.showProgressDialog(SearchByCountry.this);

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


                        final String country = c.getString("Country");


                    countryobject.add(new CountryItem(country));

                    adapter = new SearchCountryAdapter(getApplicationContext(), countryobject);
                    listView.setAdapter(adapter);

                        Log.d("ResponseCountry", "" + jsonStr);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                CountryItem item = countryobject.get(i);
                                Toast.makeText(getApplicationContext(), "" + item.getCountry(), Toast.LENGTH_SHORT).show();
                              //  MActivity.playerSearch(item.getCountry());
                                Intent intent=new Intent(getApplicationContext(),SearchByName.class);
                                intent.putExtra("Name",item.getCountry());
                                startActivity(intent);


                            }
                        });


                        inputSearch.addTextChangedListener(new TextWatcher() {

                            @Override
                            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                                // When user changed the Text
                                int textlength = cs.length();
                                ArrayList<CountryItem> tempArrayList = new ArrayList<CountryItem>();
                                for (CountryItem c : countryobject) {
                                    if (textlength <= c.getCountry().length()) {
                                        if (c.getCountry().toLowerCase().contains(cs.toString().toLowerCase())) {
                                            tempArrayList.add(c);
                                        }
                                    }
                                }
                                adapter = new SearchCountryAdapter(getApplicationContext(), tempArrayList);
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

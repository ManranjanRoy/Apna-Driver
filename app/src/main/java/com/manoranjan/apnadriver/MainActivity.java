package com.manoranjan.apnadriver;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.manoranjan.apnadriver.Activity.AboutUsActivtity;
import com.manoranjan.apnadriver.Activity.ContactUsActivity;
import com.manoranjan.apnadriver.Activity.CurrentBookingActivity;
import com.manoranjan.apnadriver.Activity.MyDetailsActivity;
import com.manoranjan.apnadriver.Activity.HelpActivity;
import com.manoranjan.apnadriver.Activity.Login;
import com.manoranjan.apnadriver.Activity.MyAddressActivity;
import com.manoranjan.apnadriver.Activity.MyProfileActivity;
import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.Response.AddressResponse;
import com.manoranjan.apnadriver.Response.BookingCountResponse;
import com.manoranjan.apnadriver.Response.BookingListResponse;
import com.manoranjan.apnadriver.Response.ProfileResponse;
import com.manoranjan.apnadriver.model.AddressModel;
import com.manoranjan.apnadriver.model.BookingListModel;
import com.manoranjan.apnadriver.model.Profile;
import com.manoranjan.apnadriver.service.CountryService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import ir.apend.slider.model.Slide;
import ir.apend.slider.ui.Slider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    static final float END_SCALE = 0.7f;
    boolean loggedIn;
    String tokencode;
    ProgressDialog progressDialog;
    NavigationView navigationView;
    TextView navname,navemail;
    TextView openornot,pickupdate,deliverdate;
    LinearLayout ld,lp;
    List<Slide> slideList;
    Slider slider;

   @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Configss.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(!loggedIn){
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            //We will start the Profile Activity
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }else{
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
             booking_count();
            loadProfile();
            getbooking();
            //requestMultiplePermissions();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait while Loading ...");
        progressDialog.setCancelable(false);
        StaticData.bsservicetype="0";
        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("our24mart");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");
        Log.d("tokenhome",tokencode);
        openornot=findViewById(R.id.openorclose);
        pickupdate=findViewById(R.id.pickupdate);
        deliverdate=findViewById(R.id.deliverydate);
        ld=findViewById(R.id.ld);
        lp=findViewById(R.id.lp);
        slider = findViewById(R.id.slider);
        //create list of slides
        slideList = new ArrayList<>();
        addbanner();

        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i=new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(i);*/
            }
        });
        findViewById(R.id.schedulebooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticData.schecdulebookingornot<=0) {
                    Intent i = new Intent(getApplicationContext(), MyDetailsActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "There is already an existing booking.Please cancel it to make a new booking.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.managebooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(), CurrentBookingActivity.class);
                startActivity(i);
            }
        });
        findViewById(R.id.pricing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   String url = "https://77laundry.com.au/pricing/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
            }
        });
        findViewById(R.id.helpcard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i=new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(i);*/
            }
        });


       getbooking();
        booking_count();




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setCheckedItem(R.id.nav_home);
        View headerView = navigationView.getHeaderView(0);
        navname = (TextView) headerView.findViewById(R.id.navname);
        navemail=(TextView)headerView.findViewById(R.id.navemail);

        navigationView.setNavigationItemSelectedListener(this);


    }
    public void addbanner(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiLinks.banner,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("banner",response.toString());
                        try {
                            JSONObject outer=new JSONObject(response);
                            JSONArray jsonArray=outer.getJSONArray("list");
                            if (outer.getString("status").equals("true")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject inner = jsonArray.getJSONObject(i);
                                    slideList.add(new Slide(i+1,
                                            ApiLinks.baseimgurl+inner.getString("image"),0));
                                }
                                /*slider.setItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        //do what you want
                                    }
                                });*/
                                slider.addSlides(slideList);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),outer.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString()+"exception   ", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                //params.put("sub_category_id",subid);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.nav_myprofile) {
            startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
        } else if (id == R.id.nav_myaddress) {
            //startActivity(new Intent(getApplicationContext(), MyAddressActivity.class));
        } else if (id == R.id.nav_contactus) {
            startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
        } else if (id == R.id.nav_currentbooking) {
            startActivity(new Intent(getApplicationContext(), CurrentBookingActivity.class));
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(getApplicationContext(), AboutUsActivtity.class));
        } else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void logout(){

        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String roleid = sharedPreferences.getString(Configss.login_role,"Not Available");

        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        if(roleid.equals("0")) {
                            SharedPreferences preferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            //Getting editor
                            SharedPreferences.Editor editor = preferences.edit();

                            //Puting the value false for loggedin
                            editor.putBoolean(Configss.LOGGEDIN_SHARED_PREF, false);

                            //Putting blank value to email
                            editor.putString(Configss.EMAIL_SHARED_PREF, "");
                            editor.putString(Configss.tokencode, "");

                            //Saving the sharedpreferences
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                        //Starting login activity

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(),"For Logout Click on Yes",Toast.LENGTH_SHORT).show();

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void loadProfile() {
      // progressDialog.show();
        new CountryService().getAPI().profile(ApiLinks.view_profile,tokencode).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                //progressDialog.dismiss();
                if (response.body()!=null) {

                    if (response.body().getStatus().equals("true")) {
                        Profile profiles=response.body().getData();
                        StaticData.profile=profiles;
                        //printdata();
                        navemail.setText(profiles.getEmailId());
                        navname.setText(profiles.getFname());

                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
               // progressDialog.dismiss();
                loadProfile();
            }
        });

    }





    void getbooking(){
       /*ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
       progressDialog.setMessage("Please Wait while loading data....");
        progressDialog.show();*/
        new CountryService().getAPI().getbookinglist(ApiLinks.current_booking,tokencode).enqueue(new Callback<BookingListResponse>() {
            @Override
            public void onResponse(Call<BookingListResponse> call, Response<BookingListResponse> response) {
              //  progressDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getStatus().equals("true")){
                        if (response.body().getData()!=null||!response.body().getData().isEmpty()) {
                            BookingListModel bookingListModels = response.body().getData().get(0);
                            openornot.setText("Booked");
                            lp.setVisibility(View.VISIBLE);
                            ld.setVisibility(View.VISIBLE);
                            pickupdate.setText(bookingListModels.getReportingAddress()+" ");
                            deliverdate.setText(bookingListModels.getBookingTiming()+" For  "+bookingListModels.getBookingHour());
                        }else{
                            openornot.setText("close");
                           lp.setVisibility(View.GONE);
                           ld.setVisibility(View.GONE);
                        }
                    }
                    else{
                        findViewById(R.id.aaa).setVisibility(View.GONE);
                        openornot.setText("You currently have no booking");
                        lp.setVisibility(View.GONE);
                        ld.setVisibility(View.GONE);
                      //  Toast.makeText(MainActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<BookingListResponse> call, Throwable t) {
               // progressDialog.dismiss();
                getbooking();
            }
        });
    }

    void booking_count(){
       new CountryService().getAPI().getbooking_count(ApiLinks.booking_count,tokencode).enqueue(new Callback<BookingCountResponse>() {
           @Override
           public void onResponse(Call<BookingCountResponse> call, Response<BookingCountResponse> response) {
               if (response.body()!=null){
                   if (response.body().getStatus().equals("1")){
                       StaticData.schecdulebookingornot=response.body().getCount();
                   }
               }
           }

           @Override
           public void onFailure(Call<BookingCountResponse> call, Throwable t) {

           }
       });
    }
}

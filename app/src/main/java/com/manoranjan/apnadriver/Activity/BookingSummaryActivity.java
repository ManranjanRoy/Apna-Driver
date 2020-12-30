package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.MainActivity;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.Bookingresponse;
import com.manoranjan.apnadriver.service.CountryService;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class BookingSummaryActivity extends AppCompatActivity implements PaymentResultListener {
TextView location,reportingaddress,landmark,mobileno,destination,driver,date,duration,cartype,area;
RelativeLayout rrlocation,rraddress,rrlandmark,rrmobileno,rrdestination,rrdriver,rrbookingdate,rrduration,rrcartype,rrarea;
Button submit;
String tokencode;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_summary);
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");
        Log.d("tokenhome",tokencode);
        progressDialog=new ProgressDialog(BookingSummaryActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        location=findViewById(R.id.textlocation);
        reportingaddress=findViewById(R.id.textraddress);
        landmark=findViewById(R.id.textlandmark);
        mobileno=findViewById(R.id.textmobileno);
        destination=findViewById(R.id.textdestination);
        driver=findViewById(R.id.textdriver);
        date=findViewById(R.id.textbookingdate);
        duration=findViewById(R.id.textduration);
        cartype=findViewById(R.id.textcartype);
        area=findViewById(R.id.textarea);


        location.setText("₹ "+StaticData.bammount);
        reportingaddress.setText(StaticData.breportingtime);
        landmark.setText(StaticData.bnearestlandmark);
        mobileno.setText(StaticData.bnumber);
        destination.setText(StaticData.bdestination);
        driver.setText(StaticData.drivername);
        date.setText(StaticData.bdate);
        duration.setText(StaticData.bduration+"hrs");
        cartype.setText(StaticData.bcatname);
        area.setText(StaticData.bareaname);

        rrlocation=findViewById(R.id.rrlocation);
        rraddress=findViewById(R.id.rrraddress);
        rrlandmark=findViewById(R.id.rrlandmark);
        rrmobileno=findViewById(R.id.rrmobileno);
        rrdestination=findViewById(R.id.rrdestination);
        rrdriver=findViewById(R.id.rrdriver);
        rrbookingdate=findViewById(R.id.rrbookingdate);
        rrduration=findViewById(R.id.rrduration);
        rrcartype=findViewById(R.id.rrcartype);
        rrarea=findViewById(R.id.rrarea);
        
        submit=findViewById(R.id.next);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StaticData.bammount.equals("0")){
                    startPayment();
                }
            }
        });
        
        ////rrlocation,rraddress,rrlandmark,rrmobileno,rrdestination,rrdriver,rrbookingdate,rrduration,rrcartype,rrarea;
        rrlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rraddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rrlandmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rrmobileno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rrdestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rrcartype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rrarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
        rrdriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="2";
                Intent i=new Intent(getApplicationContext(), DriverListActivity.class);
                startActivity(i);
            }
        });
        rrbookingdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.edittype="1";
                Intent i=new Intent(getApplicationContext(), BookingDetailsActivity.class);
                startActivity(i);
            }
        });
      
    }

    private void bookdata() {
        progressDialog.show();
        new CountryService().getAPI().bookservice(ApiLinks.booking,tokencode,
                StaticData.barea,StaticData.bcartype,StaticData.bdriverid,
                StaticData.bname,StaticData.bemail,StaticData.bnumber,
                StaticData.baddress,StaticData.breportingtime,
                StaticData.bnearestlandmark,StaticData.bnumberoptional,StaticData.bduration,
                StaticData.bdestination,StaticData.bdate,StaticData.bprice,StaticData.bcompanyprice).enqueue(new Callback<Bookingresponse>() {
            @Override
            public void onResponse(Call<Bookingresponse> call, Response<Bookingresponse> response) {
                progressDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getStatus().equals("true")){
                        Toast.makeText(BookingSummaryActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        //StaticData.deliveryaddress=address.getText().toString();
                        Intent i=new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(BookingSummaryActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Bookingresponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*servicetype.setText(StaticData.servicetype);
        address.setText(StaticData.housetype+" "+StaticData.streetnumber+" "+StaticData.streetname +" "+ StaticData.suberb+" "+StaticData.pincode);
        pickup.setText(StaticData.pickup);
        dropoff.setText(StaticData.drop_off);*/
    }

    private void startPayment() {
        // payammount=Integer.parseInt(ammount.getText().toString());
        Float payammount=Float.parseFloat(StaticData.bammount);
        //Float payammount=payammount1/100;

        //Toast.makeText(getApplicationContext(),"Razorpay...."+payammount,Toast.LENGTH_LONG).show();
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Apna Driver");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Apna Driver");

            options.put("currency", "INR");
            options.put("email", StaticData.email);
            options.put("contact",StaticData.mobileno);
            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", payammount*100);
            options.put("prefill.email", StaticData.email);
            options.put("prefill.contact",StaticData.mobileno);
            checkout.open(activity, options);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {
        //Toast.makeText(getApplicationContext(),"Sucessfully Paid "+s,Toast.LENGTH_SHORT).show();
      bookdata();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),"Failed Paid"+s,Toast.LENGTH_SHORT).show();
    }
}

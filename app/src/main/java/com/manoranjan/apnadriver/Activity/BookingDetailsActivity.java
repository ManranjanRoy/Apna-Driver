
package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.CarTypeResponse;
import com.manoranjan.apnadriver.Response.HourResponse;
import com.manoranjan.apnadriver.Response.PricingResponse;
import com.manoranjan.apnadriver.Response.SuburbPostcodeResponse;
import com.manoranjan.apnadriver.model.CartypeModel;
import com.manoranjan.apnadriver.model.HourModel;
import com.manoranjan.apnadriver.model.PricingModel;
import com.manoranjan.apnadriver.model.SpinnerModel;
import com.manoranjan.apnadriver.model.SuburbPostCodeModel;
import com.manoranjan.apnadriver.service.CountryService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

public class BookingDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textdate,texttime,amount;
    String date="",times="0";
    EditText elocation,ereportingtime,enearestlandmark,emobileno,emobilenooptional,edestination,eduration;
    String carid="0",carname="",areaid="0",areaname="",duration="0";
    Spinner purposespinnerarea,spinnerduration;
    List<String> arealist,hourlist;
    List<SuburbPostCodeModel> areaListModels=new ArrayList<>();
    List<HourModel> hourModels=new ArrayList<>();
    List<CartypeModel> cartypeModels=new ArrayList<>();
    RadioGroup mRgAllButtons;
    Button next;
    ProgressDialog progressDialog;
    Boolean datestatus=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        progressDialog=new ProgressDialog(BookingDetailsActivity.this);
        progressDialog.setMessage("Please wait while Loading...");

        textdate=findViewById(R.id.txtdate);
        texttime=findViewById(R.id.txttime);
        amount=findViewById(R.id.ammount);
        textdate.setText("Select Date");
        elocation=findViewById(R.id.location);
        ereportingtime=findViewById(R.id.reportingtime);
        enearestlandmark=findViewById(R.id.landmark);
        emobileno=findViewById(R.id.phoneno);
        emobilenooptional=findViewById(R.id.phoneno1);
        edestination=findViewById(R.id.destinationaddress);
        eduration=findViewById(R.id.duration);

        final Calendar cal = Calendar.getInstance();
        purposespinnerarea=findViewById(R.id.purpusespinner3);
        spinnerduration=findViewById(R.id.spinnerduration);

        mRgAllButtons = findViewById(R.id.radiogroup);

        arealist=new ArrayList<>();
        hourlist=new ArrayList<>();

        areaListModels=new ArrayList<>();

        getservice();
        getcartype();
        gethours();

        //String currentdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        textdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(BookingDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view1, int year, int month, int dayOfMonth) {
                        // Toast.makeText(HomeActivity.this, String.format("%d", year) + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth), Toast.LENGTH_SHORT).show();
                        date=String.format("%d", year) + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);
                        textdate.setText(date);
                       // checkdate();
                       // SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE");
                       // Date date1 = new Date(year, month, dayOfMonth-1);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });
        texttime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                final String time = hourOfDay +":"+ minute;
                                try {
                                    String _24HourTime = time;
                                    SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                    SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                                    Date _24HourDt = _24HourSDF.parse(_24HourTime);
                                    Calendar currnetDateTime = Calendar.getInstance();
                                    currnetDateTime.add(Calendar.HOUR,2);
                                    times=_12HourSDF.format(_24HourDt);
                                    texttime.setText(_12HourSDF.format(_24HourDt));
                                    checkdate();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, hour, minute, false);
                timePickerDialog.show();
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();

            }
        });




        //addRadioButtons(3);
        next=findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()) {
                        final String location = elocation.getText().toString().trim();
                        //final String reportingtime = ereportingtime.getText().toString().trim();
                        final String landmark = enearestlandmark.getText().toString().trim();
                        final String mobno = emobileno.getText().toString().trim();
                        final String mobnooptional = emobilenooptional.getText().toString().trim();
                        final String destination = edestination.getText().toString().trim();
                        StaticData.blocation = location;
                        StaticData.breportingtime = times;
                        StaticData.bnearestlandmark = landmark;
                        StaticData.bnumber = mobno;
                        StaticData.bnumberoptional = mobnooptional;
                        StaticData.bdestination = destination;
                        StaticData.bduration = duration;
                        StaticData.bdate = date;
                        StaticData.bcartype = carid;
                        StaticData.barea = areaid;
                        StaticData.bcatname = carname;
                        StaticData.bareaname = areaname;
                        StaticData.bammount=amount.getText().toString();
                    if (StaticData.edittype.equals("1")){
                        StaticData.edittype="0";
                        Intent i = new Intent(getApplicationContext(), BookingSummaryActivity.class);
                        startActivity(i);
                    }else {
                        StaticData.edittype="0";
                        Intent i = new Intent(getApplicationContext(), DriverListActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
        if (StaticData.edittype.equals("1")){
            elocation.setText(StaticData.blocation);
            ereportingtime.setText(StaticData.breportingtime);
            enearestlandmark.setText(StaticData.bnearestlandmark);
            emobileno.setText(StaticData.bnumber);
            emobilenooptional.setText(StaticData.bnumberoptional);
            edestination.setText(StaticData.bdestination);
            eduration.setText(StaticData.bduration);
            textdate.setText(StaticData.bdate);
            if (areaListModels.size()>0) {
                int value=0;
                for (int j = 0; j < areaListModels.size(); j++) {
                   // Log.d("spinnertest",addressModel.getSuburb()+" / "+pickuplistspinner.get(j));
                    if (StaticData.barea.equals(areaListModels.get(j).getLocation_id())) {
                        value=j;
                        break;
                    }
                }
                purposespinnerarea.setSelection(value);
            }

        }
    }

    private void checkdate() {
        SimpleDateFormat sdf1234 = new SimpleDateFormat("yyyy-MM-dd");
        String selecteddate = date;
        String currentdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        try {
            Date testDate1 = sdf1234.parse(selecteddate);
            Date todayDate = sdf1234.parse(currentdate);
            if(testDate1.compareTo(todayDate)==0){
                Calendar currnetDateTime = Calendar.getInstance();
                currnetDateTime.add(Calendar.HOUR,2);
                SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                String  currentTime = df.format(currnetDateTime.getTime());
                Date date1 = df.parse(currentTime);
                Date date2 = df.parse(times);
                if (date1.after(date2)){
                    datestatus=false;
                    Toast.makeText(this, "Select Another date", Toast.LENGTH_SHORT).show();
                }else{
                    datestatus=true;
                    //Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
                }


            }else {
                datestatus=false;
                Toast.makeText(this, "false sametest"+testDate1+todayDate, Toast.LENGTH_SHORT).show();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean validatefiled() {


        final String landmark = enearestlandmark.getText().toString().trim();
        final String mobno = emobileno.getText().toString().trim();
        final String mobnooptional = emobilenooptional.getText().toString().trim();
        final  String destination  =edestination.getText().toString().trim();
        final String duration = eduration.getText().toString().trim();
      if(TextUtils.isEmpty(landmark)) {
            enearestlandmark.setError("Please nearest landmark");
            return false;
        }
        else if(TextUtils.isEmpty(mobno)) {
            emobileno.setError("Enter Your Mobile No");
            return false ;
        }
        else if(mobno.length()>10 ||mobno.length()<10) {
            emobileno.setError("Enter Your Mobile No");
            return false;
        }else
            if(!TextUtils.isEmpty(mobnooptional) && (mobnooptional.length()>10 ||mobnooptional.length()<10)) {
                emobilenooptional.setError("Enter Your Correct Mobile No");
                return false;
        }
       else if(TextUtils.isEmpty(destination)) {
            edestination.setError("Please Destination Address");
            return false;
        }
        else if(times.isEmpty() ||times.equals("0")) {
                Toast.makeText(this, "Select Booking time", Toast.LENGTH_SHORT).show();
            eduration.setError("Please Duration Time");
            return false;
        }
        else if(date.isEmpty()) {
            Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show();
            return false;
        }else if(areaid.isEmpty() ||areaid.equals("0")) {
                Toast.makeText(this, "Select Area", Toast.LENGTH_SHORT).show();
                return false;
         }else if(carid.isEmpty() ||carid.equals("0")) {
                Toast.makeText(this, "Select Car Type", Toast.LENGTH_SHORT).show();
                return false;
            }else if (datestatus==false){
                Toast.makeText(this, "Select Another Date", Toast.LENGTH_SHORT).show();
               return false;
            }
        return true;
    }
    public void addRadioButtons(int number) {
        mRgAllButtons.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0;i < number; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(cartypeModels.get(i).getName());
            rdbtn.setOnClickListener(this);
            mRgAllButtons.addView(rdbtn);
        }
    }
    @Override
    public void onClick(View v) {
        if (cartypeModels.size()>0) {
            for (int x = 0; x < cartypeModels.size(); x++) {
                Log.d("testspinner",cartypeModels.get(x).getName()+((RadioButton)v).getText());
                if (cartypeModels.get(x).getName().toString().equals(((RadioButton)v).getText() )) {
                    carid = cartypeModels.get(x).getId();
                    carname=cartypeModels.get(x).getName();
                 //   Toast.makeText(getApplicationContext(), cartypeModels.get(x).getId(), Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        Log.d("textradio", " Name " + ((RadioButton)v).getText() +" Id is "+v.getId());
    }

    boolean getservice(){
        progressDialog.show();
        new CountryService().getAPI().getsuburbpostcode(ApiLinks.suburb).enqueue(new Callback<SuburbPostcodeResponse>() {
            @Override
            public void onResponse(Call<SuburbPostcodeResponse> call, Response<SuburbPostcodeResponse> response) {
                if (response.body()!=null && response.isSuccessful()){
                    arealist.clear();
                    areaListModels.clear();
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("true")){
                        List<SuburbPostCodeModel> servicelists=response.body().getList();
                        //StaticData.pickup=servicelists.get(0).getPickupslot();
                        if (servicelists.size()>0) {
                            for (int i = 0; i < servicelists.size(); i++) {
                                arealist.add(servicelists.get(i).getName());
                                areaListModels.add(new SuburbPostCodeModel(servicelists.get(i).getLocation_id(),servicelists.get(i).getName()));
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BookingDetailsActivity.this, android.R.layout.simple_spinner_item, arealist);
                            // Drop down layout style - list view with radio button
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            // attaching data adapter to spinner
                            purposespinnerarea.setAdapter(dataAdapter);
                            purposespinnerarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (areaListModels.size()>0) {
                                    String schedule1 = purposespinnerarea.getItemAtPosition(i).toString();
                                   // Toast.makeText(getApplicationContext(),areaListModels.get(i).getLocation_id()+schedule1,Toast.LENGTH_SHORT).show();
                                    // Log.d("testspinner",areaListModels.get(i).getLocation_id()+schedule1);

                                        for (int x = 0; x < areaListModels.size(); x++) {
                                            Log.d("testspinner",areaListModels.get(x).getLocation_id()+schedule1);
                                            if (areaListModels.get(x).getName().toString().equals(schedule1)) {
                                                areaid = areaListModels.get(x).getLocation_id();
                                                areaname=areaListModels.get(x).getName();
                                               // Toast.makeText(getApplicationContext(), areaListModels.get(x).getLocation_id(), Toast.LENGTH_SHORT).show();
                                                break;
                                            }
                                        }
                                    }else{getservice();}

                                }
                                public void onNothingSelected(
                                        AdapterView<?> adapterView) {
                                }
                            });
                        }


                    }
                    else{
                        Toast.makeText(getApplicationContext(), "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<SuburbPostcodeResponse> call, Throwable t) {
                progressDialog.dismiss();
                getservice();
            }
        });
        return true;
    }
    boolean getcartype(){
        progressDialog.show();
        new CountryService().getAPI().getcartype(ApiLinks.car_type).enqueue(new Callback<CarTypeResponse>() {
            @Override
            public void onResponse(Call<CarTypeResponse> call, Response<CarTypeResponse> response) {
                if (response.body()!=null && response.isSuccessful()){
                    cartypeModels.clear();
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("true")){
                        cartypeModels=response.body().getList();
                        //StaticData.pickup=servicelists.get(0).getPickupslot();
                        addRadioButtons(cartypeModels.size());
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<CarTypeResponse> call, Throwable t) {
                progressDialog.dismiss();
                getcartype();
            }
        });
        return true;
    }
    boolean gethours(){
        progressDialog.show();
        new CountryService().getAPI().gethours(ApiLinks.hourchart).enqueue(new Callback<HourResponse>() {
            @Override
            public void onResponse(Call<HourResponse> call, Response<HourResponse> response) {
                if (response.body()!=null && response.isSuccessful()){
                    hourlist.clear();
                    hourModels.clear();
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("true")){
                        List<HourModel> servicelists=response.body().getData();
                        //StaticData.pickup=servicelists.get(0).getPickupslot();
                        if (servicelists.size()>0) {
                            for (int i = 0; i < servicelists.size(); i++) {
                                hourlist.add(servicelists.get(i).getHour()+"hrs");
                                hourModels.add(new HourModel(servicelists.get(i).getHour()));
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(BookingDetailsActivity.this, android.R.layout.simple_spinner_item, hourlist);
                            // Drop down layout style - list view with radio button
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            // attaching data adapter to spinner
                            spinnerduration.setAdapter(dataAdapter);
                            spinnerduration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (hourModels.size()>0) {
                                        String schedule1 = spinnerduration.getItemAtPosition(i).toString();
                                        String a=schedule1.substring(0,schedule1.length()-3);
                                           for (int x = 0; x < hourModels.size(); x++) {
                                            Log.d("testspinner",hourModels.get(x).getHour()+a);
                                            if (hourModels.get(x).getHour().toString().equals(a)) {
                                                duration = hourModels.get(x).getHour();
                                               // Toast.makeText(getApplicationContext(), hourModels.get(x).getHour(), Toast.LENGTH_SHORT).show();
                                                getprice();
                                                break;
                                            }
                                        }
                                    }else{getservice();}

                                }
                                public void onNothingSelected(
                                        AdapterView<?> adapterView) {
                                }
                            });
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<HourResponse> call, Throwable t) {
                progressDialog.dismiss();
                gethours();
            }
        });
        return true;
    }
    void getprice() {
        if (duration.equals("0") || carid.isEmpty() || carid.equals("0")) {

        } else{
            progressDialog.show();
        new CountryService().getAPI().getprice(ApiLinks.get_pricelist, carid, duration).enqueue(new Callback<PricingResponse>() {
            @Override
            public void onResponse(Call<PricingResponse> call, Response<PricingResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    arealist.clear();
                    areaListModels.clear();
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("true")) {
                        List<PricingModel> servicelists = response.body().getData();
                        if (servicelists.size()>0) {

                            //StaticData.pickup=servicelists.get(0).getPickupslot();
                            PricingModel p = servicelists.get(0);
                            Double a = Double.valueOf(p.getPrice());
                            Double b = Double.valueOf(p.getCompanyPrice());
                            Double c = a + b;
                            StaticData.bprice=p.getPrice();
                            StaticData.bcompanyprice=p.getCompanyPrice();

                            amount.setText(String.valueOf(c));
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PricingResponse> call, Throwable t) {
                progressDialog.dismiss();
                getservice();
            }
        });
    }
    }
}

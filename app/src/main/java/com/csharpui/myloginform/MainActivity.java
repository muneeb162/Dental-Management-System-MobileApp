package com.csharpui.myloginform;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
//import android.View;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Spinner city;
    EditText fname,lname,phoneno;
    RadioButton maleradbtn,femaleradbtn;
    TextView t1,t2;
    Button button;
    private static final int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Fragment Code
//        Fragment fragment = new reg_frag1();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment_container,fragment,"FirstFragment");
//        transaction.commit();
        fname = findViewById(R.id.fnametext);
        lname = findViewById(R.id.lnametext);
        phoneno = findViewById(R.id.pNotext);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        maleradbtn = findViewById(R.id.maleradiobtn);
        femaleradbtn =  findViewById(R.id.femaleradiobtn);
        email = findViewById(R.id.emailtext);
        pass = findViewById(R.id.passtext);
        city = findViewById(R.id.citytext);
        String[] options = {"Select an option", "Option 1", "Option 2", "Option 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);
        city.setSelection(0);
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isallfieldschecked = validate();
                if(isallfieldschecked){
                    Intent intent = new Intent(MainActivity.this, LoginActivity1.class);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean validate(){
        boolean res = true;
        int spinnerselecteditempos = city.getSelectedItemPosition();
        if(fname.length() == 0 && lname.length() == 0 && phoneno.length() == 0 && email.length() == 0 && pass.length() == 0
                && (!(spinnerselecteditempos > 0))
                && !(maleradbtn.isChecked() || femaleradbtn.isChecked())){
            fname.setError("This field is required");
            lname.setError("This field is required");
            phoneno.setError("This field is required");
            t2.setVisibility(VISIBLE);
            t2.setText("Gender is not selected");
            t1.setVisibility(VISIBLE);
            t1.setText("This field is required");
            email.setError("This field is required");
            pass.setError("This field is required");
            res = false;
        }
        if (fname.length() == 0) {
            fname.setError("This field is required");
            res = false;
        }
        else{
            fname.setError(null);
        }
        if (lname.length() == 0) {
            lname.setError("This field is required");
            res = false;
        }
        else{
            lname.setError(null);
        }
        if (phoneno.length() == 0) {
            phoneno.setError("This field is required");
            res = false;
        }
        else {
            if (phoneno.length() < 11) {
                phoneno.setError("Phone Number must be minimum 11 digits");
                res = false;
            } else {
                phoneno.setError(null);
            }
        }
        if (!(maleradbtn.isChecked() || femaleradbtn.isChecked())) {
            t2.setVisibility(VISIBLE);
            t2.setText("Gender is not selected");
            res = false;
        }
        else{
            t2.setVisibility(INVISIBLE);
        }

        if(!(spinnerselecteditempos > 0)){
            t1.setVisibility(VISIBLE);
            t1.setText("This field is required");
            res = false;
        }
        else{
            t1.setVisibility(INVISIBLE);
        }
        if(email.length() == 0 && pass.length() == 0 && spinnerselecteditempos == 0){
            email.setError("This field is required");
            pass.setError("This field is required");
            t1.setVisibility(VISIBLE);
            t1.setText("This field is required");
            res = false;
        }
        if (email.length() == 0) {
            email.setError("This field is required");
            res = false;
        }
        else{
            if (!(Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())) {
                email.setError("Invalid Email Address");
                res = false;
            }else {
                email.setError(null);
            }
        }
        if (pass.length() == 0) {
            pass.setError("This field is required");
            res = false;
        }
        else {
            pass.setError(null);
        }
        // after all validation return true.
        return res;
    }

//    private void openFileExplorer() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");  // Set the MIME type to select only image files
//        startActivityForResult(intent, PICK_IMAGE_REQUEST);
//    }
//    public void setImage(Uri bitmap) {
//        // Set the bitmap to your ImageView or use it as needed
//        imageView.setImageURI(bitmap);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
//            Uri selectedImageUri = data.getData();
//
//            imageView.setBackground(null);
//            imageView.setImageURI(selectedImageUri);
//            t1.setVisibility(INVISIBLE);
//            // Assuming you have the Image URI stored in a variable named "imageUri"
//            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("imageUri", selectedImageUri.toString());
//            editor.apply();
//            k=true;
//        }
//    }
}



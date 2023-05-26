package com.csharpui.myloginform;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reg_frag1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reg_frag1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText fname,lname,phoneno;
    RadioButton maleradbtn,femaleradbtn;
    ImageView imageView;
    TextView t1,t2;
    public boolean k = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public reg_frag1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reg_frag1.
     */
    // TODO: Rename and change types and number of parameters
    public static reg_frag1 newInstance(String param1, String param2) {
        reg_frag1 fragment = new reg_frag1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reg_frag1, container, false);
        fname = (EditText) view.findViewById(R.id.fnametext);
        lname = (EditText) view.findViewById(R.id.lnametext);
        phoneno = (EditText) view.findViewById(R.id.pNotext);
        t1 = (TextView) view.findViewById(R.id.imageerror);
        t2 = (TextView) view.findViewById(R.id.t2);
        maleradbtn = (RadioButton) view.findViewById(R.id.maleradiobtn);
        femaleradbtn = (RadioButton) view.findViewById(R.id.femaleradiobtn);
        imageView = (ImageView) view.findViewById(R.id.roundImageView);
        //filter();
        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean isallfieldschecked = validate(view);
                    if (isallfieldschecked) {
                        fname.setError(null);
                        lname.setError(null);
                        phoneno.setError(null);
                        Fragment fragment = new reg_fragment2();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(
                                        R.anim.fade_in,   // popEnter
                                        R.anim.fade_out,  // exit
                                        R.anim.slide_in,  // enter
                                        R.anim.slide_out  // popExit
                                );
                       // transaction.replace(R.id.fragment_container, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                }
        });
        TextView textView = (TextView) view.findViewById(R.id.uploadimagelink);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileExplorer();
            }
        });
        if(k==true){
            SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            String encodedImage = sharedPreferences.getString("imageUri", null);
            if (encodedImage != null) {
                // Set the retrieved bitmap to your ImageView in the first fragment or use it as needed
                setImage(Uri.parse(encodedImage));
            }
        }
        return view;
    }
    private boolean validate(View view){
        boolean res = true;
        if(fname.length() == 0 && lname.length() == 0 && phoneno.length() == 0 && imageView.getDrawable() == null && !(maleradbtn.isChecked() || femaleradbtn.isChecked())){
            fname.setError("This field is required");
            lname.setError("This field is required");
            phoneno.setError("This field is required");
            t1.setVisibility(View.VISIBLE);
            t1.setText("Image is not selected");
            t2.setVisibility(View.VISIBLE);
            t2.setText("Gender is not selected");
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
            t2.setVisibility(View.VISIBLE);
            t2.setText("Gender is not selected");
            res = false;
        }
        else{
            t2.setVisibility(View.INVISIBLE);
        }
        if(imageView.getDrawable() == null){
            t1.setVisibility(View.VISIBLE);
            t1.setText("Image is not selected");
            res = false;
        }
        // after all validation return true.
        return res;
        }

    private void openFileExplorer() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");  // Set the MIME type to select only image files
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    public void setImage(Uri bitmap) {
        // Set the bitmap to your ImageView or use it as needed
        imageView.setImageURI(bitmap);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            imageView.setBackground(null);
            imageView.setImageURI(selectedImageUri);
            t1.setVisibility(View.INVISIBLE);
            // Assuming you have the Image URI stored in a variable named "imageUri"
            SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("imageUri", selectedImageUri.toString());
            editor.apply();
            k=true;
        }
    }
}
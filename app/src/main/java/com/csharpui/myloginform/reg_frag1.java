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
        maleradbtn = (RadioButton) view.findViewById(R.id.maleradiobtn);
        femaleradbtn = (RadioButton) view.findViewById(R.id.femaleradiobtn);
        imageView = (ImageView) view.findViewById(R.id.roundImageView);
        //filter();
        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(maleradbtn.isChecked() || femaleradbtn.isChecked())) {
//                    new AlertDialog.Builder(v.getContext())
//                            .setTitle("Field Required")
//                            .setMessage("Please Select Gender")
//                            .setIcon(R.drawable.horse)
//                            .setPositiveButton("OK",null).show();
                    Toast.makeText(view.getContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();

                    lname.setError(null);
                    fname.setError(null);
                    phoneno.setError(null);
                }
                else  if(imageView.getDrawable() == null){
                    Toast.makeText(view.getContext(),"Please Select Image",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isallfieldschecked = validate();
                    if (isallfieldschecked) {
                        fname.setError(null);
                        lname.setError(null);
                        phoneno.setError(null);
                        Fragment fragment = new reg_fragment2();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(
                                        R.anim.slide_in,  // enter
                                        R.anim.fade_out,  // exit
                                        R.anim.fade_in,   // popEnter
                                        R.anim.slide_out  // popExit
                                );
                        transaction.replace(R.id.fragment_container, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
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
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String encodedImage = sharedPreferences.getString("imageUri", null);
        if (encodedImage != null) {
            // Set the retrieved bitmap to your ImageView in the first fragment or use it as needed
            setImage(Uri.parse(encodedImage));
       }
        setback();

        return view;
    }
    private boolean validate(){
        if (fname.length() == 0) {
            fname.setError("This field is required");
            lname.setError(null);
            phoneno.setError(null);
            return false;
        }

        if (lname.length() == 0) {
            lname.setError("This field is required");
            fname.setError(null);
            phoneno.setError(null);
            return false;
        }

        if (phoneno.length() == 0) {
            phoneno.setError("Phone Number is required");
            lname.setError(null);
            fname.setError(null);
            return false;
        } else if (phoneno.length() < 11) {
            phoneno.setError("Phone Number must be minimum 11 digits");
            lname.setError(null);
            fname.setError(null);
            return false;
        }
        // after all validation return true.
        return true;
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
    public void setback() {
        // Set the bitmap to your ImageView or use it as needed
        if(imageView.getDrawable()!=null){
            imageView.setBackground(null);
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            imageView.setBackground(null);
            imageView.setImageURI(selectedImageUri);
            // Assuming you have the Image URI stored in a variable named "imageUri"
            SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("imageUri", selectedImageUri.toString());
            editor.apply();
        }
    }
}
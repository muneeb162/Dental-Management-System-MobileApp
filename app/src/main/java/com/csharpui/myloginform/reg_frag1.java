package com.csharpui.myloginform;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

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

    ImageView imageView;
    EditText fname,lname,phoneno;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button b1;
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
        imageView = (ImageView) view.findViewById(R.id.roundImageView);
        Button button = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (fname.getText().toString().isEmpty() ){
                    fname.setError("First Name is required");
                    lname.setError(null);
                    phoneno.setError(null);
                }
                else if (lname.getText().toString().isEmpty()){
                    lname.setError("Last Name is required");
                    fname.setError(null);
                    phoneno.setError(null);
                }
                else if(phoneno.getText().toString().isEmpty()){
                        phoneno.setError("Enter Your Valid Phone Number");
                        lname.setError(null);
                        fname.setError(null);

                    }
                    else{
                        if (!(fname.getText().toString().matches("^[a-zA-Z0-9]+$"))){
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
                        transaction.replace(R.id.fragment_container,fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    // do something

                }
//                else  {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext() );
//                    builder.setTitle("AlertDialog Title")
//                            .setMessage("Fill All Required Fields!")
//                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // Perform action when OK button is clicked
//                                }
//                            })
//                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // Perform action when Cancel button is clicked
//                                    dialog.cancel();
//                                }
//                            });
//
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();

                }

        });
        TextView textView = (TextView) view.findViewById(R.id.uploadimagelink);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileExplorer();
            }
        });
        return view;
    }

    private void openFileExplorer() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");  // Set the MIME type to select only image files
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            // Do something with the selected image URI
            // For example, display the image in an ImageView
            //imageView.setImageURI(selectedImageUri);
           // Bitmap bitmap = null;
            //try {
              //  bitmap = BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(selectedImageUri.toString())));
           // } catch (FileNotFoundException e) {
                //e.printStackTrace();
            //}
            //Bitmap ovalBitmap = getOvalBitmap(bitmap);
            imageView.setBackground(null);
            //imageView.setImageBitmap(selectedImageUri);
            imageView.setImageURI(selectedImageUri);

        }
    }
    private Bitmap getOvalBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float radius = bitmap.getWidth() / 2f;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

}
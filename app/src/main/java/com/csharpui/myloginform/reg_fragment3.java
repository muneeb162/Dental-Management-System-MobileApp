package com.csharpui.myloginform;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reg_fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reg_fragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //EditText city,state,area,completeadd;
    public reg_fragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reg_fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static reg_fragment3 newInstance(String param1, String param2) {
        reg_fragment3 fragment = new reg_fragment3();
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
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_reg_fragment3, container, false);
//        state = (EditText) view.findViewById(R.id.statetext);
//        area = (EditText) view.findViewById(R.id.areatext);
//        completeadd = (EditText) view.findViewById(R.id.completeaddresstext);
//        city = (EditText) view.findViewById(R.id.citytext);
//
//        Button button = (Button) view.findViewById(R.id.finalbutton);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                boolean isallfieldschecked = validate();
//                if (isallfieldschecked) {
//                    state.setError(null);
//                    area.setError(null);
//                    city.setError(null);
//                    completeadd.setError(null);
//                    Fragment fragment = new reg_fragment2();
//                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
//                            .setCustomAnimations(
//                                    R.anim.fade_in,   // popEnter
//                                    R.anim.fade_out,  // exit
//                                    R.anim.slide_in,  // enter
//                                    R.anim.slide_out  // popExit
//                            );
//                    transaction.replace(R.id.fragment_container, fragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//                }
//            }
//        });
        //return view;
            return inflater.inflate(R.layout.fragment_reg_fragment3, container, false);
    }

//    private boolean validate(){
//        if (city.length() == 0) {
//            city.setError("This field is reuired");
//            state.setError(null);
//            area.setError(null);
//            completeadd.setError(null);
//            return false;
//        }
//        if (state.length() == 0) {
//            state.setError("This field is required");
//            area.setError(null);
//            completeadd.setError(null);
//            city.setError(null);
//            return false;
//        }
//
//        if (area.length() == 0) {
//            area.setError("This field is required");
//            state.setError(null);
//            completeadd.setError(null);
//            city.setError(null);
//            return false;
//        }
//
//        if (completeadd.length() == 0) {
//            completeadd.setError("This field is reuired");
//            state.setError(null);
//            area.setError(null);
//            city.setError(null);
//            return false;
//        }
//        // after all validation return true.
//        return true;
//    }
}
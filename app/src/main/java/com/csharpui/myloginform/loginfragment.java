package com.csharpui.myloginform;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText lemail,lpass;
    public loginfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static loginfragment newInstance(String param1, String param2) {
        loginfragment fragment = new loginfragment();
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
        View view = inflater.inflate(R.layout.fragment_loginfragment, container, false);
        lemail = (EditText) view.findViewById(R.id.loginemailtext);
        lpass = (EditText) view.findViewById(R.id.loginpasstext);
        

        Button button = (Button) view.findViewById(R.id.loginbutton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isallfieldschecked = validate();
                if (isallfieldschecked) {
                    lemail.setError(null);
                    lpass.setError(null);
                    Fragment fragment = new reg_fragment2();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(
                                    R.anim.fade_in,   // popEnter
                                    R.anim.fade_out,  // exit
                                    R.anim.slide_in,  // enter
                                    R.anim.slide_out  // popExit
                            );
                    //transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        return view;
    }
    private boolean validate(){
        if (lemail.length() == 0) {
            lemail.setError("This field is required");
            lpass.setError(null);
            return false;
        }

        if (lpass.length() == 0) {
            lpass.setError("This field is required");
            lemail.setError(null);
            return false;
        }
        // after all validation return true.
        return true;
    }
}
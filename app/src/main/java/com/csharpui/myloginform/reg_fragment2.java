package com.csharpui.myloginform;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reg_fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reg_fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText email,pass;
    Spinner city;
    TextView t1;
    public reg_fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reg_fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static reg_fragment2 newInstance(String param1, String param2) {
        reg_fragment2 fragment = new reg_fragment2();
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
        View view = inflater.inflate(R.layout.fragment_reg_fragment2, container, false);
        Button button = (Button) view.findViewById(R.id.midbutton);
        email = (EditText) view.findViewById(R.id.emailtext);
        pass = (EditText) view.findViewById(R.id.passtext);
        city = (Spinner) view.findViewById(R.id.citytext);
        t1 = (TextView) view.findViewById(R.id.t1);
        String[] options = {"Select an option", "Option 1", "Option 2", "Option 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter);
        city.setSelection(0);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isallfieldschecked = validate();
                if (isallfieldschecked) {
                    email.setError(null);
                    pass.setError(null);
                    Fragment fragment = new loginfragment();
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
        boolean res = true;
        int spinnerselecteditempos = city.getSelectedItemPosition();
        if(!(spinnerselecteditempos > 0)){
            t1.setVisibility(View.VISIBLE);
            t1.setText("This field is required");
            res = false;
        }
        else{
            t1.setVisibility(View.INVISIBLE);
        }
        if(email.length() == 0 && pass.length() == 0 && spinnerselecteditempos == 0){
            email.setError("This field is required");
            pass.setError("This field is required");
            t1.setVisibility(View.VISIBLE);
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
}
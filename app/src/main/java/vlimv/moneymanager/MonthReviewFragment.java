package vlimv.moneymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 22-Oct-18.
 */

public class MonthReviewFragment extends Fragment implements DayListAdapter.ItemClickListener {
    public class Dialog_add_expense extends android.app.Dialog {
        public Dialog_add_expense(Activity a) {
            super(a);
        }
        public Dialog_add_expense(@NonNull Context context, int themeResId) {
            super(context, themeResId);
        }

        public void showDialog(Activity activity, int themeResId){
            final Dialog_add_expense dialog = new Dialog_add_expense(activity, themeResId);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.dialog_add_expense);

            dialog.show();
        }
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    DayListAdapter daysAdapter;

    public MonthReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CabinetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthReviewFragment newInstance(String param1, String param2) {
        MonthReviewFragment fragment = new MonthReviewFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_month_review, container, false);
//
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.cabinet);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(
//                Color.parseColor("#ffffff")));

        //Set up data for recycler view
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        //Set up the RecyclerView
        android.support.v7.widget.RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        daysAdapter = new DayListAdapter(getContext(), animalNames);
        daysAdapter.setClickListener(this);
        recyclerView.setAdapter(daysAdapter);

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(MainPageActivity.this);
//
//                View sheetView = MainPageActivity.this.getLayoutInflater().inflate(R.layout.dialog_add_expense, null);
//
//                mBottomSheetDialog.setContentView(sheetView);
//                mBottomSheetDialog.show();
//                Dialog_add_expense d = new Dialog_add_expense(getContext(), R.style.FullWidthDialog);
//                d.showDialog(getActivity(), R.style.FullWidthDialog);
                Intent intentNewExpense = new Intent(view.getContext(), AddExpenseActivity.class);
                startActivityForResult(intentNewExpense, 1);
            }
        });

        return rootView;
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + daysAdapter.getItem(position) +
                " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        if (view != null) {
            Log.d("onResume MainPage", "invalidate view");
            view.invalidate();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
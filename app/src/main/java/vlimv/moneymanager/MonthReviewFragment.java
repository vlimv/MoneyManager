package vlimv.moneymanager;

import vlimv.moneymanager.Adapters.DayListAdapter;
import vlimv.moneymanager.Database.CategoryEntity;
import vlimv.moneymanager.Database.ExpenseEntity;
import vlimv.moneymanager.Database.ExpenseViewModel;
import vlimv.moneymanager.Models.Day;
import vlimv.moneymanager.Models.Expense;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by HP on 22-Oct-18.
 */

public class MonthReviewFragment extends Fragment implements DayListAdapter.ItemClickListener {

    public static final int ADD_EXP_REQUEST_CODE = 1;
//    public class Dialog_add_expense extends android.app.Dialog {
//        public Dialog_add_expense(Activity a) {
//            super(a);
//        }
//        public Dialog_add_expense(@NonNull Context context, int themeResId) {
//            super(context, themeResId);
//        }
//
//        public void showDialog(Activity activity, int themeResId){
//            final Dialog_add_expense dialog = new Dialog_add_expense(activity, themeResId);
//
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//            dialog.setContentView(R.layout.dialog_add_expense);
//
//            dialog.show();
//        }
//    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    DayListAdapter daysAdapter;
    List<ExpenseEntity> expenseEntities;

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
//        ArrayList<String> animalNames = new ArrayList<>();
//        animalNames.add("Horse");
//        animalNames.add("Cow");
//        animalNames.add("Camel");
//        animalNames.add("Sheep");
//        animalNames.add("Goat");


//        Set up the RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        daysAdapter = new DayListAdapter(getContext());


        ExpenseViewModel expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel.getAllExpenses().observe(this, new Observer<List<ExpenseEntity>>() {
            @Override
            public void onChanged(@Nullable final List<ExpenseEntity> expenses) {
                // Update the cached copy of the words in the adapter.
//                categoryEntities = categories;
                expenseEntities = expenses;
                daysAdapter.filterExpenses(expenses);
            }
        });

        daysAdapter.setClickListener(this);
        recyclerView.setAdapter(daysAdapter);


        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewExpense = new Intent(view.getContext(), AddExpenseActivity.class);
                startActivityForResult(intentNewExpense, ADD_EXP_REQUEST_CODE);
            }
        });

        return rootView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_EXP_REQUEST_CODE && resultCode == RESULT_OK) {
            ExpenseEntity e = data.getParcelableExtra("expense");
            expenseEntities.add(e);
            daysAdapter.filterExpenses(expenseEntities);
        } else {
            Toast.makeText(
                    getContext(),
                    R.string.error_while_adding,
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
//        Toast.makeText(getContext(), "You clicked " + daysAdapter.getItem(position) +
//                " on row number " + position, Toast.LENGTH_SHORT).show();
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
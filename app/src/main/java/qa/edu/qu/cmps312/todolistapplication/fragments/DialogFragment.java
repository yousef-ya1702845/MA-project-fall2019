package qa.edu.qu.cmps312.todolistapplication.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import qa.edu.qu.cmps312.todolistapplication.R;
import qa.edu.qu.cmps312.todolistapplication.model.Tutorial;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends android.support.v4.app.DialogFragment implements View.OnClickListener {

    Tutorial tutorial;

    TextView dialogTitleTv;
    Button cancelBtn,resetBtn,submitBtn;
    EditText subjectEdt,nameEdt,phoneEdt,infoEdt;


      View view;
    DialogFragmentInteraction interaction;

    public static final String TODO_KEY = "TODO";

    private static boolean isEdit = false;

    public interface DialogFragmentInteraction {
        void addTutorial(Tutorial todo);
        void updateTodo(Tutorial todo);
        void dismissFragment();
    }


    public DialogFragment() {

    }

    //This method is used when we want to add new To-do
    public static DialogFragment newInstance() {

        isEdit = false;    //this is used by the initializeViews method
        Bundle args = new Bundle();

        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //This method is used when we want to Edit and exiting To-do
    public static DialogFragment newInstance(Tutorial todo) {

        isEdit = true;          //this is used by the initializeViews method
        Bundle args = new Bundle();
        args.putParcelable(TODO_KEY, todo);

        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof DialogFragmentInteraction)) throw new AssertionError();
        interaction = (DialogFragmentInteraction) context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_dialog, container, false);
    }


    //This method is called after the layout is rendered and you will be able to access the layout by using the rootView object
    @Override
    public void onViewCreated(@NonNull final View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        if (getArguments() != null && isEdit)
            this.tutorial = getArguments().getParcelable(TODO_KEY);
        else
            this.tutorial = new Tutorial();

         /*
         This method gets all the views....just to not overcrowd this method, I extracted all the
         initialization of the views like findViewById to this method...this way our code will stay
         clean
         */

        initializeViews(rootView);

    }

    /*
        This method gets all the ids of the views
        If it is edit it populates data into each view otherwise
        It shows empty dialog with empty views that the user can add too
     */
    public void initializeViews(final View view) {
        this.view = view;
        subjectEdt = view.findViewById(R.id.subjectEdt);
        nameEdt = view.findViewById(R.id.nameEdt);
        phoneEdt = view.findViewById(R.id.phoneEdt);
        infoEdt = view.findViewById(R.id.infoEdt);

        cancelBtn = view.findViewById(R.id.cancel_btn);
        resetBtn = view.findViewById(R.id.reset_btn);

        dialogTitleTv = view.findViewById(R.id.titleTv);
        submitBtn = view.findViewById(R.id.submit_btn);

        cancelBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        //If it an edit then we need to populate the data
        if (isEdit) {

        }


    }

    /*
     ** This is a listener method to all the buttons in the dialog
     */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cancel_btn:
                this.dismiss();
                interaction.dismissFragment();
                break;
            case R.id.reset_btn:
                subjectEdt.setText("");
                nameEdt.setText("");
                phoneEdt.setText("");
                infoEdt.setText("");
                break;

            case R.id.submit_btn:
                tutorial.setSubject(subjectEdt.getText().toString());
                tutorial.setName(nameEdt.getText().toString());
                tutorial.setPhone(phoneEdt.getText().toString());
                tutorial.setInfo(infoEdt.getText().toString());

                interaction.addTutorial(tutorial);
                this.dismiss();
                break;
        }
    }


    public boolean validInput() {
        if (tutorial.getSubject() != null
                && tutorial.getInfo() != null

                && tutorial.getPhone() != null
                && tutorial.getName() != null)
            return true;
        return false;
    }
}

package com.example.android.resumeapplication;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.resumeapplication.dummy.DummyContent;
import com.example.android.resumeapplication.dummy.DummyContent.DummyItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    AdapterSectionRecycler adapterSectionRecycler;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     *
     */
    public String JSONResourceReader() {
        String jsonString;

        InputStream is = getResources().openRawResource(R.raw.education);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        jsonString = writer.toString();
        return jsonString;
    }


    public String[] getJsonData()  throws JSONException {

        String jsonString=JSONResourceReader();
        JSONObject obj = new JSONObject(jsonString);
        String[] data=new String[2];
        data[0]=obj.getString("education");
        data[1]=obj.getString("CourseWork");
        Log.d("testing",data[1]);

        return data;


    }




    public List<sectionheader> getProjectsList(){
        List<sectionheader> parentObjectList =new ArrayList<sectionheader>();
        try {
            JSONObject issueObj = new JSONObject(JSONResourceReader());
            Iterator iterator = issueObj.keys();
            while(iterator.hasNext()){
                String key = (String)iterator.next();
                JSONArray issue = issueObj.getJSONArray(key);

                //  get id from  issue
                List<child> childObjectList = new ArrayList<child>();
                for(int i=0;i<issue.length();i++){
                    childObjectList.add(new child(issue.getString(i)));
                }
                //String _pubKey = issue.optString("id");
                parentObjectList.add(new sectionheader(childObjectList,key,10));
            }

        }catch (Exception e){

        }
        return parentObjectList;

    }







    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
            List< child> childList = new ArrayList<>();
            String data[]=new String[2];
            try {
                data = getJsonData();
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }

            List<sectionheader> sections = new ArrayList<>();
            String education=data[0];
            childList.add(new child(education));
            sections.add(new sectionheader(childList,"Education",0));
            childList = new ArrayList<>();
            String coursework=data[1];
            childList.add(new child(coursework));
            sections.add(new sectionheader(childList,"Coursework",0));



//            sections.add(new sectionheader(childList,"A",6));
//            childList = new ArrayList<>();
//            childList.add(new child("Intruder Shanky"));
//            childList.add(new child("Invincible Vinod"));
//            sections.add(new sectionheader(childList, "I", 2));
//            childList = new ArrayList<>();
//            childList.add(new child("Bill Gates"));
//            childList.add(new child("Bob Proctor"));
//
//            sections.add(new sectionheader(childList, "B", 2));
            adapterSectionRecycler=new AdapterSectionRecycler(context,sections);
            recyclerView.setAdapter(adapterSectionRecycler);



        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}

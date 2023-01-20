package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.UnivSyllabusModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by wccs1980 on 30-04-2018.
 */

public class UnivSyllabusExpandAdpter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
  /*  private HashMap<String, List<String>> _listDataWeightage;
    private HashMap<String, List<String>> _listDataNoLec;*/

    List<String> listDataHeaderExp;



    public UnivSyllabusExpandAdpter(Context _context, List<String> _listDataHeader, HashMap<String, List<String>> _listDataChild) {
        this._context = _context;
        this._listDataHeader = _listDataHeader;
        this._listDataChild = _listDataChild;


      /*  this._listDataWeightage=_listDataWeightage;
        this._listDataNoLec=_listDataNoLec;*/
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        String[] header=headerTitle.split(Pattern.quote("$"));
        String unitname=header[0];
        String weightage=header[1];
        String noLec=header[2];
        Typeface customTypeOne = Typeface.createFromAsset(convertView.getContext().getAssets(), "font/Poppins-Medium.otf");

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(customTypeOne);
        lblListHeader.setText(unitname);

        TextView lblListCount=(TextView)convertView.findViewById(R.id.lblListCount);
        lblListCount.setTypeface(customTypeOne);
        lblListCount.setText("UNIT "+String.valueOf(groupPosition+1)+" : ");

        TextView lblListWeightage=(TextView)convertView.findViewById(R.id.lblListWeightage);
        lblListWeightage.setTypeface(customTypeOne);
        lblListWeightage.setText(weightage);

        TextView lblListPeriod=(TextView)convertView.findViewById(R.id.lblListPeriod);
        lblListPeriod.setTypeface(customTypeOne);
        lblListPeriod.setText(noLec);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        String[] value=childText.split(Pattern.quote("$"));
        String topic_name=value[0];
        String topic_descrptn=value[1];

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(topic_name);

        TextView lblListDescription=(TextView)convertView.findViewById(R.id.lblListDescription);

        lblListDescription.setText(topic_descrptn);

       // ExpandableListView lvExpUnivSyllTopic=(ExpandableListView)convertView.findViewById(R.id.lvExpUnivSyllTopic);

      /*  TextView lblListWeightage=(TextView)convertView.findViewById(R.id.lblListWeightage);
        lblListWeightage.setText(listDataWeight.get());*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}

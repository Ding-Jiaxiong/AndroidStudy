package com.study.studyexpandablelistview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ExpandableListView";
    private List<String> groupList;

    private Map<String, List<String>> childMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initExpandableListView();
    }

    private void initData() {
        groupList = new ArrayList<>();
        groupList.add("水果");
        groupList.add("蔬菜");
        groupList.add("肉类");

        childMap = new HashMap<>();
        List<String> fruitList = new ArrayList<>();
        fruitList.add("苹果");
        fruitList.add("香蕉");
        fruitList.add("橙子");

        List<String> vegList = new ArrayList<>();
        vegList.add("黄瓜");
        vegList.add("西红柿");
        vegList.add("菠菜");

        List<String> meatList = new ArrayList<>();
        meatList.add("牛肉");
        meatList.add("羊肉");
        meatList.add("猪肉");

        childMap.put(groupList.get(0), fruitList);
        childMap.put(groupList.get(1), vegList);
        childMap.put(groupList.get(2), meatList);
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int i) {
            String groupName = groupList.get(i);
            return childMap.get(groupName).size();
        }

        @Override
        public Object getGroup(int i) {
            return groupList.get(i);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String groupName = groupList.get(groupPosition);
            return childMap.get(groupName).get(childPosition);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_group, parent, false);
            }
            TextView tvGroup = convertView.findViewById(R.id.tv_group);
            tvGroup.setText(groupList.get(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_child, parent, false);
            }
            // 绑定数据
            TextView tvChild = convertView.findViewById(R.id.tv_child);
            String groupName = groupList.get(groupPosition);
            tvChild.setText(childMap.get(groupName).get(childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    private void initExpandableListView() {

        ExpandableListView expandableListView = findViewById(R.id.ExpandableListView);

        ExpandableListAdapter adapter = new MyExpandableListAdapter();
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {

            String groupName = groupList.get(groupPosition);
            Log.d(TAG, "initExpandableListView: 点击了组" + groupName);
            return false;
        });

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String groupName = groupList.get(groupPosition);
            String childName = childMap.get(groupName).get(childPosition);

            Log.d(TAG, "initExpandableListView: 点击了[" + groupName + "]下的" + childName);
            return true;
        });

    }

}
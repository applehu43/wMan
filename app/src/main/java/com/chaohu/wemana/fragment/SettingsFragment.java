package com.chaohu.wemana.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaohu.wemana.R;
import com.chaohu.wemana.adapter.SettingListAdapter;
import com.chaohu.wemana.model.SettingInfo;
import com.chaohu.wemana.utils.BMIDemo;
import com.chaohu.wemana.utils.FileHelper;

import java.util.ArrayList;

/**
 * Created by chaohu on 2016/3/30.
 */
public class SettingsFragment extends Fragment {

    private final String height_not_found = "000";
    private final String height_uit = "CM";
    private final String weight_not_found = "000.0";
    private final String weight_unit = "KG";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_wemana_settings, container, false);

        ListView listView = (ListView) view.findViewById(R.id.setting_list);

        final ArrayList<SettingInfo> settingInfos = new ArrayList<>();

        FileHelper fileHelper = new FileHelper(getActivity().getApplicationContext());
        String text_val = fileHelper.readFromSD(FileHelper.TXT_NAME);
        String[] result_val = text_val.split(",");
        if (result_val.length != 2) {
            SettingInfo setInfo = new SettingInfo();
            setInfo.setName("height");
            setInfo.setText(height_not_found);
            setInfo.setUnit(height_uit);
            settingInfos.add(setInfo);

            SettingInfo setInfo1 = new SettingInfo();
            setInfo1.setName("target weight");
            setInfo1.setText(weight_not_found);
            setInfo1.setUnit(weight_unit);
            settingInfos.add(setInfo1);
        } else {
            SettingInfo setInfo = new SettingInfo();

            setInfo.setName("height");
            setInfo.setText(result_val[0]);
            setInfo.setUnit(height_uit);
            settingInfos.add(setInfo);

            SettingInfo setInfo1 = new SettingInfo();
            setInfo1.setName("target weight");
            setInfo1.setText(result_val[1]);
            setInfo1.setUnit(weight_unit);
            settingInfos.add(setInfo1);
        }

        View headView = inflater.inflate(R.layout.list_setting_head_item, null, false);

        TextView bmiShow = (TextView) headView.findViewById(R.id.bmi_value);
        TextView bmiName = (TextView) headView.findViewById(R.id.bmi_name);
        if (height_not_found.equals(settingInfos.get(0).getText()) ||
                weight_not_found.equals(settingInfos.get(1).getText())) {
            bmiShow.setText("- - -");
            bmiName.setText("- - -");
        } else {
            BMIDemo bmiDemo = new BMIDemo(settingInfos.get(0).getText().substring(0, 3),
                    settingInfos.get(1).getText().substring(0, 5));
            bmiShow.setText(bmiDemo.calculateBMI().toString());
            bmiName.setText(bmiDemo.indexOfBMI(bmiDemo.calculateBMI()));
        }

        View footView = inflater.inflate(R.layout.list_setting_foot_item, null, false);

        Button saveButton = (Button) footView.findViewById(R.id.set_save_button);
        Button editButton = (Button) footView.findViewById(R.id.set_reset_button);
        final EditText editText = (EditText) inflater.inflate(R.layout.list_setting_item, null, false).findViewById(R.id.setting_data);
        editText.setFocusable(false);
        editText.setClickable(false);

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editText.setFocusable(true);
                editText.setClickable(true);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (height_not_found.equals(editText.getText())
//                        || weight_not_found.equals(editText.getText())) {
//                    return;
//                } else {
                StringBuffer sb = new StringBuffer();
                for (SettingInfo info : settingInfos) {
                    sb.append(info.getText());
                    sb.append(',');
                }
                FileHelper fileHelper = new FileHelper(getActivity().getApplicationContext());
                String saveResult = fileHelper.saveDataToSD(FileHelper.TXT_NAME, sb.substring(0, sb.length() - 1));
                if ("OK".equals(saveResult)) {
                    editText.setFocusable(false);
                    editText.setClickable(false);
                    Toast.makeText(getActivity().getApplicationContext(), "save success~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "save fail...", Toast.LENGTH_SHORT).show();
                }
            }
//            }
        });

        listView.addHeaderView(headView);
        listView.addFooterView(footView);

        SettingListAdapter listAdapter = new SettingListAdapter(settingInfos, getContext());
        listView.setAdapter(listAdapter);
        return view;
    }

}

package space.qmen.hellou;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class AddStuFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText add_user_no_edit;
    private Spinner spinner_user_type;
    private int checkedUserType = 0;
    private Boolean hasSpinnerSelected = false;
    private EditText add_user_name_edit;
    private RadioGroup genderRadioGroup;
    private int checkedGender = 1;
    private EditText add_user_pwd_edit;
    private EditText add_user_pwd_again_edit;
    private EditText add_user_tel_edit;
    private EditText add_user_address_edit;
    private Button add_user_btn;

    private DatabaseHelper dbHelper;


    public AddStuFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_add_stu,container,false);

        dbHelper = new DatabaseHelper(AddStuFragment.this.getActivity(), "StuManageSys.db", null, 2);
        dbHelper.getWritableDatabase();

        add_user_no_edit = (EditText) view.findViewById(R.id.add_user_no_edit);
        add_user_name_edit = (EditText) view.findViewById(R.id.add_user_name_edit);
        add_user_pwd_edit = (EditText) view.findViewById(R.id.add_user_pwd_edit);
        add_user_pwd_again_edit = (EditText) view.findViewById(R.id.add_user_pwd_again_edit);
        add_user_tel_edit = (EditText) view.findViewById(R.id.add_user_tel_edit);
        add_user_address_edit = (EditText) view.findViewById(R.id.add_user_tel_edit);

        spinner_user_type = (Spinner) view.findViewById(R.id.spinner_user_type);
        spinner_user_type.setOnItemSelectedListener(this);

        genderRadioGroup = (RadioGroup) view.findViewById(R.id.genderRadioGroup);
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId) {
                    case R.id.btnMan:
                        checkedGender = 1;
                        break;
                    case R.id.btnWoman:
                        checkedGender = 0;
                        break;
                }
                Toast.makeText(AddStuFragment.this.getActivity(),
                        checkedGender + "", Toast.LENGTH_SHORT).show();

            }
        });

        add_user_btn = (Button) view.findViewById(R.id.add_user_btn);
        add_user_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = add_user_pwd_edit.getText().toString();
                String str2 = add_user_pwd_again_edit.getText().toString();

                //显示Toast信息
//                Toast.makeText(AddStuFragment.this.getActivity(), str1 + " vs " + str2, Toast.LENGTH_SHORT).show();
                if(add_user_no_edit.getText().toString().equals("")) {
                    Toast.makeText(AddStuFragment.this.getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                    add_user_no_edit.requestFocus();
                } else if(str1.equals("")) {
                    Toast.makeText(AddStuFragment.this.getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    add_user_pwd_edit.requestFocus();
                }
                else if(!str1.equals(str2)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder
                            (AddStuFragment.this.getActivity());
                    dialog.setTitle("输入错误");
                    dialog.setMessage("密码输入不一致");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.
                            OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface. OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    add_user_pwd_edit.setText("");
                    add_user_pwd_again_edit.setText("");
                    dialog.show();
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("user_no", Integer.parseInt(add_user_no_edit.getText().toString()));
                    values.put("user_no", Integer.parseInt(add_user_no_edit.getText().toString()));
                    values.put("user_type", checkedUserType);
                    values.put("user_name", add_user_name_edit.getText().toString());
                    values.put("user_pwd", add_user_pwd_edit.getText().toString());
                    values.put("user_gender", checkedGender);
                    values.put("user_tel", add_user_tel_edit.getText().toString());
                    values.put("user_address", add_user_address_edit.getText().toString());
                    db.insert("User", null, values);
//                    values.clear();
//                    values.put("name", "The Lost Symbol");
//                    values.put("author", "Dan Brown");
//                    values.put("pages", 510);
//                    values.put("price", 19.95);
//                    db.insert("Book", null, values);

//                    Cursor cursor = db.query("User", null, null, null, null, null,
//                            null, null);
//                    String outputStr = "";
//
//                    if (cursor.moveToFirst()) {
//                        do {
//                            String userNo = cursor.getString(cursor
//                                    .getColumnIndex("user_no"));
//                            String userName = cursor.getString(cursor
//                                    .getColumnIndex("user_name"));
//                            outputStr += "学号: " + userNo + "\n";
//                            outputStr += "用户名： " + userName + "\n";
//                        } while (cursor.moveToNext());
//                    }
//                    cursor.close();
                    Toast.makeText(AddStuFragment.this.getActivity()
                                 , "添加成功"
                                 , Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(hasSpinnerSelected == true) {
        Toast.makeText(AddStuFragment.this.getActivity(), "授权用户类型： " + parent.getItemAtPosition(position).toString(),
                            Toast.LENGTH_SHORT).show();
            checkedUserType = (int)id;
        } else {
            hasSpinnerSelected = true;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

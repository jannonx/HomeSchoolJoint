package com.overdose.homeschooljoint.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.overdose.homeschooljoint.AppApplication;
import com.overdose.homeschooljoint.MainActivity;
import com.overdose.homeschooljoint.R;
import com.overdose.homeschooljoint.activity.LoginActivity;
import com.overdose.homeschooljoint.activity.RegisterActivity;
import com.overdose.homeschooljoint.base.BaseFragment;
import com.overdose.homeschooljoint.bean.ClassBean;
import com.overdose.homeschooljoint.bean.CourseBean;
import com.overdose.homeschooljoint.bean.RegisterQBean;
import com.overdose.homeschooljoint.bean.UserBean;
import com.overdose.homeschooljoint.utils.ActivityUtils;
import com.overdose.homeschooljoint.utils.ApiCallback;
import com.overdose.homeschooljoint.utils.net.AppClient;
import com.overdose.homeschooljoint.utils.ConstantValue;
import com.overdose.homeschooljoint.utils.LogUtils;
import com.overdose.homeschooljoint.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {

    public static final String TAG = "CourseFragment";
    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.rb_man)
    RadioButton rbMan;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;
    @BindView(R.id.rd_group)
    RadioGroup rdGroup;

    @BindView(R.id.et_pw)
    EditText etPw;
    @BindView(R.id.label_code)
    TextView labelCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.label_class)
    TextView labelClass;
    @BindView(R.id.spinner_view)
    Spinner spinnerView;

    private int checkedRadioButtonId;
    private String roleName;
    private String role;

    List<String> classArr = new ArrayList<>();
    List<String> courseArr = new ArrayList<>();
    private List<CourseBean.DataBean> courseData;
    private List<ClassBean.DataBean> classData;
    private String classCode;
    private int checkedRbId;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RegisterFragment newInstance(int CheckedIndex) {
        Bundle args = new Bundle();
        args.putInt(ConstantValue.TITLE, CheckedIndex);
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_register;
    }

    @Override
    public void onUnBind() {

    }

    @Override
    protected void initialization() {
        getStudentClass();
        getCourse();

        checkedRbId = getArguments().getInt(ConstantValue.TITLE, 0);
        roleName = checkedRbId == 0 ? "student" : "teacher";
        role = checkedRbId == 0 ? "1" : "0";//role 1 是学生 role 0 是教师
        labelClass.setText(checkedRbId == 0 ? "班别:" : "课程:");
        labelCode.setText(checkedRbId == 0 ? "学号:" : "职工号:");

        rdGroup.setOnCheckedChangeListener(this);
        rbMan.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.rb_man:
                LogUtils.showLog("rb_man");
                checkedRadioButtonId = 0;
                break;

            case R.id.rb_female:
                checkedRadioButtonId = 1;
                LogUtils.showLog("rb_female");
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.tv_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_btn:
                postRegisterInfo();
                break;
            default:
        }
    }

    private void postRegisterInfo() {
        String name = etName.getText().toString().trim();
        String pw = etPw.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        if (checkInfo(name) || checkInfo(pw) || checkInfo(code)) {
            return;
        }
        RegisterQBean rBean = new RegisterQBean();
        rBean.setRoleName("student");
        rBean.setName(name);
        rBean.setSex(checkedRadioButtonId == 0 ? "男" : "女");
        rBean.setPassword(pw);
        rBean.setCode(code);//学号
        rBean.setClasss(checkedRbId == 0 ? classCode : "");
        rBean.setCoursecode(checkedRbId == 0 ? "" : classCode);
        rBean.setRole(role);
        LogUtils.showLog("RegisterQBean=" + rBean.toString());
        register(rBean);
    }

    private boolean checkInfo(String info) {
        if (TextUtils.isEmpty(info)) {
            ToastUtil.showToast(getContext(), "信息不能为空");
            return true;
        } else {
            return false;
        }

    }

    public void getStudentClass() {
        AppClient.subscribe(AppClient.getServerApi().getClasses(), new ApiCallback<ClassBean>() {

            @Override
            public void onSuccess(ClassBean model) {
                LogUtils.showLog("model=" + model.getData().get(0).getClasscame());
                classData = model.getData();
                if (model.getStatus() == 200) {
                    for (ClassBean.DataBean dataBean : model.getData()) {
                        classArr.add(dataBean.getClasscame());
                    }
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.showToast(getContext(), "注册失败!请重新注册!");
            }

            @Override
            public void onFinish() {
                LogUtils.showLog("model=" + classArr.size());
                String[] stringArray = classArr.toArray(new String[classArr.size()]);
                LogUtils.showLog("model=" + stringArray.length);
                if (checkedRbId == 0) addSpinner(stringArray);
            }
        });
    }

    public void getCourse() {
        AppClient.subscribe(AppClient.getServerApi().getCourse(), new ApiCallback<CourseBean>() {

            @Override
            public void onSuccess(CourseBean model) {
                courseData = model.getData();
                if (model.getStatus() == 200) {
                    for (CourseBean.DataBean dataBean : courseData) {
                        courseArr.add(dataBean.getCoursename());
                    }
                }

            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.showToast(getContext(), "注册失败!请重新注册!");
            }

            @Override
            public void onFinish() {
                LogUtils.showLog("model11=" + courseArr.size());
                String[] stringArray = courseArr.toArray(new String[courseArr.size()]);
                LogUtils.showLog("model11=" + stringArray.length);
                if (checkedRbId != 0) addSpinner(stringArray);
            }
        });
    }

    public void register(RegisterQBean bean) {
        AppClient.subscribe(AppClient.getServerApi().queryRegister(bean.getRoleName(),
                bean.getName(), bean.getCode(), bean.getPassword(), bean.getClasss(), bean.getSex(), bean.getCoursecode(), bean.getRole()),
                new ApiCallback<UserBean>() {


                    @Override
                    public void onSuccess(UserBean model) {
                        LogUtils.showLog("model=" + model.getData().getName());
                        if (model.getStatus() == 200) {
                            AppApplication.getInstance().saveCacheData(ConstantValue.USER_JSON_STRING,
                                    new Gson().toJson(model.getData()));
                            LogUtils.showLog("model=" +  new Gson().toJson(model.getData()));
                            MainActivity.start(getContext());
                            ActivityUtils.removeActivity(LoginActivity.class.getSimpleName());
                            ActivityUtils.removeActivity(RegisterActivity.class.getSimpleName());

                        }
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        ToastUtil.showToast(getContext(), "注册失败!请重新注册!");
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    private void addSpinner(String[] data) {
//        String[] spinnerList = data;
//        LogUtils.showLog("spinnerList=" + data.length);
        classCode = checkedRbId == 0 ? classData.get(0).getClasscode() : courseData.get(0).getCoursecode();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.item_spinner, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerView.setAdapter(adapter);
        spinnerView.setSelection(0, true);


        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                classCode = checkedRbId == 0 ? classData.get(pos).getClasscode() : courseData.get(pos).getCoursecode();
                LogUtils.showLog("classCode=" + classCode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

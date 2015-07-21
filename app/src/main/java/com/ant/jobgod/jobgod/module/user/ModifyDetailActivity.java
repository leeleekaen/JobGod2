package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.ant.jobgod.jobgod.util.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;



@RequiresPresenter(ModifyDetailPresenter.class)
public class ModifyDetailActivity extends BaseActivity<ModifyDetailPresenter>{


    private final int REQUEST_CODE = 1;
    private final int RESULT_CODE = 0;

    public final static String KEY_FLAG = "flag";
    public final static String DATA = "data";

    private UserDetail userData;

    @InjectView(R.id.gender)
    TextView gender;
    @InjectView(R.id.height)
    TextView height;
    @InjectView(R.id.birthday)
    TextView birthday;
    @InjectView(R.id.address)
    TextView address;
    @InjectView(R.id.eduLevel)
    TextView eduLevel;
    @InjectView(R.id.school)
    TextView school;
    @InjectView(R.id.major)
    TextView major;
    @InjectView(R.id.award)
    TextView award;
    @InjectView(R.id.viewAward)
    LinearLayout viewAward;
    @InjectView(R.id.certificate)
    TextView certificate;
    @InjectView(R.id.viewCertificate)
    LinearLayout viewCertificate;
    @InjectView(R.id.character)
    TextView character;
    @InjectView(R.id.viewCharacter)
    LinearLayout viewCharacter;
    @InjectView(R.id.like)
    TextView like;
    @InjectView(R.id.viewLike)
    LinearLayout viewLike;
    @InjectView(R.id.specialty)
    TextView specialty;
    @InjectView(R.id.viewSpecialty)
    LinearLayout viewSpecialty;
    @InjectView(R.id.intro)
    TextView intro;
    @InjectView(R.id.viewIntro)
    LinearLayout viewIntro;

    public enum InfoFlag {
        AWARD, CERTIFICATE, CHARACTER, LIKE, SPECIALTY, INTRO
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifydetail);
        ButterKnife.inject(this);
        init();
    }

    public UserDetail getUserData(){
        return userData;
    }

    public void init() {

        userData=new UserDetail();

        gender.setOnClickListener(v -> new MaterialDialog.Builder(ModifyDetailActivity.this)
                .title("请选择")
                .items(R.array.gender)
                .itemsCallbackSingleChoice(-1, (dialog, view, which, text) -> {
                    if (text == null) {
                        Utils.Toast("请重新选择");
                        return false;
                    }
                    if(text.toString().equals("男")){
                        userData.setGender(1);
                    }
                    if (text.toString().equals("女")){
                        userData.setGender(0);
                    }
                    gender.setText(text.toString());
                    return true;
                })
                .positiveText("确定")
                .show());

        height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ModifyDetailActivity.this)
                        .title("输入身高(数字)")
                        .inputType(InputType.TYPE_CLASS_NUMBER)
                        .inputMaxLength(3)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (input.toString().trim().isEmpty()) {
                                    Utils.Toast("不能为空");
                                    return;
                                }
                                userData.setHeight(Integer.parseInt(input.toString()));
                                height.setText(input + "cm");
                            }
                        }).show();
            }
        });

        eduLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ModifyDetailActivity.this).title("请选择")
                        .items(R.array.eduLevel)
                        .itemsCallbackSingleChoice(-1, (dialog, view, which, text) -> {
                            if (text == null) {
                                Utils.Toast("请重新选择");
                                return false;
                            }
                            userData.setEduLevel(text.toString());
                            eduLevel.setText(text.toString());
                            return true;
                        })
                        .positiveText("确定")
                        .show();
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ModifyDetailActivity.this)
                        .title("输入地址")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .inputMaxLength(32)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (input.toString().trim().isEmpty()) {
                                    Utils.Toast("不能为空");
                                    return;
                                }
                                userData.setAddress(input.toString());
                                address.setText(input.toString());
                            }
                        }).show();
            }
        });

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ModifyDetailActivity.this)
                        .title("输入学校")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .inputMaxLength(32)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (input.toString().trim().isEmpty()) {
                                    Utils.Toast("不能为空");
                                    return;
                                }
                                userData.setSchool(input.toString());
                                school.setText(input.toString());
                            }
                        }).show();
            }
        });

        major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ModifyDetailActivity.this)
                        .title("输入专业")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .inputMaxLength(32)
                        .input("", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if (input.toString().trim().isEmpty()) {
                                    Utils.Toast("不能为空");
                                    return;
                                }
                                userData.setMajor(input.toString());
                                major.setText(input.toString());
                            }
                        }).show();
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            final Calendar birth = Calendar.getInstance();
                                            DatePickerDialog dpd = DatePickerDialog.newInstance(
                                                    new DatePickerDialog.OnDateSetListener() {
                                                        @Override
                                                        public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {
                                                            birth.set(i, i1, i2);
                                                            if(birth.getTimeInMillis()>=System.currentTimeMillis()){
                                                                Utils.Log("输入有误");
                                                                return;
                                                            }
                                                            ((TextView) v).setText(new TimeTransform(i,i1,i2).toString(new RecentDateFormater()));
                                                            userData.setBirthday(birth.getTimeInMillis());
                                                        }
                                                    },
                                                    birth.get(Calendar.YEAR),
                                                    birth.get(Calendar.MONTH),
                                                    birth.get(Calendar.DAY_OF_MONTH)

                                            );
                                            dpd.show(getFragmentManager(), "请选择日期");
                                        }
                                    });



        viewAward.setOnClickListener(v -> getPresenter().awardToModifyDataActivityForResult(InfoFlag.AWARD, award));
        viewCertificate.setOnClickListener(v -> getPresenter().certificateToModifyDataActivityForResult(InfoFlag.CERTIFICATE, certificate));
        viewCharacter.setOnClickListener(v -> getPresenter().characterToModifyDataActivityForResult(InfoFlag.CHARACTER, character));
        viewIntro.setOnClickListener(v -> getPresenter().introToModifyDataActivityForResult(InfoFlag.INTRO, intro));
        viewLike.setOnClickListener(v -> getPresenter().likeToModifyDataActivityForResult(InfoFlag.LIKE, like));
        viewSpecialty.setOnClickListener(v -> getPresenter().specialtyToModifyDataActivityForResult(InfoFlag.SPECIALTY, specialty));
    }

    /**
     * 网络请求参数
     */
    public void submitInfo() {
        RequestMap param = new RequestMap();
        param.put("gender", gender.getText().toString());
        param.put("height", height.getText().toString());
        param.put("address", address.getText().toString());
        param.put("edulevel", eduLevel.getText().toString());
        param.put("school", school.getText().toString());
        param.put("major", major.getText().toString());
        param.put("award", award.getText().toString());
        param.put("certificate", certificate.getText().toString());
        param.put("character", character.getText().toString());
        param.put("like", like.getText().toString());
        param.put("specialty", specialty.getText().toString());
        param.put("intro", intro.getText().toString());

        getPresenter().submitInfo(param);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE && data != null) {
            InfoFlag flag = (InfoFlag) data.getSerializableExtra(KEY_FLAG);
            switch (flag) {
                case AWARD:
                    award.setText(data.getStringExtra(DATA));
                    break;
                case CERTIFICATE:
                    certificate.setText(data.getStringExtra(DATA));
                    break;
                case CHARACTER:
                    character.setText(data.getStringExtra(DATA));
                    break;
                case LIKE:
                    like.setText(data.getStringExtra(DATA));
                    break;
                case SPECIALTY:
                    specialty.setText(data.getStringExtra(DATA));
                    break;
                case INTRO:
                    intro.setText(data.getStringExtra(DATA));
                    break;
            }
        }
    }

    /**
     * 创建一个编辑对话框
     *
     * @param title     标题
     * @param maxLength 输入最大字符串长度
     * @param hint      提示文字
     * @param text      显示选择内容控件
     */

    public void createEditDialog(String title, int maxLength, String hint, TextView text) {
        new MaterialDialog.Builder(this)
                .title(title)
                .titleColor(R.color.DeepOrange)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .inputMaxLength(maxLength)
                .input(hint, "", (dialog, input) -> {
                    if (input.toString().trim().isEmpty()) {
                        Utils.Toast("标题不能为空");
                        return;
                    } else
                        text.setText(input.toString());
                }).show();
    }

    /**
     * 创建一个单选项对话框
     *
     * @param title    标题
     * @param strArray 数组
     * @param display  显示选择后的内容控件
     */
    public void createSingleChoiceDialog(String title, int strArray, TextView display) {
        new MaterialDialog.Builder(this)
                .title(title)
                .items(strArray)
                .itemsCallbackSingleChoice(-1, (dialog, view, which, text) -> {
                    if (text == null) {
                        Utils.Toast("请重新选择");
                        return false;
                    }
                    display.setText(text.toString());
                    return true;
                })
                .positiveText("确定")
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_info_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submit:
                submitInfo();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

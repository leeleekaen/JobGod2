package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.bijection.Presenter;

/**
 * Created by alien on 2015/7/10.
 */
public class ModifyDetailPresenter extends Presenter<ModifyDetailActivity> {

    private final int REQUEST_CODE = 1;

    private Intent intent;
    private UserDetail userDetail;

    @Override
    protected void onCreate(ModifyDetailActivity view,Bundle savedState) {
        super.onCreate(view,savedState);

        intent = new Intent(getView(), TextWriteActivity.class);

    }

    @Override
    protected void onCreateView(ModifyDetailActivity view) {
        super.onCreateView(view);
        getMyData();
    }

    /**
     * 获取个人信息
     */
    public void getMyData() {
        userDetail=AccountModel.getInstance().getUserAccount().getDetail();
        getView().setData(userDetail);
    }

    /**
     * 更新个人信息
     */
    public void updateMyDetail() {
        getView().getExpansion().showProgressDialog("提交中");
        UserModel.getInstance().updateUserDetail(getView().getUserDetail(), new StatusCallback() {
            @Override
            public void success(String info) {
                AccountModel.getInstance().updateAccountData();
                getView().finish();
            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                getView().getExpansion().dismissProgressDialog();
                switch (status) {
                    case 200:
                        Utils.Toast("保存成功");
                        break;
                }
            }
        });
    }

    public void awardToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setAward(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void certificateToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setCertificate(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void characterToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setCharacter(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void introToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setIntro(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void likeToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setLike(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

    public void specialtyToModifyDataActivityForResult(ModifyDetailActivity.InfoFlag flag, TextView view) {
        intent.putExtra(ModifyDetailActivity.KEY_FLAG, flag);
        intent.putExtra("data", view.getText().toString());
        getView().getUserDetail().setSpecialty(view.getText().toString());
        getView().startActivityForResult(intent, REQUEST_CODE);
    }

}

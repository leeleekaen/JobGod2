package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.ManagerModel;
import com.ant.jobgod.jobgod.model.bean.Manager;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/22.
 */
public class ManagerBackedgePresenter extends BasePresenter<ManagerBackedgeActivity> {

    private int jobId;
    private Manager mData;

    public final int MANAGER_QUEST_CODE=100;
    public final int MANAGER_RESULT_CODE=101;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getContractData();
    }

    @Override
    protected void onCreateView(ManagerBackedgeActivity view) {
        super.onCreateView(view);
        if (mData != null) {
            getView().setData(mData);
        }
    }

    /**
     * 获取合同详细
     */
    public void getContractData() {
        jobId = getView().getIntent().getIntExtra("id",0);
        ManagerModel.getInstance().getMangerData(jobId, new DataCallback<Manager>() {
            @Override
            public void success(String info, Manager data) {
                getView().setData(mData = data);
            }
        });
    }

    /**
     * 取消报名
     */
    public void cancelApply() {
        ManagerModel.getInstance().cancelApply(mData.getId(), new StatusCallback() {
            @Override
            public void success(String info) {
            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                switch (status){
                    case 200:
                        Utils.Toast("取消成功!");
                        getView().setBtnStatus(false);
                        getView().setResult(MANAGER_RESULT_CODE);
                        break;
                }
            }
        });
    }

    /**
     * 评价商家
     */
    public void evaluateBiz() {
        ManagerModel.getInstance().jodgeBiz(mData.getId(), 5, getView().getContent(), new StatusCallback() {
            @Override
            public void success(String info) {
                if (info.equals("success")) {
                    Utils.Toast("评价提交成功!");
                }
            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                if (status == 201) {
                    Utils.Toast("不能重复评价");
                }
            }
        });
    }

}

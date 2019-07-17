package com.common.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @description：
 * @author：bux on 2018/8/13 9:35
 * @email: 471025316@qq.com
 */
public abstract class BaseFragmentActivity extends BaseActivity {

    @Override
    protected void init() {
        int containerId = getContainerId();
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = getSupportFragmentManager().findFragmentById(containerId);
        if (fragment == null) {
            fragment = getFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(containerId, fragment);
            transaction.commit();
        }
    }

    protected abstract Fragment getFragment();

    protected abstract int getContainerId();
}

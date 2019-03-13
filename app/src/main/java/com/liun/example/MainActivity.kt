package com.liun.example

import android.app.Activity
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.liun.example.adapter.MyViewAdapter
import com.liun.example.base.BaseActivity
import com.liun.example.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitleGone()
        viewPager.adapter = MyViewAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = resources.getString(R.string.tab_01)
        tabLayout.getTabAt(0)?.icon = resources.getDrawable(R.drawable.selector_buttom_01)
        tabLayout.getTabAt(1)?.text = resources.getString(R.string.tab_02)
        tabLayout.getTabAt(1)?.icon = resources.getDrawable(R.drawable.selector_buttom_02)
        tabLayout.getTabAt(2)?.text = resources.getString(R.string.tab_03)
        tabLayout.getTabAt(2)?.icon = resources.getDrawable(R.drawable.selector_buttom_03)
        tabLayout.getTabAt(3)?.text = resources.getString(R.string.tab_04)
        tabLayout.getTabAt(3)?.icon = resources.getDrawable(R.drawable.selector_buttom_04)
        tabLayout.getTabAt(4)?.text = resources.getString(R.string.tab_05)
        tabLayout.getTabAt(4)?.icon = resources.getDrawable(R.drawable.selector_buttom_05)

    }

    var mLastPressTime = 0L
    override fun onBackPressed() {
        var nowPressTime = System.currentTimeMillis()
        if ((nowPressTime - mLastPressTime) >= 2000) {
            mLastPressTime = nowPressTime
            ToastUtils.showShort("再按一次退出程序")
        } else {
            finish()
            System.exit(0)
        }
    }

    override fun setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null)
    }
}

package com.ruiyi.testioc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ruiyi.testioc.inject.LayoutInject;
import com.ruiyi.testioc.inject.ViewInject;

import java.lang.reflect.Field;

/**
 * Created by ruiyi on 2017/9/15.
 */

public class InJectActivity extends AppCompatActivity {

    private int mLayoutId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectLayout();
        InjectView();
    }

    /**
     * 注解布局Layout id
     */
    private void InjectLayout() {
        Class<?> clazz = this.getClass();
        if (clazz.getAnnotations() != null) {
            if (clazz.isAnnotationPresent(LayoutInject.class)) {
                LayoutInject inject = clazz.getAnnotation(LayoutInject.class);
                mLayoutId = inject.value();
                setContentView(mLayoutId);
            }
        }

    }

    /**
     * 解析注解view id
     */
    private void InjectView() {
        if (mLayoutId <= 0)
            return;

        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();//获得成员变量
        for (Field field : fields) {
            //判断是否有注解
            try {
                if (field.getAnnotations() != null) {
                    if (field.isAnnotationPresent(ViewInject.class)) {//如果属于这个注解为这个控件设置属性
                        field.setAccessible(true);//允许修改反射属性
                        ViewInject inject = field.getAnnotation(ViewInject.class);
                        field.set(this, this.findViewById(inject.value()));
                    }
                }
            } catch (Exception e) {
                Log.e("ruiyi", "not found view id!");
            }
        }
    }
}



package vovkab.daggerexample.mock;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MockApplicationCompat extends Application {

    public MockApplicationCompat(Context context) {
        attachBaseContext(context);
    }

    @Override
    public void attachBaseContext(Context base) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            super.attachBaseContext(base);
        } else {
            try {
                Class<Application> applicationClass = Application.class;
                Method attach = applicationClass.getDeclaredMethod("attach", Context.class);
                attach.setAccessible(true);
                attach.invoke(this, base);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}

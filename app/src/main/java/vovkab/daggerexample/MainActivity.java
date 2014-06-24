package vovkab.daggerexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;


public class MainActivity extends Activity {

    @Inject HelloController mHelloController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.getInjectable(this).inject(this);

        setContentView(R.layout.activity_main);

        TextView helloView = (TextView) findViewById(R.id.hello);
        helloView.setText(mHelloController.getHello());
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static void startActivity(Context context) {
        context.startActivity(createIntent(context));
    }
}

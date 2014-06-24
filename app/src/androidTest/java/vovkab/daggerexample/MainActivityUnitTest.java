package vovkab.daggerexample;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.test.ActivityUnitTestCase;
import android.widget.TextView;

import dagger.Module;
import dagger.Provides;
import vovkab.daggerexample.mock.InjectableAppMock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainActivityUnitTest extends ActivityUnitTestCase<MainActivity> {
    public static final String TEST_HELLO_TEXT = "Test hello";

    private Application mApplication;
    private Context mContext;

    public MainActivityUnitTest() {
        super(MainActivity.class);
    }

    @Module(injects = MainActivity.class)
    final class TestModule {
        @Provides HelloController provideHelloController() {
            HelloController helloController = mock(HelloController.class);
            when(helloController.getHello()).thenReturn(TEST_HELLO_TEXT);
            return helloController;
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mContext = new ContextWrapper(getInstrumentation().getTargetContext()) {
            @Override
            public Context getApplicationContext() {
                return mApplication;
            }
        };

        mApplication = new InjectableAppMock(mContext) {
            @Override public Object[] getModules() {
                return new Object[]{new TestModule()};
            }
        };

        setApplication(mApplication);
    }

    public void testHelloControllerValueSet() {
        setActivityContext(mContext);
        startActivity(MainActivity.createIntent(mContext), null, null);

        // Application context is AppMock
        assertTrue(getActivity().getApplicationContext() instanceof InjectableAppMock);

        TextView helloView = (TextView) getActivity().findViewById(R.id.hello);
        assertEquals(TEST_HELLO_TEXT, helloView.getText());
    }
}

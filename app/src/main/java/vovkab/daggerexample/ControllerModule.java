package vovkab.daggerexample;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = MainActivity.class
)
public final class ControllerModule {
    @Provides HelloController provideHelloController() {
        return new HelloController();
    }
}

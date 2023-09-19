package phoneApp.interfaces;

import phoneApp.objects.Callable;

public interface Call {
    void start();
    void accept();
    Callable end();
}

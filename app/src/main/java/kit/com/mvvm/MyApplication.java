package kit.com.mvvm;

import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import kit.com.mvvm.database.DatabseRepositoryManager;
import kit.com.mvvm.architecture.ProjectRepository;

public class MyApplication extends Application {

    private DatabseRepositoryManager databseManager;
    private static MyApplication mInstance;
    private ProjectRepository mProjectRepository;
    private Context context;

    public static Context getContext() {
        return getInstance().context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = getApplicationContext();
        mInstance = this;
        databseManager = new DatabseRepositoryManager(context);
        mProjectRepository = new ProjectRepository();

    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public static DatabseRepositoryManager getDatabseManager() {
        return getInstance().databseManager;
    }

    public static ProjectRepository getProjectRepository() {
        return getInstance().mProjectRepository;
    }

}

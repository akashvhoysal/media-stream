package mediaserver;


import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

/**
 * Created with IntelliJ IDEA.
 * User: akash.v
 * Date: 9/5/13
 * Time: 12:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class MediaService extends Application<MediaServerConfig> {

    @Override
    public void initialize(Bootstrap<MediaServerConfig> bootstrap) {
        GuiceBundle<MediaServerConfig> guiceBundle = GuiceBundle.<MediaServerConfig>newBuilder()
                .addModule(new MediaServerModule())
                .setConfigClass(MediaServerConfig.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build(Stage.DEVELOPMENT);
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(guiceBundle);
    }


    public static void main(String[] args) throws Exception {
        new MediaService().run(args);
    }

    @Override
    public void run(MediaServerConfig mediaServerConfig, Environment environment) throws Exception {

    }
}

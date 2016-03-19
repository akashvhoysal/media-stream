package mediaserver.resources;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import mediaserver.MediaServerConfig;
import mediaserver.core.services.FileDisplay;
import mediaserver.views.FolderView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by akash.v on 11/03/16.
 */

@Path("/browse")
@Produces(MediaType.TEXT_HTML)
@Singleton

public class BrowseResource {

    InputStreamReader reader;
    Runtime runtime;
    MediaServerConfig config;

    @Inject
    public BrowseResource(MediaServerConfig config) {
        this.config = config;
        runtime = Runtime.getRuntime();
    }

    @GET
    public FolderView getRoot() throws IOException {

        File file;
        file = new File(config.getMediaRoot());
        Set<String> folders = Sets.newConcurrentHashSet();
        Set<String> files = Sets.newConcurrentHashSet();

        if(!file.exists()){
            throw new WebApplicationException(404);
        }

        for(File f:file.listFiles()){
            if(f.isDirectory()){
                folders.add(f.getName());
            }
            else {
                files.add(f.getName());
            }
        }
        return new FolderView(new FileDisplay(files, folders, ""));
    }

    @GET
    @Path("/{path}")
    public FolderView getSellerPincode(@PathParam("path") String path) throws IOException {

        File file;
        file = new File(config.getMediaRoot() + path);
        Set<String> folders = Sets.newConcurrentHashSet();
        Set<String> files = Sets.newConcurrentHashSet();

        if(!file.exists()){
            throw new WebApplicationException(404);
        }

        for(File f:file.listFiles()){
            if(f.isDirectory()){
                folders.add(f.getName());
            }
            else {
                files.add(f.getName());
            }
        }
        return new FolderView(new FileDisplay(files, folders, path));
    }
}

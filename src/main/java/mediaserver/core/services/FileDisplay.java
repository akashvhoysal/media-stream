package mediaserver.core.services;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Set;

/**
 * Created by akash.v on 13/03/16.
 */
@Getter
@Setter
public class FileDisplay {

    Set<String> folders;
    Set<String> files;

    String path;

    public FileDisplay(Set<String> files, Set<String> folders,String path) {

        this.files = files;
        this.folders = folders;
        this.path = massage(path);
    }

    private String massage(String path) {
        while(path.startsWith("/")){
            path = path.substring(1,path.length());
        }
        return path.replace("/","%2F")+ "%2F";
    }


}

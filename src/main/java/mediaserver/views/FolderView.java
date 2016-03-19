package mediaserver.views;

import io.dropwizard.views.View;
import lombok.Getter;
import mediaserver.core.services.FileDisplay;

/**
 * Created by akash.v on 19/03/16.
 */
@Getter
public class FolderView extends View {

    private final FileDisplay fileDisplay;

    public FolderView(FileDisplay fileDisplay) {
        super("browse.ftl");
        this.fileDisplay = fileDisplay;
    }
}

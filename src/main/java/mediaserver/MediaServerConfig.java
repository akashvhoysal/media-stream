package mediaserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by akash.v on 24/03/15.
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaServerConfig extends Configuration {

    @JsonProperty
    String mediaRoot ;
}

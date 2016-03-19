package mediaserver.resources;

import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import mediaserver.MediaServerConfig;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by akash.v on 14/03/16.
 */

@Path("/stream")

public class PlayResource {


    private MediaServerConfig config;

    @Inject
    public PlayResource(MediaServerConfig config){
        this.config = config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response streamExample() {
        StreamingOutput stream = new StreamingOutput() {
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write("test");
                writer.flush();
            }
        };
        return Response.ok(stream).build();
    }

    @GET
    @Path("{path}")
    @Produces("audio/mp3")
    public Response getMessage(@PathParam("path") String path) throws IOException{
        String str= config.getMediaRoot()+path;
        System.out.println("Called");
        RandomAccessFile f=new RandomAccessFile(str, "r");
        String r = null;
        int off=0;
        int to=(int)f.length();
        byte[] data ;
        if(r!=null){
            String from=r.split("=")[1].split("-")[0];
            String t=r.split("=")[1].split("-")[1];
            off=Integer.parseInt(from);
            to=Integer.parseInt(t);

        }
        data= new byte[to-off];
        f.readFully(data, off, to-off);

        Response.ResponseBuilder res=Response.ok(data)
                .header("Accept-Ranges","bytes")
                .header("Content-Range:", "bytes "+off+"-"+to+"/"+data.length)
                .header("Pragma", "no-cache");;

        if(r==null){
            res=res.header("Content-Length", data.length);
        }
        f.close();

        Response ans=res.build();

        return ans;


    }
}


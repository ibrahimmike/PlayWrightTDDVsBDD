package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.URIParameter;
import java.util.Objects;



public class ResourceLoader {
    //private static final Logger loger = LoggerFactory.getLogger(ResourceLoader.class);

    public static InputStream getResource() {


        InputStream stream;
        try {

           // stream = Files.newInputStream(Path.of("src/test/resources/config.properties"));
            stream = Files.newInputStream(Paths.get("src/test/resources/config.properties"));


        } catch (IOException e) {
            try {

                stream = Files.newInputStream(Paths.get("config.properties"));

            } catch (IOException c) {
                throw new RuntimeException("Both the paths are not returning  the values of the configurations");


            }
        }
        if (Objects.isNull(stream)){

            throw new RuntimeException("Stream was not set to a value");
        }
        return stream;
    }




}

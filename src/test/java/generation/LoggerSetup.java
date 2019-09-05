package generation;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;

public class LoggerSetup {

    public LoggerSetup() {

        //Get "log4j" configuration file
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("log4j.properties");

        if (in == null) throw new NullPointerException("/'log4j.properties/' configuration file is not found under resource/configuration-files/ directory");

        //Configure log4j
        PropertyConfigurator.configure(in);

        //Close input stream
        try{
            in.close();
        }catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}

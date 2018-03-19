/**
 * CheckerController.java 10-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * CheckerController.java 10-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.info.BuildProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableScheduling
@EnableAutoConfiguration
public class CheckerController {

    private static final Logger log = LoggerFactory.getLogger(CheckerController.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static boolean fallando = false;

    @Autowired
    BuildProperties buildProperties;

    @RequestMapping("/")
    @ResponseBody
    String checkStatus() {
        final RestTemplate restTemplate = new RestTemplate();
        final Status status = restTemplate.getForObject("https://momoko.es:5000/health", Status.class);
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info(status.getStatus());
        return status.getStatus();
        //
    }

    // nohup java -jar -Xms64m -Xmx512m -Dspring.profiles.active=server -Dspring.datasource.password=DLlCfAjs1
    // backend-0.8.1-SNAPSHOT.jar

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        final RestTemplate restTemplate = new RestTemplate();
        try {
            final Status status = restTemplate.getForObject("http://momoko.es:5000/health", Status.class);
            log.info(dateFormat.format(new Date()) + ": " + this.buildProperties.getArtifact() + "-"
                    + this.buildProperties.getVersion() + ": " + status.getStatus());
            if (fallando) {
                fallando = false;
            }
        } catch (final ResourceAccessException ex) {
            if (!fallando) {
                final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
                // Es la primera vez que falla
                fallando = true;
                // Mail.sendEmail("Prueba", "Prueba");
                if (!isWindows) {
                    final ProcessBuilder pb = new ProcessBuilder("sh", "script.sh", this.buildProperties.getVersion());
                    Process p;
                    try {
                        p = pb.start();

                        final StringBuffer output = new StringBuffer();
                        if (p.getErrorStream().read() != -1) {
                            System.err.println(p.getErrorStream());
                        } else {
                            System.out.println(p.getInputStream());
                        }

                        System.out.println(output.toString());
                    } catch (final IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(CheckerController.class, args);
    }

}

/**
 * CheckerController.java 10-mar-2018
 *
 * Copyright 2018 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CheckerController.java 10-mar-2018
 * <p>
 * Copyright 2018 RAMON CASARES.
 *
 * @author Ramon.Casares.Porto@gmail.com
 */

@Controller
@EnableScheduling
@EnableAutoConfiguration
public class CheckerController {

    private static final Logger log = LoggerFactory.getLogger(CheckerController.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static boolean fallando = false;

    private RestTemplate restTemplate;

    public CheckerController() {

    }

    private BuildProperties buildProperties;

    @Autowired
    public CheckerController(BuildProperties buildProperties){
        this.buildProperties = buildProperties;
        this.restTemplate = new RestTemplate();
    }

    @RequestMapping("/")
    @ResponseBody
    String checkStatus() {

        final String status = this.restTemplate.getForObject("https://momoko.es:5000/public/health", String.class);
        log.info("The time is now {}", dateFormat.format(new Date()));
        log.info(status);
        return status;
        //
    }

    // nohup java -jar -Xms64m -Xmx512m -Dspring.profiles.active=server -Dspring.datasource.password=
    // backend-0.8.1-SNAPSHOT.jar

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        try {
            final String status = this.restTemplate.getForObject("https://momoko.es/public/health", String.class);
            log.info(dateFormat.format(new Date()) + ": " + this.buildProperties.getArtifact() + "-"
                    + this.buildProperties.getVersion() + ": " + status);
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

    @Scheduled(fixedRate = 300000)
    public void renderIndex() {
        this.restTemplate.getForObject("https://momoko.es/", String.class);
        log.info(dateFormat.format(new Date()) + ": " + this.buildProperties.getArtifact() + "-"
                + this.buildProperties.getVersion() + ": INDEX RENDERED");
    }

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(CheckerController.class, args);
    }

}

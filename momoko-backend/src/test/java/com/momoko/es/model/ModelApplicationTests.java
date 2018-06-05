/**
 * ModelApplicationTests.java 14-ago-2017
 *
 * Copyright 2017 RAMON CASARES.
 * @author Ramon.Casares.Porto@gmail.com
 */
package com.momoko.es.model;

import com.momoko.es.backend.ModelApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ModelApplication.class)
public class ModelApplicationTests {


    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

     @Test
    public void contextLoads() {
         String toEncode = "momadm.18";
         System.out.println(this.passwordEncoder.encode(toEncode));
    }


}

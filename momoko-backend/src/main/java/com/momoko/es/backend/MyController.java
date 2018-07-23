package com.momoko.es.backend;

import com.momoko.es.backend.security.MomokoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core")
public class MyController extends MomokoController<User, Long> {

}

package com.momoko.es.controllers;

import com.momoko.es.entities.User;
import com.momoko.es.jpa.MomokoController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core")
public class MyController extends MomokoController<User, Long> {

}
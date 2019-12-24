package com.example.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    // all this request will match to this Request Mapping
    @RequestMapping({"", "/", "index", "index.hrml"})
    public String index() {

        // look for template called "index"
        return "index";
    }

    @RequestMapping("/oups")
    public String Oups() {
        return "notImplemented";
    }
}

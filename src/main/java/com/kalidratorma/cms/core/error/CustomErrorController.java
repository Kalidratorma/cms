package com.kalidratorma.cms.core.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error")
public class CustomErrorController {

    @RequestMapping
    @ResponseBody
    public String generalError() {
        return "Error";
    }
}

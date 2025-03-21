package cl.caranguizh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador de prueba.
 */
@Controller
public class TestController {
    @GetMapping("/simple")
    @ResponseBody
    public String doGet() {
        return "Hello from TestController";
    }
}
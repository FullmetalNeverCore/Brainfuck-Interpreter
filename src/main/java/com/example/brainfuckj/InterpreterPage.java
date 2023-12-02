package com.example.brainfuckj;

import org.springframework.http.ResponseEntity;  // Import ResponseEntity
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Controller
public class InterpreterPage {

    private String result;

    private Interpreter i = new Interpreter();

    @GetMapping("/")
    public String hw() {
        return "page";
    }


    @PostMapping("/submit")
    public ResponseEntity<?> interp(@RequestBody Data text) {
        if (text.getData().replaceAll("\\s", "").matches("[<>+\\-.\\,\\[\\]]+")) {
            String result = new String(i.Inter(text.getData()));
            return ResponseEntity.ok().body(Map.of("output", result));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid input"));
        }
    }
}

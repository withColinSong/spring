package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String Hello(Model model) {
    model.addAttribute("data", "hello!!");
        return "hello";
    }

    @ResponseBody
    @GetMapping("hello-api")
    public Hello HelloFn(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName("song");
        return hello;
    }

    static class Hello {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

    }


}

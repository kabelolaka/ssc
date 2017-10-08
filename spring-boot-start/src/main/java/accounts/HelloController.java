package accounts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kabelo on 2017/10/08.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}


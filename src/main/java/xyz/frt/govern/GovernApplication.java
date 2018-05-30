package xyz.frt.govern;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.common.JsonResult;

@RestController
@SpringBootApplication
@MapperScan("xyz.frt.govern.dao")
public class GovernApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovernApplication.class, args);
    }

    @GetMapping("/")
    public JsonResult home() {
        return JsonResult.success("Welcome to Govern Application");
    }

    @GetMapping("/401")
    public JsonResult _401() {
        return JsonResult.error(401, "Token Invalid or Expired, Try to re-Login");
    }

    @GetMapping("/403")
    public JsonResult _403() {
        return JsonResult.error(403, "Access Denied");
    }

    @GetMapping("/404")
    public JsonResult _404() {
        return JsonResult.error(404, "Request Not Found");
    }


}

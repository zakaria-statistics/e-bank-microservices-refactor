package net.zakaria.customerservice.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import net.zakaria.customerservice.config.ConfigParams;

import java.util.Map;


@RestController
@RefreshScope
public class ConfigTestRestController {
//    @Value("${customer.params.dev1}")
//    private String x;
//    @Value("${customer.params.dev2}")
//    private String y;

    private ConfigParams configParams;

    public ConfigTestRestController(ConfigParams configParams) {
        this.configParams = configParams;
    }

//    @GetMapping("/config1")
//    public Map<String, String> params1(){
//        return Map.of("x",x,"y",y);
//    }

    @GetMapping("/config2")
    public ConfigParams params2(){
        return configParams;
    }

}

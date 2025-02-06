package net.zakaria.customerservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter @Getter @AllArgsConstructor @NoArgsConstructor
@ConfigurationProperties(prefix = "customer.params")
public class ConfigParams {
    private String dev1;
    private String dev2;
    
}

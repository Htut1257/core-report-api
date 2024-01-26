package cv.report.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@EnableR2dbcAuditing
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Core Report", version = "1.0", description = "Core Report APIs v1.0"))
public class CoreReportApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreReportApiApplication.class, args);
    }

}

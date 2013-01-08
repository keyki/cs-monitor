package org.game.cs.web.init;

import org.game.cs.dal.init.DaoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(value = {DaoConfig.class})
@ImportResource(value = "WEB-INF/spring/spring-security.xml")
public class RootConfig {

}

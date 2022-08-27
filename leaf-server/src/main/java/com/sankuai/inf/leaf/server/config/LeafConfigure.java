package com.sankuai.inf.leaf.server.config;

import com.sankuai.inf.leaf.exception.InitException;
import com.sankuai.inf.leaf.service.SegmentService;
import com.sankuai.inf.leaf.service.SnowflakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * LeafConfigure
 *
 * @author sheep
 */
@EnableConfigurationProperties(LeafProperties.class)
@Configuration
public class LeafConfigure {

    private static final Logger logger = LoggerFactory.getLogger(LeafConfigure.class);

    @Bean
    public SegmentService segmentService(LeafProperties properties) throws SQLException, InitException {
        if (properties != null && properties.getSegment() != null && properties.getSegment().isEnable()) {
            LeafProperties.Segment segment = properties.getSegment();
            return new SegmentService(segment.getUrl(), segment.getUsername(), segment.getPassword());
        }

        logger.warn("init leaf segment ignore properties is {}", properties);
        return null;
    }

    @Bean
    public SnowflakeService snowflakeService(LeafProperties properties) throws InitException {
        if (properties != null && properties.getSnowflake() != null && properties.getSnowflake().isEnable()) {
            LeafProperties.Snowflake snowflake = properties.getSnowflake();
            return new SnowflakeService(snowflake.getAddress(), snowflake.getPort());
        }

        logger.warn("init leaf segment ignore properties is {}", properties);
        return null;
    }

}

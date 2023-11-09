package com.xhj.message.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: xhj
 * @Date: 2023/11/09/19:39
 * @Description:
 */
@ConfigurationProperties(prefix = "xhj.thread")
@Data
public class ThreadPoolConfigProperties {
    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}

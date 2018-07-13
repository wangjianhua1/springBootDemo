/**
 * Copyright (C), 2012-2020
 * File Name: XmlSpringConfig.java
 * Encoding: UTF-8
 * Date: 2018年3月13日
 * History: 
 */
package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 引入配置
 *
 * @author CrayHu(hukelei@jd.com)
 * @version Revision: 1.00 Date: 2018年3月13日
 */
@Configuration
@ImportResource(locations="classpath:spring/spring*.xml")
public class XmlSpringConfig {

}

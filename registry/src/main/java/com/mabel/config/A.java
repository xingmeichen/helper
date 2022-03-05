package com.mabel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2021-11-30 19:19
 **/
@Configuration
public class A {

    @Autowired
    private B b;
}
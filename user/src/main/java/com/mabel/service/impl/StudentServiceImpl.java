package com.mabel.service.impl;

import com.mabel.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("student")
public class StudentServiceImpl implements PersonService {

    @Override
    public String task() {
        return "Learn";
    }

    public static void main(String[] args) {
        System.out.println("");
    }
}

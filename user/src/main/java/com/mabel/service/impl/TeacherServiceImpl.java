package com.mabel.service.impl;

import com.mabel.service.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("teacher")
public class TeacherServiceImpl implements PersonService {

    @Override
    public String task() {
        return "Teach";
    }
}

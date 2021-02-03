package com.rap.restservicevalidator.service;

import java.io.File;

public interface ValidatorService<T> {

    boolean validate(File input);
}

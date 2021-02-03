package com.rap.restservicevalidator.service;

public interface UpaService<T, U> {
    T processUPA(U file);
}

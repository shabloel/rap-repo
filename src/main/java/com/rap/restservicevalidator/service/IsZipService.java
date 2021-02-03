package com.rap.restservicevalidator.service;

import org.springframework.web.multipart.MultipartFile;

public interface IsZipService<T> {

    boolean isZip(T file);
}

package com.centre.rediscache.response;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record ResponseEmployee(Long id, String fullname, String email, LocalDate dob,Integer age) {

}

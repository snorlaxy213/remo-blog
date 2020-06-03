package com.remo.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor //可以生成带参或者不带参的构造方法
public class Person {

    private String lastName;
    private String firstName;
}

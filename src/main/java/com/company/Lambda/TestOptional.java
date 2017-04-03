package com.company.Lambda;

import java.util.Optional;

/**
 * Created by eli9 on 3/17/2017.
 */
public class TestOptional {
    public static void main(String[] args) {
        Optional empty = Optional.ofNullable(null);

        Optional<String> name = Optional.of("Sanaulla");

        System.out.println(empty.orElse("Test"));

        System.out.println(empty.orElseGet(() -> "Default Value"));

        Optional<String> anotherName = Optional.of("Sana");
        Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
    //输出：name长度不足6字符
        System.out.println(shortName.orElse("The name is less than 6 characters"));

        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));
    }
}

package com.demo.productservice.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import lombok.Builder;
import lombok.Data;
//import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EqualsHelperTest {
//    @Test
//    public void shouldBeEqualsObject() {
//        TestObj obj1 = TestObj.builder()
//                .stringField("stringField")
//                .testNestedObjs(ImmutableSet.of(TestNestedObj.builder()
//                        .code("code1")
//                        .description("desc1")
//                        .build(),
//                    TestNestedObj.builder()
//                        .code("code2")
//                        .description("desc2")
//                        .build()))
//                .build();
//        TestObj obj2 = TestObj.builder()
//                .stringField("stringField")
//                .testNestedObjs(ImmutableSet.of(TestNestedObj.builder()
//                        .code("code2")
//                        .description("desc2")
//                        .build(),
//                    TestNestedObj.builder()
//                        .code("code1")
//                        .description("desc1")
//                        .build()))
//                .build();
//        //assertThat(EqualsHelper.equals(obj1, obj2)).isTrue();
//        assertThat(obj1.equals(obj2)).isTrue();
//
//    }
//
//    @Data
//    @Builder
//    static class TestObj {
//        private String stringField;
//        private Set<TestNestedObj> testNestedObjs;
//    }
//
//    @Data
//    @Builder
//    static class TestNestedObj{
//        private String code;
//        private String description;
//    }
}

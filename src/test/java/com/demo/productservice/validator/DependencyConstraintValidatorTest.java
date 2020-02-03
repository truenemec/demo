package com.demo.productservice.validator;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;

import javax.validation.Validator;

        import static org.assertj.core.api.Assertions.assertThat;

class DependencyConstraintValidatorTest {

    private static Validator validator;
//
//    @BeforeAll
//    public static void createValidator() {
//        validator = Validation.buildDefaultValidatorFactory().getValidator();
//    }
//
//    @Test
//    public void testParentId() {
//        TestDto dto = TestDto.builder().childOne("").childTwo("").build();
//        Set<ConstraintViolation<TestDto>> errors = validator.validate(dto);
//        assertThat(errors.size()).isEqualTo(2);
//    }
//
//    @Builder
//    @DependencyConstraint(attribute = "childOne", dependsOn = "parent")
//    @DependencyConstraint(attribute = "childTwo", dependsOn = "parent")
//    @Getter
//    private static class TestDto {
//        private String parent;
//        private String childOne;
//        private String childTwo;
//    }

}
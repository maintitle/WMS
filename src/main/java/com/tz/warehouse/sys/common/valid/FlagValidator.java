package com.tz.warehouse.sys.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 用于验证状态是否在指定范围内的注解Created by TangZhen on 2022/9/22
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {
    int[] value() default {};

    String message() default "参数错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

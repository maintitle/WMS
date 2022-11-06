package com.tz.warehouse.sys.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 状态约束校验器Created by TangZhen on 2022/9/22
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator,Integer> {
    private Set<Integer> set = new HashSet<>();
    @Override
    public void initialize(FlagValidator flagValidator) {
        int[] vals = flagValidator.value();
        for(int val : vals){
            set.add(val);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }
}

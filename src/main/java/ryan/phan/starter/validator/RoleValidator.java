package ryan.phan.starter.validator;

import ryan.phan.starter.anotation.RoleValidation;
import ryan.phan.starter.constant.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleValidator implements ConstraintValidator<RoleValidation, Role> {
    private Set<Role> roleSet;

    @Override
    public void initialize(RoleValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        roleSet = new HashSet<>(List.of(Role.values()));
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        return roleSet.contains(value);
    }
}

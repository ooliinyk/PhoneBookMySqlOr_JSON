package com.validator;

import com.AbstractTest;
import com.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 25.04.2016.
 */
public class UserValidatorTest extends AbstractTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void checkUser() {
        User entity = new User();


        entity.setName("Oleksandr");
        entity.setSurname("Oliinyk");
        entity.setPatronymic("Sergiy");
        entity.setLogin("asdasdad");
        entity.setPassword("dasdasdads");


        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(entity);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void checkUserName() {
        User entity = new User();


        entity.setName("Ol");
        entity.setSurname("Oliinyk");
        entity.setPatronymic("Sergiy");
        entity.setLogin("asdasdad");
        entity.setPassword("dasdasdads");


        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(entity);

        assertEquals(1, constraintViolations.size());

        assertEquals(
                "size must be between 5 and 2147483647",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void checkUserLogin() {
        User entity = new User();


        entity.setName("Olasdsa");
        entity.setSurname("Oliinyk");
        entity.setPatronymic("Sergiy");
        entity.setLogin("фывфыв");
        entity.setPassword("dasdasdads");


        Set<ConstraintViolation<User>> constraintViolations =
                validator.validate(entity);

        assertEquals(1, constraintViolations.size());

        assertEquals(
                "{only English characters allowed, without special characters}",
                constraintViolations.iterator().next().getMessage()
        );
    }
}

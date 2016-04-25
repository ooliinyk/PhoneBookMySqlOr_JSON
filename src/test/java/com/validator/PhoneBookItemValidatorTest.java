package com.validator;

import com.AbstractTest;
import com.entity.PhoneBookItem;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23.04.2016.
 */
public class PhoneBookItemValidatorTest extends AbstractTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void checkPhoneBook() {
        PhoneBookItem entity = new PhoneBookItem();

        entity.setName("Oleksandr");
        entity.setSurname("Oliinyk");
        entity.setPatronymic("Sergiy");
        entity.setMobPhone("+380(93)5546881");
        entity.setEmail("ooliinyk@outlook.com");


        Set<ConstraintViolation<PhoneBookItem>> constraintViolations =
                validator.validate(entity);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void checkPhoneBookNameTest() {
        PhoneBookItem entity = new PhoneBookItem();

        entity.setName("asd");
        entity.setSurname("Oliinyk");
        entity.setPatronymic("Sergiy");
        entity.setMobPhone("+380(93)5546881");
        entity.setEmail("ooliinyk@outlook.com");


        Set<ConstraintViolation<PhoneBookItem>> constraintViolations =
                validator.validate(entity);

        assertEquals(1, constraintViolations.size());

        assertEquals(
                "size must be between 4 and 2147483647",
                constraintViolations.iterator().next().getMessage()
        );

    }

    @Test
    public void checkPhoneBookSurNameTest() {
        PhoneBookItem entity = new PhoneBookItem();

        entity.setName("asasdasdd");
        entity.setSurname("Ol");
        entity.setPatronymic("Sergiy");
        entity.setMobPhone("+380(93)5546881");
        entity.setEmail("ooliinyk@outlook.com");


        Set<ConstraintViolation<PhoneBookItem>> constraintViolations =
                validator.validate(entity);

        assertEquals(1, constraintViolations.size());

        assertEquals(
                "size must be between 4 and 2147483647",
                constraintViolations.iterator().next().getMessage()
        );

    }

    @Test
    public void checkPhoneBookSurNameTestNotEmpty() {
        PhoneBookItem entity = new PhoneBookItem();

        entity.setName("asasdasdd");
//        entity.setSurname("Ol");
        entity.setPatronymic("Sergiy");
        entity.setMobPhone("+380(93)5546881");
        entity.setEmail("ooliinyk@outlook.com");


        Set<ConstraintViolation<PhoneBookItem>> constraintViolations =
                validator.validate(entity);

        assertEquals(1, constraintViolations.size());

        assertEquals(
                "may not be empty",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void checkPhoneBookMobPhoneTest() {
        PhoneBookItem entity = new PhoneBookItem();

        entity.setName("OLeksansd");
        entity.setSurname("Oliinyk");
        entity.setPatronymic("Sergiy");
        entity.setMobPhone("+380(93)55");
        entity.setEmail("ooliinyk@outlook.com");


        Set<ConstraintViolation<PhoneBookItem>> constraintViolations =
                validator.validate(entity);

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "{Phone}",
                constraintViolations.iterator().next().getMessage()
        );
    }
}

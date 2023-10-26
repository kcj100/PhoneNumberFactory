package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] arr = new PhoneNumber[phoneNumberCount];
        for(int i = 0; i < phoneNumberCount; i++) {
            arr[i] = createRandomPhoneNumber();
        }
        return arr;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() {
//        Random random = new Random();
//        int areaCode = random.nextInt(800) + 200;
//        int centralOfficeCode = random.nextInt(800) + 200;
//        int phoneLineCode = random.nextInt(10000);
        int areaCode = RandomNumberFactory.createInteger(200, 999);
        int centralOfficeCode = RandomNumberFactory.createInteger(200, 999);
        int phoneLineCode = RandomNumberFactory.createInteger(1000, 9999);
        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode)  {
        String formattedPhoneNumber = "(" + areaCode + ")-" + centralOfficeCode + "-" + phoneLineCode;
        System.out.println(formattedPhoneNumber);
        try {
            return createPhoneNumber(formattedPhoneNumber);
        } catch (InvalidPhoneNumberFormatException e) {
            logger.info(formattedPhoneNumber + " is not a valid phone number");
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        PhoneNumber phoneNumber = null;
        try {
//            String formattedNumber = phoneNumberString.replaceAll("[-.\\s]?", "");
//            formattedNumber = String.format("(%s) %s-%s",
//                    formattedNumber.substring(0, 3),
//                    formattedNumber.substring(3, 6),
//                    formattedNumber.substring(6, 10)
//            );
            phoneNumber = new PhoneNumber(phoneNumberString);
        }catch (InvalidPhoneNumberFormatException e) {
            logger.info(phoneNumberString + " is not a valid phone number");
            throw new InvalidPhoneNumberFormatException();
        }
        return phoneNumber;
    }
}

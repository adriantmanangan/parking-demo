package com.example.demo.parking.converter;

import com.example.demo.parking.Size;
import org.mapstruct.Named;

@Named("sizeEnumConverter")
public class SizeEnumConverter {

    @Named("enumSizeToInteger")
    public static Integer sizeToInteger(Size size) {
        if (size == null) {
            return null;
        } else {
            switch (size) {
                case LARGE:
                    return 3;
                case MEDIUM:
                    return 2;
                case SMALL:
                    return 1;
            }
        }
        return null;
    }

    @Named("integerToEnumSize")
    public static Size IntegerToSize(Integer size) {
        switch (size) {
            case 3:
                return Size.LARGE;
            case 2:
                return Size.MEDIUM;
            case 1:
                return Size.SMALL;
        }
        return null;
    }
}

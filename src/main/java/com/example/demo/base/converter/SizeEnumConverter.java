package com.example.demo.base.converter;

import com.example.demo.base.constants.Size;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Integer> getPossibleSize(Integer size) {
        List<Integer> sizes = new ArrayList<>();
        switch (size) {
            case 3:
                sizes = List.of(3);
                break;
            case 2:
                sizes = List.of(3, 2);
                break;
            case 1:
                sizes = List.of(3, 2, 1);
        }
        return sizes;
    }
}

package org.aeonbits.owner.converters;

import org.aeonbits.owner.util.bytesize.ByteSize;
import org.aeonbits.owner.util.bytesize.ByteSizeUnit;
import org.aeonbits.owner.Converter;
import org.aeonbits.owner.util.converters.ConverterUtil;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * @author Stefan Freyr Stefansson
 */
public class ByteSizeConverter implements Converter<ByteSize> {

    public ByteSize convert(Method method, String input) {
        return parse(input);
    }

    private static ByteSize parse(String input){
        String[] parts = ConverterUtil.splitNumericAndChar(input);
        String value = parts[0];
        String unit = parts[1];

        BigDecimal bdValue = new BigDecimal(value);
        ByteSizeUnit bsuUnit = ByteSizeUnit.parse(unit);

        if (bsuUnit == null){
            throw new IllegalArgumentException("Invalid unit string: '" + unit + "'");
        }

        return new ByteSize(bdValue, bsuUnit);
    }
}

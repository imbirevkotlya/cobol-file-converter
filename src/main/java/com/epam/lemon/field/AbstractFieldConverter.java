package com.epam.lemon.field;

import com.epam.lemon.record.Encoding;

public abstract class AbstractFieldConverter implements FieldConverter {

    @Override
    public byte[] convertValue(byte[] value, Encoding sourceEncoding, Encoding targetEncoding) {
        if (!isConvertedField()) {
            return value;
        }
        return convert(value, sourceEncoding, targetEncoding);
    }

    protected abstract boolean isConvertedField();

    protected byte[] convert(byte[] value, Encoding sourceEncoding, Encoding targetEncoding) {
        return null;
    }
}

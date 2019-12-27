package com.epam.lemon.field;

import com.epam.lemon.record.Encoding;
import com.epam.lemon.statement.StatementType;

import java.nio.charset.Charset;

public class AlphanumericFieldConverter extends AbstractFieldConverter {

    @Override
    protected boolean isConvertedField() {
        return true;
    }

    @Override
    protected byte[] convert(byte[] value, Encoding sourceEncoding, Encoding targetEncoding) {
        String resultValue = new String(value, Charset.forName(sourceEncoding.getCharset()));
        return resultValue.getBytes(Charset.forName(targetEncoding.getCharset()));
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.ALPHANUMERIC_STATEMENT;
    }
}

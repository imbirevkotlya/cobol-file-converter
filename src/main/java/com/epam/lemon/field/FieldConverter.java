package com.epam.lemon.field;

import com.epam.lemon.record.Encoding;
import com.epam.lemon.statement.StatementType;

public interface FieldConverter {

    StatementType getStatementType();

    byte[] convertValue(byte[] value, Encoding sourceEncoding, Encoding targetEncoding);

}

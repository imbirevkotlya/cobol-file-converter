package com.epam.lemon.field;

import com.epam.lemon.encoding.Encoding;
import com.epam.lemon.statement.StatementType;

public class IntegerField extends Field {

  public IntegerField(Encoding encoding, byte[] value,
      StatementType statementType) {
    super(encoding, value, statementType);
  }

  @Override
  public StatementType getCorrespondingStatementType() {
    return null;
  }

  @Override
  public Field buildField() {
    return null;
  }

  @Override
  public void convert(Encoding targetEncoding) {

  }
}

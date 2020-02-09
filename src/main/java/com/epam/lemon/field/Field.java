package com.epam.lemon.field;

import com.epam.lemon.Convertable;
import com.epam.lemon.encoding.Encoding;
import com.epam.lemon.statement.StatementType;

public abstract class Field implements Convertable {

  protected final Encoding encoding;
  protected final byte[] value;

  public Field(Encoding encoding, byte[] value, StatementType statementType) {
    this.encoding = encoding;
    this.value = value;
  }

  public abstract StatementType getCorrespondingStatementType();

  public abstract Field buildField();
}

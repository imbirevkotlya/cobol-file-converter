package com.epam.lemon.field;

import com.epam.lemon.Convertable;
import com.epam.lemon.encoding.Encoding;

public abstract class Field implements Convertable {

  protected final Encoding encoding;
  protected final byte[] value;

  public Field(Encoding encoding, byte[] value) {
    this.encoding = encoding;
    this.value = value;
  }
}

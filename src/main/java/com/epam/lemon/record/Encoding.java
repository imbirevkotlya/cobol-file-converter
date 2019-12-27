package com.epam.lemon.record;

public enum Encoding {

  ASCII("windows-1251"),
  EBCDIC("cp037");

  private final String charset;

  Encoding(String charset) {
    this.charset = charset;
  }

  public String getCharset() {
    return charset;
  }
}

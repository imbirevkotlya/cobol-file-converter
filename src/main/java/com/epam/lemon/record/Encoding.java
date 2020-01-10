package com.epam.lemon.record;

/**
 * Class represents the supported encodings to convert from or to. All supported encodings (can be
 * added here) are described here: Charset.fromString();
 */
public enum Encoding {

  /**
   * Standard ascii encoding
   */
  ASCII("windows-1251"),
  /**
   * IBM mainframe standard encoding
   */
  EBCDIC("cp037");

  private final String charset;

  Encoding(String charset) {
    this.charset = charset;
  }

  /**
   * Method returns the charset for the Java charset determination
   *
   * @return the Java charset to use it in the Charset.from...()
   */
  public String getCharset() {
    return charset;
  }
}

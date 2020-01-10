package com.epam.lemon.field;

import com.epam.lemon.record.Encoding;
import com.epam.lemon.statement.StatementType;
import java.nio.charset.Charset;

/**
 * Class represents the COBOL alphanumeric field conversion approach.
 */
public class AlphanumericFieldConverter extends AbstractFieldConverter {

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isConvertedField() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected byte[] convert(byte[] value, Encoding sourceEncoding, Encoding targetEncoding) {
    String resultValue = new String(value, Charset.forName(sourceEncoding.getCharset()));
    return resultValue.getBytes(Charset.forName(targetEncoding.getCharset()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public StatementType getStatementType() {
    return StatementType.ALPHANUMERIC_STATEMENT;
  }
}

package com.epam.lemon.field;

import com.epam.lemon.record.Encoding;
import com.epam.lemon.statement.StatementType;

/**
 * Class represents the one field conversion approach. Cobol field types described in the IBM
 * documentation.
 *
 * All you need to add a new field conversion approach is to realize this interface and add it into
 * the converter constructor.
 */
public interface FieldConverter {

  /**
   * Method returns the statement type, which this converter is supported to convert
   *
   * @return the field type for conversion
   */
  StatementType getStatementType();

  /**
   * Main converter method to convert the field value from one encoding to another.
   *
   * @param value is a raw field data, related to the field type which is specified above
   * @param sourceEncoding is a bytes source encoding
   * @param targetEncoding is a bytes target encoding
   * @return the converted bytes in the target encoding
   */
  byte[] convertValue(byte[] value, Encoding sourceEncoding, Encoding targetEncoding);

}

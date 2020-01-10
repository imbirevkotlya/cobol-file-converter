package com.epam.lemon.field;

import com.epam.lemon.exception.InvalidDataException;
import com.epam.lemon.record.Encoding;

/**
 * Class represents the little helper for the field converter interface.
 *
 * All you need to do is implement 1 or 2 methods in your implementation and support the contract.
 */
public abstract class AbstractFieldConverter implements FieldConverter {

  /**
   * {@inheritDoc}
   */
  @Override
  public byte[] convertValue(byte[] value, Encoding sourceEncoding, Encoding targetEncoding) {
    if (!isConvertedField()) {
      return value;
    }
    return convert(value, sourceEncoding, targetEncoding);
  }

  /**
   * Method encapsulates the logic about what field types should be converted, and what are not.
   *
   * For example, simple integer fields should not be converted at all, but alphanumeric fields
   * should be converted
   *
   * @return the ability of the field with specified type be converted or not, true - the field
   * should be converted, false - is not (convert method will just do nothing)
   */
  protected abstract boolean isConvertedField();

  /**
   * Helper method to convert one field to another.
   *
   * Parameters are described in the convertValue method in the interface.
   */
  protected byte[] convert(byte[] value, Encoding sourceEncoding, Encoding targetEncoding) {
    throw new InvalidDataException("Such field has no conversion abilities yet!");
  }
}

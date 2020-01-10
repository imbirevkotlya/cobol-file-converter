package com.epam.lemon.record;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.exception.InvalidDataException;

/**
 * Class represents the mainframe one record (value, structure, determined by copybook and length).
 */
public class Record {

  private final Copybook recordStructure;
  private byte[] value;
  private final Integer length;

  /**
   * Main record constructor.
   * @param recordStructure is a record definition using the COBOL copybook.
   * @param length is a record length (actual data length)
   */
  public Record(Copybook recordStructure, Integer length) {
    this.recordStructure = recordStructure;
    this.length = length;
  }

  /**
   * Method returns the record definition in COBOL copybook view.
   * @return the record organization
   */
  public Copybook getRecordStructure() {
    return recordStructure;
  }

  /**
   * Method returns the record value in raw bytes.
   * @return the record value in raw bytes.
   */
  public byte[] getValue() {
    return value;
  }

  /**
   * Method to set record value in raw bytes. Encoding is not should be specified.
   * @param value the record raw bytes
   */
  public void setValue(byte[] value) {
    if (value.length != length) {
      throw new InvalidDataException("Such field has invalid format, actual data length = " + value.length
          + ", where the expected length should be = " + length);
    }
    this.value = value;
  }

  /**
   * Method to get actual record length.
   * @return the actual record length
   */
  public Integer getLength() {
    return length;
  }
}

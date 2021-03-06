package com.epam.lemon.record;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class represents the iteration pattern for the mainframe records.
 */
public class RecordIterator implements Iterator<Record> {

  private final Copybook recordStructure;
  private final byte[] value;
  private final int datasetLength;
  private final int recordLength;

  private int cursor;

  /**
   * Main object consructor. The record length will be calculated via the copybook presentation
   *
   * @param recordStructure is a COBOL record organization representation.
   * @param value is a raw dataset value
   */
  public RecordIterator(Copybook recordStructure, byte[] value) {
    this.recordStructure = recordStructure;
    this.value = value;
    recordLength = determineRecordLength();
    datasetLength = value.length;
  }

  private Integer determineRecordLength() {
    int recordLength = 0;
    for (DataDeclarationCobolStatement statement : recordStructure.getCobolStatements()) {
      if (statement.getStatementType().equals(StatementType.GROUP_STATEMENT)) {
        GroupDataDeclarationCobolStatement groupStatement = (GroupDataDeclarationCobolStatement) statement;
        for (DataDeclarationCobolStatement childStatement : groupStatement
            .getChildrenStatements()) {
          recordLength += ((RegularDataDeclarationCobolStatement) childStatement).getLength();
        }
      } else {
        recordLength += ((RegularDataDeclarationCobolStatement) statement).getLength();
      }
    }
    return recordLength;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return cursor < datasetLength;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Record next() {
    if (hasNext()) {
      int actualRecordLength =
          recordLength > (this.value.length - cursor) ? (this.value.length - cursor) : recordLength;
      Record record = new Record(recordStructure, actualRecordLength);
      byte[] buffer = new byte[actualRecordLength];
      System.arraycopy(this.value, cursor, buffer, 0, actualRecordLength);
      record.setValue(buffer);
      cursor += actualRecordLength;
      return record;
    }
    throw new NoSuchElementException();
  }

  /**
   * Method returns the dataset raw value.
   *
   * @return the dataset raw value.
   */
  public byte[] getValue() {
    return value;
  }

}
package com.epam.lemon.record;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RecordIterator implements Iterator<Record> {

  private final Copybook recordStructure;
  private final byte[] value;
  private final Encoding sourceEncoding;
  private final int maxCursorValue;
  private final int recordLength;
  private int valuePosition = 0;

  public RecordIterator(Copybook recordStructure, byte[] value, Encoding sourceEncoding) {
    this.recordStructure = recordStructure;
    this.sourceEncoding = sourceEncoding;
    this.value = value;
    recordLength = determineRecordLength();
    maxCursorValue = value.length;
  }

  private Integer determineRecordLength() {
    Integer length = 0;
    for (DataDeclarationCobolStatement cobolStatement : recordStructure.getCobolStatements()) {
      if (cobolStatement.getStatementType().equals(StatementType.GROUP_STATEMENT)) {
        GroupDataDeclarationCobolStatement groupStatement = (GroupDataDeclarationCobolStatement) cobolStatement;
        for (DataDeclarationCobolStatement childrenStatement : groupStatement.getChildrenStatements()) {
          RegularDataDeclarationCobolStatement regularStatement = (RegularDataDeclarationCobolStatement) childrenStatement;
          length += regularStatement.getLength();
        }
      }
      else {
        RegularDataDeclarationCobolStatement regularStatement = (RegularDataDeclarationCobolStatement) cobolStatement;
        length += regularStatement.getLength();
      }
    }
    return length;
  }

  @Override
  public boolean hasNext() {
    return valuePosition < maxCursorValue;
  }

  @Override
  public Record next() {
    if (hasNext()) {
      Record record = new Record(recordStructure);
      record.setEncoding(sourceEncoding);
      record.setValue(Arrays.copyOfRange(value, valuePosition, valuePosition + recordLength));
      valuePosition += recordLength;
      return record;
    }
    throw new NoSuchElementException();
  }
}

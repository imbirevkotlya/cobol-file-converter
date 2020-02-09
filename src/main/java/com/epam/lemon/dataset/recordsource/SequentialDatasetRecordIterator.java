package com.epam.lemon.dataset.recordsource;

import com.epam.lemon.copybook.Copybook;

public class SequentialDatasetRecordIterator implements RecordIterator {

  private final Copybook copybook;
  private final Integer recordLength;
  private final byte[] dataset;

  private int cursor;

  public SequentialDatasetRecordIterator(Copybook copybook, Integer recordLength, byte[] dataset) {
    this.copybook = copybook;
    this.recordLength = recordLength;
    this.dataset = dataset;
  }

  @Override
  public Copybook getRecordStructure() {
    return copybook;
  }

  @Override
  public Integer getRecordLength() {
    return recordLength;
  }

  @Override
  public boolean hasNext() {
    return cursor < dataset.length;
  }

  @Override
  public byte[] next() {
    byte[] recordValue = new byte[recordLength];
    System.arraycopy(dataset, cursor, recordValue, 0, recordLength);
    cursor += recordLength;
    return recordValue;
  }
}

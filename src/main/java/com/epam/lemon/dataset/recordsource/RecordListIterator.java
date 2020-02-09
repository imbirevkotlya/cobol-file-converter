package com.epam.lemon.dataset.recordsource;

import com.epam.lemon.copybook.Copybook;
import java.util.List;

public class RecordListIterator implements RecordIterator {

  private final Copybook copybook;
  private final Integer recordLength;
  private final List<byte[]> records;

  private int cursor;

  public RecordListIterator(Copybook copybook, Integer recordLength, List<byte[]> records) {
    this.copybook = copybook;
    this.recordLength = recordLength;
    this.records = records;
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
    return cursor < records.size();
  }

  @Override
  public byte[] next() {
    return records.get(cursor++);
  }
}

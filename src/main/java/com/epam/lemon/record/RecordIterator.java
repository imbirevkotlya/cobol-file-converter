package com.epam.lemon.record;

import com.epam.lemon.copybook.Copybook;
import java.util.Iterator;

public class RecordIterator implements Iterator<Record> {

  private final Copybook recordStructure;

  public RecordIterator(Copybook recordStructure) {
    this.recordStructure = recordStructure;
  }

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public Record next() {
    return null;
  }
}

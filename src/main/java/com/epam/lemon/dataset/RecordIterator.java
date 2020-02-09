package com.epam.lemon.dataset;

import com.epam.lemon.copybook.Copybook;
import java.util.Iterator;

public abstract class RecordIterator implements Iterator<byte[]> {

  protected final Copybook copybook;
  protected final byte[] dataset;

  public RecordIterator(Copybook copybook, byte[] dataset) {
    this.copybook = copybook;
    this.dataset = dataset;
  }

  public Copybook getCopybook() {
    return copybook;
  }

  public abstract Integer getRecordLength();
}

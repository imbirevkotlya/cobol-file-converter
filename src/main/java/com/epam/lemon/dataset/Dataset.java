package com.epam.lemon.dataset;

import com.epam.lemon.Convertable;
import com.epam.lemon.copybook.Copybook;

/**
 * The class represents the mainframe dataset, as a container for the records, with values.
 */
public abstract class Dataset implements Convertable {

  protected final Copybook recordStructure;
  protected final Integer recordLength;

  public Dataset(RecordIterator recordIterator) {
    this.recordStructure = recordIterator.getRecordStructure();
    this.recordLength = recordIterator.getRecordLength();
  }
}

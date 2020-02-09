package com.epam.lemon.dataset;

import com.epam.lemon.Convertable;
import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.dataset.recordsource.RecordIterator;
import com.epam.lemon.encoding.Encoding;

/**
 * The class represents the mainframe dataset, as a container for the records, with values.
 */
public abstract class Dataset implements Convertable {

  protected final Copybook recordStructure;
  protected final Integer recordLength;
  protected final Encoding encoding;

  public Dataset(RecordIterator recordIterator, Encoding encoding) {
    this.recordStructure = recordIterator.getRecordStructure();
    this.recordLength = recordIterator.getRecordLength();
    this.encoding = encoding;
  }
}

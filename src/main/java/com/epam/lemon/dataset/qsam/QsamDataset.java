package com.epam.lemon.dataset.qsam;

import com.epam.lemon.dataset.Dataset;
import com.epam.lemon.dataset.recordsource.RecordIterator;
import com.epam.lemon.encoding.Encoding;
import com.epam.lemon.record.Record;
import java.util.ArrayList;
import java.util.List;

public class QsamDataset extends Dataset {

  private final List<Record> records = new ArrayList<>();
  private final RecordIterator recordIterator;

  public QsamDataset(RecordIterator recordIterator, Encoding encoding) {
    super(recordIterator, encoding);
    this.recordIterator = recordIterator;
    initRecords();
  }

  private void initRecords() {
    while (recordIterator.hasNext()) {
      byte[] recordValue = recordIterator.next();
      records.add(new Record(recordStructure, recordValue));
    }
  }

  @Override
  public void convert(Encoding targetEncoding) {
    for (Record record : records) {
      record.convert(targetEncoding);
    }
  }
}

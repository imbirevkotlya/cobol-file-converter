package com.epam.lemon.dataset;

import com.epam.lemon.record.Record;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

  private final List<Record> records;
  private final Integer datasetLength;

  public Dataset(Integer datasetLength) {
    this.datasetLength = datasetLength;
    this.records = new ArrayList<>();
  }

  public void addRecord(Record record) {
    records.add(record);
  }

  public byte[] getDataFile() {
    ByteBuffer buffer = ByteBuffer.allocate(datasetLength);
    for (Record record : records) {
      buffer.put(record.getValue());
    }
    return buffer.array();
  }
}

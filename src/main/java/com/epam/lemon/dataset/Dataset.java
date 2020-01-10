package com.epam.lemon.dataset;

import com.epam.lemon.record.Record;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents the mainframe QSAM dataset with array of records inside it.
 */
public class Dataset {

  private final List<Record> records;
  private final Integer datasetLength;

  /**
   * Main dataset constructor with determined length of raw data inside it.
   *
   * @param datasetLength is a length of the byte array, which is represented via records list (can
   * be added via the addRecord method)
   */
  public Dataset(Integer datasetLength) {
    this.datasetLength = datasetLength;
    this.records = new ArrayList<>();
  }

  /**
   * The method to add mainframe record in the dataset.
   *
   * @param record the mainframe record which we need to add
   */
  public void addRecord(Record record) {
    records.add(record);
  }

  /**
   * The method to get raw dataset bytes from the records list.
   *
   * @return the raw dataset in bytes
   */
  public byte[] getDataFile() {
    ByteBuffer buffer = ByteBuffer.allocate(datasetLength);
    for (Record record : records) {
      buffer.put(record.getValue());
    }
    return buffer.array();
  }
}

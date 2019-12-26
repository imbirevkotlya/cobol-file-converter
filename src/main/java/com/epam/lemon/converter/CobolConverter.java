package com.epam.lemon.converter;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;
import com.epam.lemon.dataset.Dataset;
import com.epam.lemon.exception.InvalidDataException;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.record.Encoding;
import com.epam.lemon.record.Record;
import com.epam.lemon.record.RecordIterator;

public class CobolConverter {

  public byte[] convert(byte[] dataFile, byte[] copybook, Encoding targetEncoding) throws InvalidStatementFormatException, InvalidDataException {
    CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(copybook);
    Copybook parsedCopybook = new CopybookParser().parse(copybookStatementIterator);
    return convert(dataFile, parsedCopybook, targetEncoding);
  }

  private byte[] convert(byte[] dataFiles, Copybook copybook, Encoding targetEncoding) throws InvalidDataException {
    RecordIterator recordIterator = new RecordIterator(copybook);
    return convert(recordIterator, targetEncoding);
  }

  private byte[] convert(RecordIterator dataFileIterator, Encoding targetEncoding) throws InvalidDataException {
    Dataset dataset = new Dataset();
    while (dataFileIterator.hasNext()) {
      Record record = dataFileIterator.next();
      record.convert(targetEncoding);
      dataset.addRecord(record);
    }
    return buildDataFile(dataset);
  }

  private byte[] buildDataFile(Dataset dataset) {
    return dataset.getDataFile();
  }

}
